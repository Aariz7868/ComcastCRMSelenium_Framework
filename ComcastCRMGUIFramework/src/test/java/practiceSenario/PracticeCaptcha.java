package practiceSenario;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class PracticeCaptcha {
	@Test
	public void captchaTest() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		 driver.get("https://mailservice25.com/login");
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		 driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
		 Thread.sleep(3000);
		 JavascriptExecutor js = (JavascriptExecutor) driver;

		 WebElement ele=driver.findElement(By.xpath("//span[@role='checkbox']"));
		 Actions act = new Actions(driver);
		 //act.moveToElement(ele).perform();
		 js.executeScript("arguments[0].click();", ele);
		 //WebElement ele=driver.findElement(By.xpath("//span[@role='checkbox']"));
		//ele.click();
	}

	
}
