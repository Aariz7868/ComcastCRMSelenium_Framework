package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrg {

	public static void main(String[] args) throws InterruptedException, Throwable {
		
		
				FileUtility fLib = new FileUtility();
				ExcelUtility eLib = new ExcelUtility();
				JavaUtility jLib = new JavaUtility();
				WebDriverUtility wLib = new WebDriverUtility();

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
					driver= 
					new ChromeDriver();
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
				Sheet sh = wb.getSheet("contact");
				Row row = sh.getRow(3);
				
				String orgname = row.getCell(2).toString() + jLib.getRandomNmber();
				String lastname = row.getCell(3).toString() + jLib.getRandomNmber();
				
				driver.manage().window().maximize();
				wLib.waitForPageLoad(driver);
				
				
				
				driver.get(URL);
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				driver.findElement(By.linkText("Organizations")).click();
				
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				driver.findElement(By.name("accountname")).sendKeys(orgname);
				driver.findElement(By.name("ship_street")).sendKeys("noida");
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				Thread.sleep(1500);
				
				//verify header msg expected result
				String headerInfo= driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerInfo.contains(orgname))
				{
					System.out.println(orgname +  " is visible in headerInfo====Pass");
				}else {
					System.out.println(orgname +  " is visible in headerInfo====Fail");
				}
				
				//verify org name
				String actualOrgName= driver.findElement(By.id("dtlview_Organization Name")).getText();
				if(actualOrgName.equals(orgname))
				{
					System.out.println(orgname +  " is created====Pass");
				}else {
					System.out.println(orgname +  " is not created====Fail");
				}
				
				//navigate to contact module
driver.findElement(By.linkText("Contacts")).click();
				
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				driver.findElement(By.name("lastname")).sendKeys(lastname);
				driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
				
				//switch to child window
				wLib.SwitchToTabOnURL(driver, "module=Accounts&action");
			
				driver.findElement(By.id("search_txt")).sendKeys(orgname);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.linkText(orgname+"")).click();
				
				//switch to parent window
				wLib.SwitchToTabOnURL(driver, "module=Contacts&action");
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				//verify header msg expected result
				 headerInfo= driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerInfo.contains(lastname))
				{
					System.out.println(lastname +  " is visible in headerInfo====Pass");
				}else {
					System.out.println(lastname +  " is visible in headerInfo====Fail");
				}
				
				//verify contact lastname
				String actuallastname= driver.findElement(By.id("dtlview_Last Name")).getText();
				if(actuallastname.equals(lastname))
				{
					System.out.println(lastname +  " is created====Pass");
				}else {
					System.out.println(lastname +  " is not created====Fail");
				}
				//verify organization name
				String actOrgName= driver.findElement(By.id("mouseArea_Organization Name")).getText();
				if(actOrgName.trim().equals(orgname))
				{
					System.out.println("Contact is created with" +orgname +"====Pass");
				}else {
					System.out.println("Contact is not created with" +orgname +"====Fail");
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
