package com.comcast.crm.basetest;

import java.sql.SQLException;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {
	
	/* create oject */
	public DataBaseUtility dbLib= new DataBaseUtility();
	public FileUtility fLib= new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public WebDriver driver = null;
	
	
	@BeforeSuite(groups = {"smokeTest", "regressionTest"})
	public void configBS() throws SQLException {
		System.out.println("connect to db");
		dbLib.getDbConnection();
		}
		
	@BeforeClass(groups = {"smokeTest", "regressionTest"})
	public void configBC() throws Throwable {
		System.out.println("Launch the browser");
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		

		if (BROWSER.equals("chrome"))
			driver = new ChromeDriver();
		else if (BROWSER.equals("firefox"))
			driver = new FirefoxDriver();
		else if (BROWSER.equals("edge"))
			driver = new EdgeDriver();
		else {
			driver = new ChromeDriver();
		}
		
		UtilityClassObject.setDriver(driver);
	}
	
	@BeforeMethod(groups = {"smokeTest", "regressionTest"})
	public void configBM() throws Throwable {
		System.out.println("Login");
		LoginPage lp = new LoginPage(driver);
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		lp.loginToApp(URL, USERNAME, PASSWORD);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1500));
	}
	
	@AfterMethod(groups = {"smokeTest", "regressionTest"})
	public void configAM() {
		System.out.println("Logout");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}
	
	@AfterClass(groups = {"smokeTest", "regressionTest"})
	public void configAC() throws SQLException {
		System.out.println("Close the browser");
		driver.quit();
	}
	
	@AfterSuite(groups = {"smokeTest", "regressionTest"})
	public void configAS() throws SQLException {
		System.out.println("close db, Report backup");
		dbLib.closeConnection();
		
	}	
}
