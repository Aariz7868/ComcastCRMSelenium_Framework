package com.comcast.crm.contacttest;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
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
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.listenerutility.ListenerImpClass;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;


public class CreateContactTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createContactTest() throws Throwable, IOException {

		// read data from excel file
		//UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		FileInputStream fis1 = new FileInputStream("C:\\Users\\91790\\OneDrive\\Documents\\testdatacrm.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("contact");
		Row row = sh.getRow(1);

		String lastname = row.getCell(3).toString() + jLib.getRandomNmber();
		
		// step-1 login to app
		// step-2 navigate to contact module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact page");
		HomePage homePage = new HomePage(driver);
		homePage.getContactLink().click();
		

		//step-3 click on create contact button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create contact page");
		ContactPage contactPage = new ContactPage(driver);
		contactPage.getCreateNewContactBtn().click();
		

		//step-4 enter all details and create new contact
		UtilityClassObject.getTest().log(Status.INFO, "create a new contact");
		CreateNewContactPage createNewContactPage = new CreateNewContactPage(driver);
		createNewContactPage.getContactLastNameEdt().sendKeys(lastname);
		createNewContactPage.getSaveBtn().click();
		
		
		//step-5 verify header msg expected result
		ContactInfoPage contactInformationPage = new ContactInfoPage(driver);
		String contactHeaderInformation = contactInformationPage.getContactHeaderMsg().getText();
		Assert.assertEquals(true, contactHeaderInformation.contains(lastname));
		

		//step-6 verify lastname
		String actualLastName =contactInformationPage.getLastName().getText();
		Assert.assertEquals(actualLastName, lastname);
	
	}

	@Test(groups = "regressionTest")
	public void createContactWithSupportDateTest() throws Throwable, IOException {

		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequiredDateYYYYMMDD(30);
		
		// read data from excel file
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		FileInputStream fis1 = new FileInputStream("C:\\Users\\91790\\OneDrive\\Documents\\testdatacrm.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("contact");
		Row row = sh.getRow(2);

		String lastname = row.getCell(3).toString() + jLib.getRandomNmber();
		
		// step-1 login to app
		// step-2 navigate to contact module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact page");
		HomePage homePage = new HomePage(driver);
		homePage.getContactLink().click();
		
		
		//step-3 click on create contact button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create contact page");
		ContactPage contactPage = new ContactPage(driver);
		contactPage.getCreateNewContactBtn().click();
		
		
		
		//step-4 enter all details and create new contact
		UtilityClassObject.getTest().log(Status.INFO, "create a new contact");
		CreateNewContactPage createNewContactPage = new CreateNewContactPage(driver);
		createNewContactPage.getContactLastNameEdt().sendKeys(lastname);
		createNewContactPage.getSupportStartDateEdt().clear();
		createNewContactPage.getSupportStartDateEdt().sendKeys(startDate);
		createNewContactPage.getSupportEndDateEdt().clear();
		createNewContactPage.getSupportEndDateEdt().sendKeys(endDate);
		createNewContactPage.getSaveBtn().click();
		
		
		//step-5 verify support date
		ContactInfoPage contactInformationPage = new ContactInfoPage(driver);
		String actualStartDate = contactInformationPage.getStartDateField().getText();
		String actualEndDate =contactInformationPage.getEndDateField().getText(); 

		Assert.assertEquals(actualStartDate, startDate);
		Assert.assertEquals(actualEndDate, endDate);
	}

	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws InterruptedException, Throwable {
		
		// read data from excel file
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		FileInputStream fis1 = new FileInputStream("C:\\Users\\91790\\OneDrive\\Documents\\testdatacrm.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("contact");
		Row row = sh.getRow(3);

		String orgname = row.getCell(2).toString() + jLib.getRandomNmber();
		String lastname = row.getCell(3).toString() + jLib.getRandomNmber();
		String shippingAdd = row.getCell(4).toString() + jLib.getRandomNmber();
		
		// step-1 login to app
		
		//step-2 navigate to org module
		HomePage homePage = new HomePage(driver);
		homePage.getOrgLink().click();
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		  
		//step-3 click on create org button
		OrganizationsPage organizationPage = new OrganizationsPage(driver);
		organizationPage.getCreateNewOrgBtn().click();
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		
		//step-4 Create new organization
		CreateNewOrganizationPage createNewOrganizationPage = new CreateNewOrganizationPage(driver);
		createNewOrganizationPage.createOrg(orgname, shippingAdd);
		Thread.sleep(1500);
		UtilityClassObject.getTest().log(Status.INFO, "create a new org");
		
		// verify header msg expected result
		OrganizationInfoPage organizationInformationPage= new OrganizationInfoPage(driver);
		String organizationHeaderInfo =organizationInformationPage.getHeaderMsg().getText(); 
		Assert.assertEquals(true, organizationHeaderInfo.contains(orgname));
		
		// verify org name
		String actualOrgName = organizationInformationPage.getOrgName().getText();
		Assert.assertEquals(actualOrgName, orgname);
	
		// navigate to contact module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact page");
		homePage.getContactLink().click();
		
		
		// click on create contact button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create contact page");
		ContactPage contactPage = new ContactPage(driver);
		contactPage.getCreateNewContactBtn().click();
		
		
		// enter all details and create new contact
		UtilityClassObject.getTest().log(Status.INFO, "create a new contact");
		CreateNewContactPage createNewContactPage = new CreateNewContactPage(driver);
		createNewContactPage.getContactLastNameEdt().sendKeys(lastname);
		createNewContactPage.getAddOrgBtn().click();
		

		// switch to child window
		wLib.SwitchToTabOnURL(driver, "module=Accounts&action");

		
		organizationPage.getSearchEdt().sendKeys(orgname);
		organizationPage.getSearchBtn().click();
		driver.findElement(By.linkText(orgname + "")).click();

		// switch to parent window
		wLib.SwitchToTabOnURL(driver, "module=Contacts&action");
		createNewContactPage.getSaveBtn().click();
		
		// verify header msg expected result
		ContactInfoPage contactInformationPage = new ContactInfoPage(driver);
		String contactHeaderInformation = contactInformationPage.getContactHeaderMsg().getText();
		
		Assert.assertEquals(true, contactHeaderInformation.contains(lastname));
		
		// verify contact lastname
		String actualLastName =contactInformationPage.getLastName().getText();
		Assert.assertEquals(actualLastName, lastname);
	
		// verify organization name
		
		String actOrgName = driver.findElement(By.linkText(orgname)).getText();
		//System.out.println("actORGnAME IS "+actOrgName);
		Assert.assertEquals(actOrgName.trim(), orgname);
		

	}

}
