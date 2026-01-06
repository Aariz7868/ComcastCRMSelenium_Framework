package com.comcast.crm.orgtest;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
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
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationTest {
	public static void main(String[] args) throws InterruptedException, Throwable {
		//create object
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		
		
		//read data from property file
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL      = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD  = fLib.getDataFromPropertiesFile("password");
	    
		WebDriver driver = null;
		
		if(BROWSER.equalsIgnoreCase("chrome"))
			driver= new ChromeDriver();
		else if(BROWSER.equalsIgnoreCase("firefox"))
			driver= new FirefoxDriver();
		else if(BROWSER.equalsIgnoreCase("edge"))
			driver=new EdgeDriver();
		else {
			driver=new ChromeDriver();
		}
		
		//read data from excel file
		FileInputStream fis1 = new FileInputStream("C:\\Users\\91790\\OneDrive\\Documents\\testdatacrm.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("org");
		Row row = sh.getRow(1);
		
		String orgname = row.getCell(2).toString() + jLib.getRandomNmber();
		String shippingAdd = row.getCell(6).toString() + jLib.getRandomNmber();
		// step-1 login to app
		
		
		
		LoginPage lp = new LoginPage(driver);
		
		lp.loginToApp(URL, USERNAME, PASSWORD);
		
//		// step-2 navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
		
		
		 //step-3 click on create Organization button
		 OrganizationsPage op= new OrganizationsPage(driver);
		 op.getCreateNewOrgBtn().click();
		 
		 //step-4 enter all the details and create new organization
		 CreateNewOrganizationPage cnp= new CreateNewOrganizationPage(driver);
		 cnp.createOrg(orgname, shippingAdd);
		
		//step-5 verify header msg expected result
		 
		 OrganizationInfoPage oip= new OrganizationInfoPage(driver);
		 String headerInfo = oip.getHeaderMsg().getText();
		
		if(headerInfo.contains(orgname))
		{
			System.out.println(orgname +  " is visible in headerInfo====Pass");
		}else {
			System.out.println(orgname +  " is visible in headerInfo====Fail");
		}
		
		//step-6 verify org name
		String actualOrgName= oip.getOrgName().getText();
		if(actualOrgName.trim().equals(orgname))
		{
			System.out.println(orgname +  " is created====Pass");
		}else {
			System.out.println(orgname +  " is not created====Fail");
		}
		
		//step-7 logout
		hp.logout();
		driver.quit();
		
	}
	}


