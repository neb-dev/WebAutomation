package encompass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Quote {

	public static void main(String[] args) throws Exception {
		// set webdriver to chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\josh\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		// init webdriver object
		WebDriver driver = new ChromeDriver();
		
		// navigate to encompass
		driver.get("https://www.encompassinsurance.com/");
		
		// maximize window
		driver.manage().window().maximize();
		
		// 5 second timeout for page load
		Thread.sleep(5000);
		
		driver.close();
	}

}
