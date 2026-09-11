package practiceSenario;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Sample2 {
	
	@Test
	public static void sampleTest() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("https://automationexercise.com/");
		driver.findElement(By.xpath("//i[@class='material-icons card_travel']/..")).click();
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("winter");
		driver.findElement(By.xpath("//button[@id='submit_search']")).click();
		driver.findElement(By.xpath("//i[@class='fa fa-plus-square']/..")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-default cart']")).click();
		driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();
		driver.findElement(By.xpath("//i[@class='fa fa-shopping-cart']/..")).click();
		
		WebElement item=driver.findElement(By.xpath("//a[text()='Winter Top']"));
		String productName=item.getText();
		String actualProductName="Winter Top";
		Assert.assertEquals(productName, actualProductName);
	}

}
