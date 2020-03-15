package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOwPO {
	
	public WebDriver driver;

	// Initializing elements and driver.
	public CheckoutOwPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Web Elements
	private @FindBy (xpath = "//*[text()='FINISH']") WebElement btnFinish;
	private @FindBy (css = "div.subheader") WebElement owHeader;
	private @FindBy (css = "h2.complete-header") WebElement orderMessage;
	
	// Methods
	public WebElement getBtnFinish() {
		return btnFinish;
	}
	
	public WebElement getOverviewHeader() {
		return owHeader;
	}
	
	public WebElement getOrderMessage() {
		return orderMessage;
	}
	
	//Get products added on the overview section
	public String[] getAddedProducts(int totalProducts) {
		List<WebElement> product = driver.findElements(By.cssSelector("div.inventory_item_name"));;
		String[] overviewProducts = new String[totalProducts];
		for (int i = 0; i < totalProducts; i++) {
			overviewProducts[i] = product.get(i).getText();	
		}
		return overviewProducts;	
	}
}
