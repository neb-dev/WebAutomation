package encompass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Quote {

	public static void main(String[] args) throws Exception {
		// set webdriver to chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\josh\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		// init webdriver object
		WebDriver driver = new ChromeDriver();
		
		// navigate to encompass agent login form
		driver.get("https://dashboard.encompassinsurance.com/pkmslogin.form");
		
		// 3 second timeout for page load
		Thread.sleep(3000);
		
		// select username input field
		driver.findElement(By.name("username")).sendKeys("a1177529");
		
		// select password input field
		driver.findElement(By.name("password")).sendKeys("4sD3Y14bK0MJ");
		
		// select submit button
		// driver.findElement(By.name("submitBtn")).click();
		
		Thread.sleep(3000);
		
		driver.quit();
	}

}
