package practice.test;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {
	
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName, String productname) throws InterruptedException {
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.amazon.in/");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName, Keys.ENTER);
String x = "//span[text()='"+productname+"']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a/span/span[2]";
		String price= driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		Thread.sleep(1000);
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getData() throws Throwable{
		//ExcelUtility eLib = new ExcelUtility();
		//int rowCount= eLib.getRowCount("product");
		FileInputStream fis1 = new FileInputStream("C:\\Users\\91790\\OneDrive\\Documents\\testdatacrm.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("product");
		//Row row = sh.getRow(2);
		int rowCount=sh.getLastRowNum();
		Object[][] objArr = new Object[rowCount][2];
		
		for(int i=0; i<rowCount; i++) {
			Row row = sh.getRow(i+1);
		objArr[i][0] = row.getCell(0).getStringCellValue();//eLib.getDataFromExcel("product", i+1, 0);
		objArr[i][1] = row.getCell(1).getStringCellValue();//eLib.getDataFromExcel("product", i+1, 1);
		}
		
		return objArr;
	
	}
}
