package encompass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Quote {
	static String id = "";
	static String first_name = ""; 
	static String last_name = "";
	static String date_of_birth = "";
	static String email = "";
	static String phone = "";
	static String gender = "";
	static String marital_status = ""; 
	static String address = "";
	static String city = "";
	static String state = "";
	static String zip = "";
	static String county = "";
	static String residents = "";
	static String vehicles = "";
	static String date_of_purchase = "";
	static String sump_pumps = "";
	static String roof_year = "";
	static String conditions = "";
	
	static void connectToDataSource() {
		try {
			// connect to mysql database
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms", "cms_user", "userpass");
			System.out.println("Connected to MySQL Database...");
			System.out.println(conn.isValid(1000));
			
			// create statement object to send to sql database
			Statement stmt = conn.createStatement();
			
			// execute query - store result
			ResultSet result = stmt.executeQuery("select * from encompass_quote");
			
			while(result.next()) {
				// System.out.println(result.getInt("id") + ", " + result.getString("title"));
				id = result.getString("id");
				first_name = result.getString("first_name");
				last_name = result.getString("last_name");
				date_of_birth = result.getString("dob");
				email = result.getString("email");
				phone = result.getString("phone");
				gender = result.getString("gender");
				marital_status = result.getString("marital_status");
				address = result.getString("address");
				city = result.getString("city");
				state = result.getString("state");
				zip = result.getString("zip");
				county = result.getString("county");
				residents = result.getString("residents");
				vehicles = result.getString("vehicles");
				date_of_purchase = result.getString("dop");
				sump_pumps = result.getString("sump");
				roof_year = result.getString("roof_year");
				conditions = result.getString("conditions");
				System.out.println("id: " + id + 
						"\nname: " + first_name + " " + last_name + 
						"\ndob: " + date_of_birth + 
						"\nemail: " + email + 
						"\nphone: " + phone + 
						"\ngender: " + gender +
						"\nmarital status: " + marital_status +
						"\naddress: " + address + " " + city + " " + state + " " + zip + " " + county +
						"\nresidents: " + residents +
						"\nvehicles: " + vehicles +
						"\ndate of purchase: " + date_of_purchase +
						"\nsump pumps: " + sump_pumps +
						"\nroof year: " + roof_year +
						"\nconditions: " + conditions);
			}
			
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		connectToDataSource();
		
		System.out.println("name: " + first_name + " " + last_name);
		
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
		driver.findElement(By.id("FVNETXInsuredFirstName_input")).sendKeys(first_name);
		// select insured last name input
		driver.findElement(By.id("FVNETXInsuredLastName_input")).sendKeys(last_name);
		// select dob input
		driver.findElement(By.id("FVNETXInsuredCalcDOB_input")).sendKeys(date_of_birth);
		// select email input
		driver.findElement(By.id("FVNETXAddressEmailAddress_input")).sendKeys(email);
		// select home phone input
		driver.findElement(By.id("FVNETXAddressHomePhoneNumber_input")).sendKeys(phone);
		// select address input
		driver.findElement(By.id("FVNETXAddressAddressLine1_input")).sendKeys(address);
		// select city input
		driver.findElement(By.id("FVNETXAddressCity_input")).sendKeys(city);
		// click state input dropdown
		driver.findElement(By.id("FVNETXAddressState_img")).click();
		// 1 second timeout for input update
		Thread.sleep(1000);
		// select state value in dropdown
		driver.findElement(By.id("spPopupRow30")).click();
		// select zip code input
		driver.findElement(By.id("FVNETXAddressPostalCode_input")).sendKeys(zip);
		// click county input dropdown
		driver.findElement(By.id("FVNETXAddressCountyMeaning_img")).click(); /* ----------------- county ----------------- */ 
		Thread.sleep(1000);
		driver.findElement(By.id("spPopupRow27")).click();
		
		// click continue button
		driver.findElement(By.id("_PolicyContinue")).click();
		Thread.sleep(5000);
		
		/* --------------- SECOND PAGE OF QUOTE FORM --------------- */
		// click gender input dropdown
		driver.findElement(By.id("FTNETXDrivergGenderMeaning_1_img")).click(); /* ----------------- gender ----------------- */ 
		Thread.sleep(1000);
		driver.findElement(By.id("spPopupRow0")).click();
		Thread.sleep(1000);
		// click marital status input dropdown
		driver.findElement(By.id("FTNETXDriverCalcMaritalStatusMeaning1_1_img")).click(); /* ----------------- marital status ----------------- */ 
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
		driver.findElement(By.id("FVENCXPropertyPurchaseDate_input")).sendKeys(date_of_purchase);
		
		// select if home is rented out
		driver.findElement(By.id("FVENCXPropertyRentedToOthersFlag_img")).click(); /* ----------------- is rented? ----------------- */ 
		Thread.sleep(1000);
		driver.findElement(By.id("spPopupRow1")).click();
		
		// select sump pump
		driver.findElement(By.id("FVENCXPropertySumpPumpCountMeaning_img")).click(); /* ----------------- number of sump pumps ----------------- */ 
		Thread.sleep(1000);
		driver.findElement(By.id("spPopupRow0")).click();
		
		// select roof year
		driver.findElement(By.id("FVENCXPropertyRoofImprovementYearNumber_input")).sendKeys(roof_year);
		
		// click continue button
		driver.findElement(By.id("_PropertyInfoContinue")).click();
		Thread.sleep(5000);
		
		/* --------------- SIXTH PAGE OF QUOTE FORM --------------- */
		// click continue button
		driver.findElement(By.id("_PropertyLossContinue")).click();
		Thread.sleep(5000);
		
		/* --------------- SEVENTH PAGE OF QUOTE FORM --------------- */
		// click following conditions input dropdown
		driver.findElement(By.id("FVENCXPropertyUnderwritngCalcPropertyUnderwriteMeets_img")).click(); /* ----------------- conditions ----------------- */ 
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
