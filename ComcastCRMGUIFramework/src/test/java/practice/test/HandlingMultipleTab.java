package practice.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class HandlingMultipleTab {
	
	@Test
	public void FetchURL() throws Throwable {
		WebDriver driver = new ChromeDriver();
		ExcelUtility eLib = new ExcelUtility();
		for(int i = 1; i<5; i++) {
			
		driver.switchTo().newWindow(WindowType.TAB);
		String URL = eLib.getDataFromExcel("url", i, 1);
		driver.get(URL);
		
	}
	}
}
