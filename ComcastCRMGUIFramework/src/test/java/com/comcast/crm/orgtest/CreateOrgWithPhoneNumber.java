package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrgWithPhoneNumber {
	public static void main(String[] args) throws Throwable {
		
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
	
			
			//read data from property file
			FileInputStream fis = new FileInputStream("C:\\Users\\91790\\Desktop\\New folder\\commondata.properties");
			Properties pObj= new Properties();
			pObj.load(fis);
			String BROWSER = pObj.getProperty("browser");
			String URL = pObj.getProperty("url");
			String USERNAME = pObj.getProperty("username");
			String PASSWORD = pObj.getProperty("password");
			WebDriver driver = null;
			
			
			if(BROWSER.equals("chrome"))
				driver= new ChromeDriver();
			else if(BROWSER.equals("firefox"))
				driver= new FirefoxDriver();
			else if(BROWSER.equals("edge"))
				driver=new EdgeDriver();
			else {
				driver=new ChromeDriver();
			}
			
			//read data from excel file
			FileInputStream fis1 = new FileInputStream("C:\\Users\\91790\\OneDrive\\Documents\\testdatacrm.xlsx");
			Workbook wb = WorkbookFactory.create(fis1);
			Sheet sh = wb.getSheet("org");
			Row row = sh.getRow(3);
			
			String orgname = row.getCell(2).toString() + jLib.getRandomNmber();
			String phoneNumber = row.getCell(5).getStringCellValue();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1500));
			
			
			
			driver.get(URL);
			driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();
			driver.findElement(By.linkText("Organizations")).click();
			
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			driver.findElement(By.name("accountname")).sendKeys(orgname);
			driver.findElement(By.name("ship_street")).sendKeys("noida");
			driver.findElement(By.id("phone")).sendKeys(phoneNumber);
			
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			Thread.sleep(1500);
			
			//verify phone number info from expected result
			String actPhoneNumber= driver.findElement(By.id("dtlview_Phone")).getText();
			if(actPhoneNumber.equals(phoneNumber))
			{
				System.out.println(phoneNumber +  " information is  verified====Pass");
			}else {
				System.out.println(orgname +  " information is not verified====Fail");
			}
			
			WebElement signout=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			Actions action = new Actions(driver);
			action.moveToElement(signout).perform();
			Thread.sleep(1500);
			//driver.close();
			//driver.findElement(By.linkText("Sign Out")).click();
			
			//Thread.sleep(1500);
			//driver.quit();
	}		
}

