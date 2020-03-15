package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPO {
	public WebDriver driver;

	// Initializing elements and driver.
	public LoginPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElements
	private @FindBy(css = "#user-name") WebElement username;
	private @FindBy(id = "password") WebElement password;
	private @FindBy(xpath = "//*[@value='LOGIN']") WebElement btnLogin;
	private @FindBy(xpath = "//*[@class='error-button']") WebElement errorMessage;

	// Get Methods
	public WebElement getUsername() {
		return username;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getBtnLogin() {
		return btnLogin;
	}

	public WebElement getErrorMessage() {
		return errorMessage;
	}

}
