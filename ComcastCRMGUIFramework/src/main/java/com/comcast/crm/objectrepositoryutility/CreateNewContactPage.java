package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {
	public WebDriver driver;
	public CreateNewContactPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(name="lastname")
	private WebElement contactLastNameEdt;
	
	@FindBy(name="support_start_date")
	private WebElement supportStartDateEdt;

	@FindBy(name="support_end_date")
	private WebElement supportEndDateEdt;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement addOrgBtn;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public WebElement getContactLastNameEdt() {
		return contactLastNameEdt;
	}

	public WebElement getSupportStartDateEdt() {
		return supportStartDateEdt;
	}

	public WebElement getSupportEndDateEdt() {
		return supportEndDateEdt;
	}

	public WebElement getAddOrgBtn() {
		return addOrgBtn;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	
	
}
