package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInfoPO {

	public WebDriver driver;

	// Initializing elements and driver.
	public CheckoutInfoPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Web Elements
	private @FindBy(id = "first-name") WebElement firstName;
	private @FindBy(id = "last-name") WebElement lastName;
	private @FindBy(id = "postal-code") WebElement postalCode;
	private @FindBy(xpath = "//*[text()='CANCEL']") WebElement btnCancel;
	private @FindBy(xpath = "//*[@type='submit'][@value='CONTINUE']") WebElement btnContinue;
	private @FindBy(css = "	button.error-button") WebElement errorMessage;
	

	// Methods
	public WebElement getFirstName() {
		return firstName;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getPostalCode() {
		return postalCode;
	}

	public WebElement getBtnCancel() {
		return btnCancel;
	}

	public WebElement getBtnContinue() {
		return btnContinue;
	}
	
	public WebElement getErrorMessage() {
		return errorMessage;
	}
}
