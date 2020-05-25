package encompass;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Quote {

	public static void main(String[] args) throws Exception {
		// set webdriver to chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\josh\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		// init webdriver object - pass options argument
		WebDriver driver = new ChromeDriver();
		
		// navigate to encompass agent login form
		driver.get("https://dashboard.encompassinsurance.com/pkmslogin.form");
			
		// 3 second timeout for page load
		Thread.sleep(3000);
		
		// select username input field
		driver.findElement(By.name("username")).sendKeys("a1177529");
		
		// select password input field
		driver.findElement(By.name("password")).sendKeys("fzP94W5K0z");
		
		// select submit button
		driver.findElement(By.name("submitBtn")).click();
		
		// 3 second timeout for page load
		Thread.sleep(3000);
		
		// log out current user session
		driver.findElement(By.cssSelector("a[href*='pkmslogout']")).click();
		
		// 3 second timeout for page load
		Thread.sleep(3000);
		
		// select username input field
		driver.findElement(By.name("username")).sendKeys("a1177529");
		
		// select password input field
		driver.findElement(By.name("password")).sendKeys("fzP94W5K0z");
		
		// select submit button
		driver.findElement(By.name("submitBtn")).click();
		
		// 5 second timeout for page load
		Thread.sleep(5000);
		
		// stores parent window handle
		String parentWindow = driver.getWindowHandle();
		// will store popup window handle
		String popupWindow = null;
		
		driver.findElement(By.name("startQuote")).click();
		
		// 6 second timeout for page load
		Thread.sleep(6000);
		
		// stores all window handles
		Set<String> handles = driver.getWindowHandles();
		// cursor to iterate through handles collection
		Iterator<String> iterator = handles.iterator();
		
		// loop through iterator
		while(iterator.hasNext()) {
			popupWindow = iterator.next();
		}
		
		// focus popup window
		driver.switchTo().window(popupWindow);
		
		// select state
		driver.findElement(By.id("FVNETXPolicyInformationStateMeaning2_input")).sendKeys("Id");
		// 2 second timeout for form update
		Thread.sleep(2000);
		// tab out of input
		driver.findElement(By.id("FVNETXPolicyInformationStateMeaning2_input")).sendKeys(Keys.TAB);
		// 2 second timeout for form update
		Thread.sleep(2000);
		
		
		// select line of business
		driver.findElement(By.id("FVNETXPolicyInformationCalcLineOfBusiness_input")).sendKeys("Ho");
		// 2 second timeout for form update
		Thread.sleep(2000);
		// tab out of input
		driver.findElement(By.id("FVNETXPolicyInformationStateMeaning2_input")).sendKeys(Keys.TAB);
		// 2 second timeout for form update
		Thread.sleep(2000);
		
		// select line of business
		driver.findElement(By.id("FVNETXPolicyInformationNumDrivers_input")).sendKeys("1");
		// 2 second timeout for form update
		Thread.sleep(2000);
		// tab out of input
		driver.findElement(By.id("FVNETXPolicyInformationStateMeaning2_input")).sendKeys(Keys.TAB);
		// 2 second timeout for form update
		Thread.sleep(2000);
		
		// click start button
		driver.findElement(By.id("ACFRPreLaunchStart")).click();
		
		// 3 second timeout for page load
		Thread.sleep(3000);
		
		// close dialog element
		driver.findElement(By.id("_fcraCloseButton")).click();
		
		Thread.sleep(6000);
		
		driver.quit();
        
	}

}
