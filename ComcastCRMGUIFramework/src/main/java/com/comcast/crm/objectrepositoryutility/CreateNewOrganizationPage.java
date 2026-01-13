package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {
	
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(name="accountname")
	private WebElement orgNameEdt;

	@FindBy(name="ship_street")
	private WebElement shippingEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDD;
	
	@FindBy(name="accounttype")
	private WebElement accountTypeDD;
	
	@FindBy(id="phone")
	private WebElement phoneNumberEdt;

	public WebElement getPhoneNumberEdt() {
		return phoneNumberEdt;
	}


	public WebElement getAccountTypeDD() {
		return accountTypeDD;
	}


	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getShippingEdt() {
		return shippingEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public WebElement getIndustryDD() {
		return industryDD;
	}
	
	public void createOrg(String orgname, String shippingAdd ) {
		orgNameEdt.sendKeys(orgname);
		shippingEdt.sendKeys(shippingAdd);
		saveBtn.click();
	}
	
	public void createOrg(String orgname, String shippingAdd, String industry, String type ) {
		orgNameEdt.sendKeys(orgname);
		shippingEdt.sendKeys(shippingAdd);
		Select sel = new Select(industryDD);
		sel.selectByVisibleText(industry);
		Select sel1 = new Select(accountTypeDD);
		sel1.selectByVisibleText(type);
		
		saveBtn.click();
	}

	

}
