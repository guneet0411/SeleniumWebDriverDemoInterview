package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CalendarPage {
	WebDriver driver;

	public CalendarPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@id='fin-cal-events']/div[2]/ul/li[6]")
	private WebElement calendar_date_wise;

	public void get_values_from_calendar() {
		// Assert.assertTrue(calendar_date_wise.isDisplayed());
		String calendar_by_date = "//div[@id='fin-cal-events']/div[2]/ul/li[6]/";
		WebElement calenderDate = driver.findElement(By.xpath(calendar_by_date + "div"));
		String date = calenderDate.getText();
		System.out.println("date...." + date);

		for (int i = 1; i < 5; i++) {
			String calenderList = driver.findElement(By.xpath(calendar_by_date + "a[" + i + "]")).getText();
			System.out.println("calenderListValue" + i + ": " + calenderList);

		}
	}
}
