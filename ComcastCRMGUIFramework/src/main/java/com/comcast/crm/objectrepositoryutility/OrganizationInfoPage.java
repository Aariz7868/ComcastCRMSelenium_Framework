package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement orgHeaderMsg;
	
	@FindBy(xpath="//span[@id='dtlview_Organization Name']")
	private WebElement orgName;
	
	@FindBy(id="dtlview_Industry")
	private WebElement industryName;
	
	@FindBy(id="dtlview_Type")
	private WebElement accountType;
	
	@FindBy(id="dtlview_Phone")
	private WebElement actualPhoneNumber;
	
	
	public WebElement getActualPhoneNumber() {
		return actualPhoneNumber;
	}


	public WebElement getIndustryName() {
		return industryName;
	}


	public WebElement getAccountType() {
		return accountType;
	}


	public WebElement getHeaderMsg() {
		return orgHeaderMsg;
	}


	public WebElement getOrgName() {
		return orgName;
	}



	
	
	

}
