package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public LoginPage(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using = "login-username")
	private WebElement userName;

	@FindBy(how = How.ID, using = "login-passwd")
	private WebElement userPassword;

	@FindBy(how = How.ID, using = "login-signin")
	private WebElement signIn;

	public void enterUserName(String name) {
		userName.sendKeys(name);
	}

	public void enterPassword(String password) {
		userPassword.sendKeys(password);

	}

	public void signInButton() {
		signIn.click();
		;
	}

	public void loginYahooPage(String username, String password) {
		enterUserName(username);
		signInButton();
		enterPassword(password);
		signInButton();
	}

}
