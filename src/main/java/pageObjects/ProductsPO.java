package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProductsPO {
	public WebDriver driver;

	public ProductsPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Web Elements
	private @FindBy(xpath = "//*[@class='product_label']") WebElement productsLabel;
	private @FindBy(xpath = "//*[text()='Open Menu']") WebElement btnOpenMenu;
	private @FindBy(id = "logout_sidebar_link") WebElement btnLogout;
	private @FindBy(xpath = "//*[@href='./cart.html']") WebElement shoppingCart;
	private @FindBy(xpath = "//*[text()='Your Cart']") WebElement cartHeader;
	private @FindBy(css = "span.fa-layers-counter.shopping_cart_badge") WebElement cartBadge;

	// Add products method
	public String[] AddProductsToCart(String[] products) {
		int index = 0;
		int cont = 0;
		String[] addedProducts = new String[products.length];
		List<WebElement> table = driver.findElements(By.cssSelector("div.inventory_item_name"));
		for (int i = 0; i < products.length; i++) {
			for (int j = 0; j < table.size(); j++) {
				if (table.get(j).getText().contains(products[i])) {
					index = j + 1;
					addedProducts[cont] = table.get(j).getText();
					cont++;
					table.get(j).findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[" + index + "]/div[3]/button")).click();
					break;
				}
			}
		}
		Assert.assertTrue(getCartBadge().getText().equals(String.valueOf(products.length)));
		return addedProducts;
	}

	// Getters
	public WebElement getProductsLabel() {
		return productsLabel;
	}

	public WebElement getBtnOpenMenu() {
		return btnOpenMenu;
	}

	public WebElement getBtnLogout() {
		return btnLogout;
	}

	public WebElement getShoppingCart() {
		return shoppingCart;
	}

	public WebElement getCartHeader() {
		return cartHeader;
	}

	public WebElement getCartBadge() {
		return cartBadge;
	}
}
