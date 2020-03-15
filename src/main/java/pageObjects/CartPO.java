package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPO {

	public WebDriver driver;

	// Initializing elements and driver.
	public CartPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//Web Elements
	private @FindBy (xpath = "//*[text()='Continue Shopping']") WebElement btnContShopping;
	private @FindBy (xpath = "//*[text()='CHECKOUT']") WebElement btnCheckout;
	
	
	//Methods
	public WebElement getBtnContShopping() {
		return btnContShopping;
	}
	
	public WebElement getBtnCheckout() {
		return btnCheckout;
	}
}
