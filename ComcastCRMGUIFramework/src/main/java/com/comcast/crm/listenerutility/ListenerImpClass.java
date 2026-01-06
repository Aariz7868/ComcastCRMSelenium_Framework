package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImpClass  implements ITestListener, ISuiteListener{
	public ExtentSparkReporter spark;
	public  ExtentReports report;
	public static ExtentTest test;
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
        String time= new Date().toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test SUITE Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//add ENV Information and create test
		report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "Chrome-100");
		
	}
	
	public void onFinish(ISuite suite) {
		report.flush();
		System.out.println("Report backup");
		
	}
	
	public void onTestStart(ITestResult result) {
		System.out.println("====="+result.getMethod().getMethodName()+"====START===");
		 test = report.createTest(result.getMethod().getMethodName());
		 UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"===>STARTED<===");
	}
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("====="+result.getMethod().getMethodName()+"=====END==");
		test.log(Status.PASS, result.getMethod().getMethodName()+"===>COMPLETED<===");
	}
	
	public void onTestFailure(ITestResult result) {
		String testName= result.getMethod().getMethodName();
		TakesScreenshot tks =  (TakesScreenshot)UtilityClassObject.getDriver();
		String time= new Date().toString().replace(" ", "_").replace(":", "_");
		String filePath= tks.getScreenshotAs(OutputType.BASE64);
		
		test.addScreenCaptureFromBase64String(filePath, testName+"_"+time);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"===>FAILED<===");
		
	}
	
	

}
