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
	// form variables
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
			
			// loop through result and set values
			while(result.next()) {
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
	
	static void insertQuote(String quote) {
		try {
			// connect to mysql database 
			// database name: cms
			// database username: cms_user
			// database password: userpass
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms", "cms_user", "userpass");
			System.out.println("Connected to MySQL Database...");
			System.out.println(conn.isValid(1000));
			
			// create statement object to send to sql database
			Statement stmt = conn.createStatement();
			
			// prepare sql insert statement
			String sql = "update encompass_quote set initial_quote = '" + quote + "' where id = " + id;
			System.out.println(sql);
			// execute sql statement
			stmt.executeUpdate(sql);
			
			System.out.println("Insert successful");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		connectToDataSource();
		
		// set webdriver to chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\josh\\Downloads\\chromedriver_win32\\chromedriver.exe");
		// init webdriver object - pass options argument
		WebDriver driver = new ChromeDriver();
		
		// opens encompass agent login form
		driver.get("https://dashboard.encompassinsurance.com/pkmslogin.form");
		// wait for page load
		Thread.sleep(3000);
		
		/* 
		 * --------------- ENCOMPASS AGENT LOGIN --------------- 
		 * logs into encompass agent
		 * logs out of pkms screen (possibly due to anonymous user chrome session)
		 * logs back into encompass agent
		 * stores parent/popup window handles
		 * starts quote
		 * sets focus to popup window
		*/
		// sends username
		driver.findElement(By.name("username")).sendKeys("a1177529");
		// sends password
		driver.findElement(By.name("password")).sendKeys("fzP94W5K0z");
		// clicks submit
		driver.findElement(By.name("submitBtn")).click();
		// wait for page load
		Thread.sleep(3000);
		
		// log out of pkms
		driver.findElement(By.cssSelector("a[href*='pkmslogout']")).click();
		// wait for page load
		Thread.sleep(3000);
		
		// repeat login
		driver.findElement(By.name("username")).sendKeys("a1177529");
		driver.findElement(By.name("password")).sendKeys("fzP94W5K0z");
		driver.findElement(By.name("submitBtn")).click();
		// wait for page load
		Thread.sleep(5000);
		
		// stores parent window handle
		String parentWindow = driver.getWindowHandle();
		// init popup window handle
		String popupWindow = null;
		
		// click start quote button
		driver.findElement(By.name("startQuote")).click();
		// wait for page load
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
		
		/* 
		 * --------------- INITIAL QUOTE FORM ---------------
		 * select state (Nebraska)
		 * select business type (Homeowner)
		 * select number of residents (1)
		*/
		// click state dropdown
		driver.findElement(By.id("FVNETXPolicyInformationStateMeaning2_img")).click();
		Thread.sleep(1000);
		// select value (Nebraska)
		driver.findElement(By.id("spPopupRow2")).click();
		Thread.sleep(1000);
		
		// click business type dropdown
		driver.findElement(By.id("FVNETXPolicyInformationCalcLineOfBusiness_img")).click();
		Thread.sleep(1000);
		// select value (Homeowner)
		driver.findElement(By.id("spPopupRow2")).click();
		Thread.sleep(1000);
		
		// click residents dropdown
		driver.findElement(By.id("FVNETXPolicyInformationNumDrivers_img")).click();
		Thread.sleep(1000);
		// select value (1)
		driver.findElement(By.id("spPopupRow0")).click();
		Thread.sleep(1000);
		
		// click start button
		driver.findElement(By.id("ACFRPreLaunchStart")).click();
		Thread.sleep(5000);
		
		/* 
		 * --------------- PAGE 1 QUOTE FORM ---------------
		 * sends first/last name,
		 * dob,
		 * email, 
		 * phone,
		 * address,
		 * city,
		 * state,
		 * zip, 
		 * county
		*/
		// close dialog box
		driver.findElement(By.id("_fcraCloseButton")).click();
		
		// sends first name
		driver.findElement(By.id("FVNETXInsuredFirstName_input")).sendKeys(first_name);
		
		// sends last name
		driver.findElement(By.id("FVNETXInsuredLastName_input")).sendKeys(last_name);
		
		// sends dob
		driver.findElement(By.id("FVNETXInsuredCalcDOB_input")).sendKeys(date_of_birth);
		
		// sends email
		driver.findElement(By.id("FVNETXAddressEmailAddress_input")).sendKeys(email);
		
		// sends phone
		driver.findElement(By.id("FVNETXAddressHomePhoneNumber_input")).sendKeys(phone);
		
		// sends address
		driver.findElement(By.id("FVNETXAddressAddressLine1_input")).sendKeys(address);
		
		// sends city
		driver.findElement(By.id("FVNETXAddressCity_input")).sendKeys(city);
		
		// click state dropdown
		driver.findElement(By.id("FVNETXAddressState_img")).click();
		Thread.sleep(1000);
		// select value (Nebraska)
		driver.findElement(By.id("spPopupRow30")).click();
		
		// sends zip
		driver.findElement(By.id("FVNETXAddressPostalCode_input")).sendKeys(zip);
		
		// click county dropdown
		driver.findElement(By.id("FVNETXAddressCountyMeaning_img")).click(); 
		Thread.sleep(1000);
		// select value by inner text value
		driver.findElement(By.xpath("//td[text()='" + county.toUpperCase() + "']")).click();
		
		// click continue button
		driver.findElement(By.id("_PolicyContinue")).click();
		Thread.sleep(5000);
		
		/* 
		 * --------------- SECOND PAGE OF QUOTE FORM ---------------
		 * sends gender,
		 * marital status
		*/
		// click gender dropdown
		driver.findElement(By.id("FTNETXDrivergGenderMeaning_1_img")).click();
		Thread.sleep(1000);
		// select value by inner text value
		driver.findElement(By.xpath("//td[text()='" + gender + "']")).click();
		Thread.sleep(1000);
		
		// click marital status dropdown
		driver.findElement(By.id("FTNETXDriverCalcMaritalStatusMeaning1_1_img")).click();
		Thread.sleep(1000);
		// select value by inner text value
		driver.findElement(By.xpath("//td[text()='" + marital_status + "']")).click();
		Thread.sleep(1000);
		
		// click continue button
		driver.findElement(By.id("_ResidentDriverContinue")).click();
		Thread.sleep(5000);
		
		/* --------------- THIRD PAGE OF QUOTE FORM --------------- */
		// click continue button
		driver.findElement(By.id("_VehicleViolationsContinue")).click();
		Thread.sleep(2000);
		
		/* 
		 * --------------- FOURTH PAGE OF QUOTE FORM --------------- 
		 * retrieves prior auto carrier data
		 * sends # of years with carrier
		 * sends # of vehicles
		*/
		// retrieve prior carrier data
		driver.findElement(By.id("FVCCDBCalcRateUsingMeaning_Prior Carrier Data")).click();
		Thread.sleep(1000);
		
		// sends # of years with prior carrier
		driver.findElement(By.id("FVCCDBCalcAgentAutoYearAsCustomer_input")).sendKeys(driver.findElement(By.id("_CCDBYearsWithPrior_ro")).getText());
		
		// sends # of vehicles
		driver.findElement(By.id("FVNETXPolicyInformationEGroupVehicleCount_input")).sendKeys("1");
		
		// click continue button
		driver.findElement(By.id("_VehicleUndrwrtngContinue")).click();
		Thread.sleep(5000);
		
		/* 
		 * --------------- FIFTH PAGE OF QUOTE FORM --------------- 
		 * stores parent/popup window handle
		 * calculates reconstruction cost
		 * sends calculation to dwelling protection,
		 * occupancy type,
		 * property purchase date,
		 * if property is rented out,
		 * # of sump pumps,
		 * year of roof installment
		*/
		// stores parent window handle
		parentWindow = driver.getWindowHandle();
		// init popup window handle
		popupWindow = null;
		
		// click calculate in MSB button
		driver.findElement(By.id("_FVENCXPropertyCalcMSBButton")).click();
		Thread.sleep(7000);
		
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
		
		// focus parent window
		driver.switchTo().window(parentWindow);
		Thread.sleep(8000);
		
		// select dwelling protection - send value from calculation
		driver.findElement(By.id("FVENCXPropertyCalcResidenceReplacementValue_input")).sendKeys(driver.findElement(By.id("_FVENCXPropertyCalcMSBButton")).getText());
		
		// click occupancy dropdown
		driver.findElement(By.id("FVENCXPropertyENCXOccupancyCodeMeaning_img")).click();
		Thread.sleep(1000);
		// select value (Owner)
		driver.findElement(By.id("spPopupRow0")).click();
		
		// sends property purchase date
		driver.findElement(By.id("FVENCXPropertyPurchaseDate_input")).sendKeys(date_of_purchase);
		
		// click isRented? dropdown
		driver.findElement(By.id("FVENCXPropertyRentedToOthersFlag_img")).click();
		Thread.sleep(1000);
		// select value (No)
		driver.findElement(By.id("spPopupRow1")).click();
		
		// click # of sump pumps dropdown
		driver.findElement(By.id("FVENCXPropertySumpPumpCountMeaning_img")).click();
		Thread.sleep(1000);
		// select value by inner text value
		driver.findElement(By.xpath("//td[text()='" + sump_pumps + "']")).click();
		
		// sends roof installment year
		driver.findElement(By.id("FVENCXPropertyRoofImprovementYearNumber_input")).sendKeys(roof_year);
		
		// click continue button
		driver.findElement(By.id("_PropertyInfoContinue")).click();
		Thread.sleep(5000);
		
		/* --------------- SIXTH PAGE OF QUOTE FORM --------------- */
		// click continue button
		driver.findElement(By.id("_PropertyLossContinue")).click();
		Thread.sleep(5000);
		
		/* 
		 * --------------- SEVENTH PAGE OF QUOTE FORM --------------- 
		 * sends conditions?,
		 * prior insurance
		*/
		// click following conditions dropdown
		driver.findElement(By.id("FVENCXPropertyUnderwritngCalcPropertyUnderwriteMeets_img")).click();
		Thread.sleep(1000);
		// select value by inner text value
		driver.findElement(By.xpath("//td[text()='" + conditions + "']")).click();
		
		// click prior insurance input dropdown
		driver.findElement(By.id("FVNETXPolicyInformationENCXPropertyPrirInsrncTypMnng_img")).click();
		Thread.sleep(1000);
		// select value in dropdown (no prior insurance)
		driver.findElement(By.id("spPopupRow2")).click();
		Thread.sleep(1000);
		
		// click continue button
		driver.findElement(By.id("_PropUndrwrtngContinue")).click();
		Thread.sleep(5000);
		
		/* 
		 * --------------- LAST PAGE OF QUOTE FORM --------------- 
		 * sends wind/hail coverage amount,
		 * closes modal,
		 * close binding alert modal,
		 * store quote value as String,
		 * sends value to insertQuote method
		 * driver waits five seconds
		 * driver quits
		*/
		// send wind/hail coverage
		driver.findElement(By.id("FTNETXCalcErrorGridInput_2_img")).click();
		Thread.sleep(1000);
		// select value (no coverage)
		driver.findElement(By.id("spPopupRow12")).click();
		
		// click continue inside modal
		driver.findElement(By.id("_ErrorModalContinue")).click();
		Thread.sleep(7000);
		
		// click close on binding alert modal
		driver.findElement(By.id("_CloseDoNotBind")).click();
		Thread.sleep(7000);
		
		// store quote value
		String quoteValue = driver.findElement(By.className("wf_premiumView")).getText();
		// print quote value to console
		System.out.println(quoteValue);
		
		insertQuote(quoteValue);
		
		// Thread.sleep(5000);
		// driver.quit();
	}
}

/*
 * --------------- ADDITONAL COMMENTS ---------------
 * - rather than using sleep, the program could be further optimized using implicit, explicit, and fluent wait commands
 * - form values needing greater flexibility, such as:
 * 		state
 * 		# of residents
 * 		# of vehicles
 * 		if property is rented
 * 		if property is in gated community (see on property info. form page)
 * 		prior insurance
 * 		adjusting coverage amounts (see last form page)
 * - program needs handling for users with vehicle violations (see form page #3 above) & property loss (see form page #6 above)
 * - program still needs to be connected to the WeCare database
 * - program needs logic to determine which table row to execute (i.e. WHERE initial_quote = null)
 * - see the php bootstrap form for the mysql table structure
 * 
 * --------------- SELENIUM WEBDRIVER, CHROMEDRIVER, JDBC JAR ---------------
 * - Selenium WebDriver: https://www.selenium.dev/downloads/ (scroll down to WebDriver section)
 * - ChromeDriver: https://sites.google.com/a/chromium.org/chromedriver/
 * - JDBC (Java Database Connectivity): https://dev.mysql.com/downloads/connector/j/ (select platform independent)
 */
