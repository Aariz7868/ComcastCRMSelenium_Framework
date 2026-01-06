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
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.listenerutility.ListenerImpClass;

public class CreateContactTest extends BaseClass {

	@Test
	public void createContactTest() throws Throwable, IOException {

		// read data from excel file
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		FileInputStream fis1 = new FileInputStream("C:\\Users\\91790\\OneDrive\\Documents\\testdatacrm.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("contact");
		Row row = sh.getRow(1);

		String lastname = row.getCell(3).toString() + jLib.getRandomNmber();

		// navigate to contact module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact page");
		driver.findElement(By.linkText("Contacts")).click();

		// click on create contact button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create contact page");
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// enter all details and create new contact
		UtilityClassObject.getTest().log(Status.INFO, "create a new contact");
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verify header msg expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertEquals(true, headerInfo.contains(lastname));
		

		// verify lastname
		String actualLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		Assert.assertEquals(actualLastName, lastname);
	
	}

	@Test
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
		
		// navigate to contact module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact page");
		driver.findElement(By.linkText("Contacts")).click();
		
		// click on create contact button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create contact page");
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		// enter all details and create new contact
		UtilityClassObject.getTest().log(Status.INFO, "create a new contact");
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String actualStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		String actualEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();

		// verify support date
		Assert.assertEquals(actualStartDate, startDate);
		Assert.assertEquals(actualEndDate, endDate);
	}

	@Test
	public void createContactWithOrgTest() throws InterruptedException, Throwable {
		
		// read data from excel file
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		FileInputStream fis1 = new FileInputStream("C:\\Users\\91790\\OneDrive\\Documents\\testdatacrm.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("contact");
		Row row = sh.getRow(3);

		String orgname = row.getCell(2).toString() + jLib.getRandomNmber();
		String lastname = row.getCell(3).toString() + jLib.getRandomNmber();
		
		// navigate to org module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		driver.findElement(By.linkText("Organizations")).click();
		
		// click on create org button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		// enter all details and create new org
		UtilityClassObject.getTest().log(Status.INFO, "create a new org");
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		driver.findElement(By.name("ship_street")).sendKeys("noida");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(1500);

		// verify header msg expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertEquals(true, headerInfo.contains(orgname));
		
		// verify org name
		String actualOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		Assert.assertEquals(actualOrgName, orgname);
	
		// navigate to contact module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact page");
		driver.findElement(By.linkText("Contacts")).click();
		
		// click on create contact button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create contact page");
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		// enter all details and create new contact
		UtilityClassObject.getTest().log(Status.INFO, "create a new contact");
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// switch to child window
		wLib.SwitchToTabOnURL(driver, "module=Accounts&action");

		driver.findElement(By.id("search_txt")).sendKeys(orgname);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText(orgname + "")).click();

		// switch to parent window
		wLib.SwitchToTabOnURL(driver, "module=Contacts&action");

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		// verify header msg expected result
		headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertEquals(true, headerInfo.contains(lastname));
		
		// verify contact lastname
		String actuallastname = driver.findElement(By.id("dtlview_Last Name")).getText();
		Assert.assertEquals(actuallastname, lastname);
	
		// verify organization name
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		Assert.assertEquals(actOrgName.trim(), orgname);
		

	}

}
