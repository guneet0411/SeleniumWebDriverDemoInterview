package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.LINK_TEXT, using = "Sign in")
	private WebElement signInLink;

	@FindBy(how = How.NAME, using = "agree")
	private WebElement agreeButton;

	public void clickSignInLink() {
		signInLink.click();
	}

	public void clickAgreeButton() {
		agreeButton.click();
	}

	public void navigateTo_HomePage() {
		driver.get("https://uk.yahoo.com/?p=us&guccounter=1");

		if (agreeButton.isDisplayed()) {
			clickAgreeButton();
			driver.get("https://uk.yahoo.com/?p=us&guccounter=1");
		}

		signInLink.click();
	}
}
