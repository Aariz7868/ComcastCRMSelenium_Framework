package practiceSenario;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SampleExercise {
	@Test
	public static void sampleTest() {
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> prefs = new HashMap<>();

		// Disable Save Address popup
		prefs.put("autofill.profile_enabled", false);

		// Disable Save Password popup
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);

		options.setExperimentalOption("prefs", prefs);

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("https://automationexercise.com/");
		driver.findElement(By.xpath("//i[@class='fa fa-lock']/..")).click();
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Aariz");
		driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("mohdaariz107@gmail.com");
		
		driver.findElement(By.xpath("//button[text()='Signup']")).click();
		driver.findElement(By.xpath("//input[@id='id_gender1']")).click();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Aariz@123");
		driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("Mohammad");
		driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys("Aariz");
		driver.findElement(By.xpath("//input[@name='address1']")).sendKeys("Noida");
		WebElement country=driver.findElement(By.xpath("//select[@name='country']"));
		Select sel = new Select(country);
		sel.selectByVisibleText("India");
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys("Uttar Pradesh");
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Noida");
		driver.findElement(By.xpath("//input[@name='zipcode']")).sendKeys("201301");
		driver.findElement(By.xpath("//input[@name='mobile_number']")).sendKeys("7905078089");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement submit= driver.findElement(By.xpath("//button[text()='Create Account']"));
	    WebElement element = wait.until(
	            ExpectedConditions.elementToBeClickable(submit));

	    try {
	        element.click();
	    } catch (ElementClickInterceptedException e) {

	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true).click();", element);
	        
	    }
		
		
		
	}

}
