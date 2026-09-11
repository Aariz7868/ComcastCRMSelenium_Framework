package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;


public class CreateOrgWithIndustryType extends BaseClass {
	 
				 @Test
				 public void createOrgWithIndustryTest() throws InterruptedException, EncryptedDocumentException, IOException {
				//read data from excel file   
				FileInputStream fis1 = new FileInputStream("C:\\Users\\91790\\OneDrive\\Documents\\testdatacrm.xlsx");
				Workbook wb = WorkbookFactory.create(fis1);
				Sheet sh = wb.getSheet("org");
				Row row = sh.getRow(2);
				String orgname = row.getCell(2).toString() + jLib.getRandomNmber();
				String industry = row.getCell(3).toString() ;
				String type = row.getCell(4).toString() ;
				String shippingAddress = row.getCell(6).toString() + jLib.getRandomNmber();
				
				// step-1 login to app
				// step-2 navigate to Organization module
				HomePage homePage = new HomePage(driver);
				homePage.getOrgLink().click();

				// step-3 click on create Organization button
				OrganizationsPage organizationPage = new OrganizationsPage(driver);
				organizationPage.getCreateNewOrgBtn().click();
				
				// step-4 enter all the details and create new organization
				CreateNewOrganizationPage createNewOrganizationPage = new CreateNewOrganizationPage(driver);
				createNewOrganizationPage.createOrg(orgname, shippingAddress, industry, type);

				
				
				
				
				
				//verify Industry from dropdown
				
				OrganizationInfoPage organizationInformationPage = new OrganizationInfoPage(driver);
				String actIndustry = organizationInformationPage.getIndustryName().getText();
				Assert.assertEquals(actIndustry, industry);
				
				// step-6 verify AccountType from dropdown
				String actuaType = organizationInformationPage.getAccountType().getText();
				Assert.assertEquals(actuaType, type);

				
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
	


