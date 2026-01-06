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
	private WebElement headerMsg;
	
	@FindBy(xpath="//span[@id='dtlview_Organization Name']")
	private WebElement orgName;
	
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}


	public WebElement getOrgName() {
		return orgName;
	}



	
	
	

}
