package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
@Listeners(com.comcast.crm.listenerutility.ListenerImpClass.class)
public class CreateOrganizationTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createOrgTest() throws Throwable {

		// read data from excel file
		FileInputStream fis1 = new FileInputStream("C:\\Users\\91790\\OneDrive\\Documents\\testdatacrm.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("org");
		Row row = sh.getRow(1);

		String orgname = row.getCell(2).toString() + jLib.getRandomNmber();
		String shippingAdd = row.getCell(6).toString() + jLib.getRandomNmber();

		// step-1 login to app
		// step-2 navigate to Organization module
		HomePage homePage = new HomePage(driver);
		homePage.getOrgLink().click();

		// step-3 click on create Organization button
		OrganizationsPage organizationPage = new OrganizationsPage(driver);
		organizationPage.getCreateNewOrgBtn().click();

		// step-4 enter all the details and create new organization
		CreateNewOrganizationPage createNewOrganizationPage = new CreateNewOrganizationPage(driver);
		createNewOrganizationPage.createOrg(orgname, shippingAdd);

		// step-5 verify header msg expected result
		//UtilityClassObject.getTest().log(Status.INFO, "Create Organization");
		OrganizationInfoPage organizationInformationPage = new OrganizationInfoPage(driver);
		String organizationHeaderInfo = organizationInformationPage.getHeaderMsg().getText();
		Assert.assertEquals(true, organizationHeaderInfo.contains(orgname));

		// step-6 verify org name
		
		String actualOrgName = organizationInformationPage.getOrgName().getText();
		Assert.assertEquals(actualOrgName, orgname);
		

	}

	@Test(groups = "regressionTest")
	public void createOrgWithIndustryTypeTest() throws InterruptedException, EncryptedDocumentException, IOException {
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();

		// read data from excel file
		FileInputStream fis1 = new FileInputStream("C:\\Users\\91790\\OneDrive\\Documents\\testdatacrm.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Org");
		Row row = sh.getRow(2);
		String orgname = row.getCell(2).toString() + jLib.getRandomNmber();
		String industry = row.getCell(3).toString();
		String type = row.getCell(4).toString();
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

		// step-5 verify Industry from dropdown
		OrganizationInfoPage organizationInformationPage = new OrganizationInfoPage(driver);
		String actIndustry = organizationInformationPage.getIndustryName().getText();
		Assert.assertEquals(actIndustry, industry);
		

		// step-6 verify AccountType from dropdown
		String actuaType = organizationInformationPage.getAccountType().getText();
		Assert.assertEquals(actuaType, type);

	}

	@Test(groups = "regressionTest")
	public void createOrgWithPhoneNumber() throws EncryptedDocumentException, IOException, InterruptedException {
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();

		// read data from excel file
		FileInputStream fis1 = new FileInputStream("C:\\Users\\91790\\OneDrive\\Documents\\testdatacrm.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("org");
		Row row = sh.getRow(3);

		String orgname = row.getCell(2).toString() + jLib.getRandomNmber();
		String phoneNumber = row.getCell(5).getStringCellValue();
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
		createNewOrganizationPage.getOrgNameEdt().sendKeys(orgname);
		createNewOrganizationPage.getShippingEdt().sendKeys(shippingAddress);
		createNewOrganizationPage.getPhoneNumberEdt().sendKeys(phoneNumber);

		createNewOrganizationPage.getSaveBtn().click();

		Thread.sleep(1500);

		// verify phone number info from expected result
		OrganizationInfoPage organizationInformationPage = new OrganizationInfoPage(driver);
		String actPhoneNumber = organizationInformationPage.getActualPhoneNumber().getText();
		Assert.assertEquals(actPhoneNumber, phoneNumber);
	}

}
