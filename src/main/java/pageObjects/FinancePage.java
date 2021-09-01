package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class FinancePage {

	WebDriver driver;

	public FinancePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@id='Nav-0-DesktopNav']/div/div[3]/div/nav/ul/li[2]/div")
	private WebElement business_data_option;

	@FindBy(how = How.LINK_TEXT, using = "Calendar")
	private WebElement calendar_link;

	@FindBy(how = How.LINK_TEXT, using = "Finance Home")
	private WebElement finance_home;

	@FindBy(how = How.XPATH, using = "//div[@id='fin-cal-events']/div[2]/ul/li[6]")
	private WebElement calendar_date_wise;

	public void choose_calendar_link_from_businessData() throws InterruptedException {
		finance_home.click();
		Actions builder = new Actions(driver);
		builder.moveToElement(business_data_option).perform();
		Thread.sleep(10000);
		calendar_link.click();

	}
}
