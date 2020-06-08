package encompass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Quote {
	
	static void connectToDataSource() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms", "cms_user", "userpass");
			System.out.println("Connected to MySQL Database...");
			System.out.println(conn.isValid(1000));
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		// connect to mysql database
		connectToDataSource();
		
		// set webdriver to chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\josh\\Downloads\\chromedriver_win32\\chromedriver.exe");
		// init webdriver object - pass options argument
		WebDriver driver = new ChromeDriver();
		
		// navigate to encompass agent login form
		driver.get("https://dashboard.encompassinsurance.com/pkmslogin.form");
		// 3 second timeout for page load
		Thread.sleep(3000);
		
		/* --------------- ENCOMPASS AGENT LOGIN --------------- */
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
		
		driver.findElement(By.name("username")).sendKeys("a1177529");
		driver.findElement(By.name("password")).sendKeys("fzP94W5K0z");
		driver.findElement(By.name("submitBtn")).click();
		Thread.sleep(5000);
		
		// stores parent window handle
		String parentWindow = driver.getWindowHandle();
		// will store popup window handle
		String popupWindow = null;
		
		// click start quote button
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
		
		/* --------------- INITIAL START FORM --------------- */
		// click state input dropdown
		driver.findElement(By.id("FVNETXPolicyInformationStateMeaning2_img")).click();
		// 1 second timeout for input update
		Thread.sleep(1000);
		// select state value in dropdown
		driver.findElement(By.id("spPopupRow2")).click();
		// 1 second timeout for form update
		Thread.sleep(1000);
		
		// click business type input dropdown
		driver.findElement(By.id("FVNETXPolicyInformationCalcLineOfBusiness_img")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("spPopupRow2")).click();
		Thread.sleep(1000);
		
		// click number of residents input dropdown
		driver.findElement(By.id("FVNETXPolicyInformationNumDrivers_img")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("spPopupRow0")).click();
		Thread.sleep(1000);
		
		// click start button
		driver.findElement(By.id("ACFRPreLaunchStart")).click();
		// 3 second timeout for page load
		Thread.sleep(5000);
		
		/* --------------- FIRST PAGE OF QUOTE FORM --------------- */
		// close dialog box
		driver.findElement(By.id("_fcraCloseButton")).click();
		
		// select insured first name input
		driver.findElement(By.id("FVNETXInsuredFirstName_input")).sendKeys("Laura");
		// select insured last name input
		driver.findElement(By.id("FVNETXInsuredLastName_input")).sendKeys("Weidert");
		// select dob input
		driver.findElement(By.id("FVNETXInsuredCalcDOB_input")).sendKeys("07/25/1961");
		// select email input
		driver.findElement(By.id("FVNETXAddressEmailAddress_input")).sendKeys("weidertl@aetna.com");
		// select home phone input
		driver.findElement(By.id("FVNETXAddressHomePhoneNumber_input")).sendKeys("(402) 630-3383");
		// select address input
		driver.findElement(By.id("FVNETXAddressAddressLine1_input")).sendKeys("15215  Valley St");
		// select city input
		driver.findElement(By.id("FVNETXAddressCity_input")).sendKeys("OMAHA");
		// click state input dropdown
		driver.findElement(By.id("FVNETXAddressState_img")).click();
		// 1 second timeout for input update
		Thread.sleep(1000);
		// select state value in dropdown
		driver.findElement(By.id("spPopupRow30")).click();
		// select zip code input
		driver.findElement(By.id("FVNETXAddressPostalCode_input")).sendKeys("68144");
		// click county input dropdown
		driver.findElement(By.id("FVNETXAddressCountyMeaning_img")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("spPopupRow27")).click();
		
		// click continue button
		driver.findElement(By.id("_PolicyContinue")).click();
		Thread.sleep(5000);
		
		/* --------------- SECOND PAGE OF QUOTE FORM --------------- */
		// click gender input dropdown
		driver.findElement(By.id("FTNETXDrivergGenderMeaning_1_img")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("spPopupRow0")).click();
		Thread.sleep(1000);
		// click marital status input dropdown
		driver.findElement(By.id("FTNETXDriverCalcMaritalStatusMeaning1_1_img")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("spPopupRow2")).click();
		Thread.sleep(1000);
		
		// click continue button
		driver.findElement(By.id("_ResidentDriverContinue")).click();
		Thread.sleep(5000);
		
		/* --------------- THIRD PAGE OF QUOTE FORM --------------- */
		// click continue button
		driver.findElement(By.id("_VehicleViolationsContinue")).click();
		Thread.sleep(2000);
		
		/* --------------- FOURTH PAGE OF QUOTE FORM --------------- */
		// click carrier data radio input
		driver.findElement(By.id("FVCCDBCalcRateUsingMeaning_Prior Carrier Data")).click();
		Thread.sleep(1000);
		
		// select years as auto customer - get text value from length with prior carrier
		driver.findElement(By.id("FVCCDBCalcAgentAutoYearAsCustomer_input")).sendKeys(driver.findElement(By.id("_CCDBYearsWithPrior_ro")).getText());
		
		// select number of vehicles
		driver.findElement(By.id("FVNETXPolicyInformationEGroupVehicleCount_input")).sendKeys("1");
		
		// click continue button
		driver.findElement(By.id("_VehicleUndrwrtngContinue")).click();
		Thread.sleep(5000);
		
		/* --------------- FIFTH PAGE OF QUOTE FORM --------------- */
		// stores parent window handle
		parentWindow = driver.getWindowHandle();
		// will store popup window handle
		popupWindow = null;
		
		// click calculate in MSB button
		driver.findElement(By.id("_FVENCXPropertyCalcMSBButton")).click();
		// 6 second timeout for page load
		Thread.sleep(6000);
		
		// stores all window handles
		handles = driver.getWindowHandles();
		// cursor to iterate through handles collection
		iterator = handles.iterator();
		
		// loop through iterator
		while(iterator.hasNext()) {
			popupWindow = iterator.next();
		}
		
		// focus popup window
		driver.switchTo().window(popupWindow);
		
		// click finish button
		driver.findElement(By.className("save-or-discard-valuation")).click();
		Thread.sleep(3000);
		
		// click save button
		driver.findElement(By.id("SaveValuationButton")).click();
		// switch back to parent window
		driver.switchTo().window(parentWindow);
		Thread.sleep(8000);
		
		// select dwelling protection input - get text value from reconstruction cost
		driver.findElement(By.id("FVENCXPropertyCalcResidenceReplacementValue_input")).sendKeys(driver.findElement(By.id("_FVENCXPropertyCalcMSBButton")).getText());
		
		// click occupancy input dropdown
		driver.findElement(By.id("FVENCXPropertyENCXOccupancyCodeMeaning_img")).click();
		Thread.sleep(1000);
		// select owner value in dropdown
		driver.findElement(By.id("spPopupRow0")).click();
		
		// select property purchase date
		driver.findElement(By.id("FVENCXPropertyPurchaseDate_input")).sendKeys("09/06/2013");
		
		// select if home is rented out
		driver.findElement(By.id("FVENCXPropertyRentedToOthersFlag_img")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("spPopupRow1")).click();
		
		// select sump pump
		driver.findElement(By.id("FVENCXPropertySumpPumpCountMeaning_img")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("spPopupRow0")).click();
		
		// select roof year
		driver.findElement(By.id("FVENCXPropertyRoofImprovementYearNumber_input")).sendKeys("2019");
		
		// click continue button
		driver.findElement(By.id("_PropertyInfoContinue")).click();
		Thread.sleep(5000);
		
		/* --------------- SIXTH PAGE OF QUOTE FORM --------------- */
		// click continue button
		driver.findElement(By.id("_PropertyLossContinue")).click();
		Thread.sleep(5000);
		
		/* --------------- SEVENTH PAGE OF QUOTE FORM --------------- */
		// click following conditions input dropdown
		driver.findElement(By.id("FVENCXPropertyUnderwritngCalcPropertyUnderwriteMeets_img")).click();
		Thread.sleep(1000);
		// select conditions value in dropdown
		driver.findElement(By.id("spPopupRow1")).click();
		
		// click prior insurance input dropdown
		driver.findElement(By.id("FVNETXPolicyInformationENCXPropertyPrirInsrncTypMnng_img")).click();
		Thread.sleep(1000);
		// select property insurance value in dropdown
		driver.findElement(By.id("spPopupRow2")).click();
		Thread.sleep(1000);
		
		// click continue button
		driver.findElement(By.id("_PropUndrwrtngContinue")).click();
		Thread.sleep(5000);
		
		/* --------------- LAST PAGE OF QUOTE FORM --------------- */
		// select wind/hail coverage
		driver.findElement(By.id("FTNETXCalcErrorGridInput_2_img")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("spPopupRow12")).click();
		
		// click continue inside modal window
		driver.findElement(By.id("_ErrorModalContinue")).click();
		Thread.sleep(5000);
		
		// click close on binding alert
		driver.findElement(By.id("_CloseDoNotBind")).click();
		Thread.sleep(1000);
		
		// store quote value
		String quoteValue = driver.findElement(By.className("wf_premiumView")).getText();
		// print quote value to console
		System.out.println(quoteValue);
		
		// Thread.sleep(5000);
		// driver.quit();
	}
}
