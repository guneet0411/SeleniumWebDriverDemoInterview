package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import Utilities.TestUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CalendarPage;
import pageObjects.FinancePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SearchPage;

public class Steps {

	WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	CalendarPage calendarPage;
	SearchPage searchPage;
	FinancePage financePage;
	TestUtil test;
	List<HashMap<String, String>> dataSet;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","/Users/guneetkaur/Documents/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String file = System.getProperty("user.dir") + "/src/test/resources/Credentials.xlsx";
		dataSet = TestUtil.readExcelDatafromFile(file, "Credentials");

	}

	@Given("Open the browser and launch the application")
	public void launchApplication() {
		homePage = new HomePage(driver);
		homePage.navigateTo_HomePage();
	}

	@When("Enter the Username and Password from excel file test-data {int}")
	public void enter_credentials_from_excel_file(Integer excelDataRow) {
		String username = dataSet.get(excelDataRow).get("username").trim();
		String password = dataSet.get(excelDataRow).get("password").trim();

		loginPage = new LoginPage(driver);
		loginPage.loginYahooPage(username, password);
	}

	@Then("User is able to login successfully")
	public void user_is_able_to_login_successfully() throws InterruptedException {
		System.out.println("Get home page title: " + driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Yahoo UK"));
	}

	@Given("User is on Yahoo Home Page")
	public void user_is_on_home_page() {
		launchApplication();
		enter_credentials_from_excel_file(0);

	}

	@When("User selects Finance / Market Data/ Calendar")
	public void user_selects_calendar() throws InterruptedException {
		searchPage = new SearchPage(driver);
		searchPage.clickFinanceLink();

		financePage = new FinancePage(driver);
		financePage.choose_calendar_link_from_businessData();
		
	}

	@Then("capture the values for the 27th August")
	public void capture_values() {
		calendarPage = new CalendarPage(driver);
		calendarPage.get_values_from_calendar();
		Assert.assertTrue(driver.getTitle().contains("Financial calendars"));
	}

	@After
	public void cleanUp() {
		driver.quit();

	}

}
