package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	 public WebDriver driver;
	public ContactInfoPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement contactHeaderMsg;
	
	@FindBy(xpath="//span[@id='dtlview_Last Name']")
	private WebElement lastName;
	

	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement startDateField;
	
	public WebElement getStartDateField() {
		return startDateField;
	}

	public WebElement getEndDateField() {
		return endDateField;
	}
	@FindBy(id="dtlview_Support End Date")
	private WebElement endDateField;
	
	public WebElement getContactHeaderMsg() {
		return contactHeaderMsg;
	}

	public WebElement getLastName() {
		return lastName;
	}



	
}
