package wizeline;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.CartPO;
import pageObjects.CheckoutInfoPO;
import pageObjects.CheckoutOwPO;
import pageObjects.ProductsPO;
import resources.base;

public class ProductsValidation extends base {
	ProductsPO ppo;
	CartPO cpo;
	CheckoutInfoPO cipo;
	CheckoutOwPO cop;
	
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		StandardLogin(driver);
	}
	
	@Test
	@Parameters({"name","lastName","zipcode"})
	public void CartPageValidation(String name, String lastName, String zipcode) {
		//Creating objects for repositories
		ppo = new ProductsPO(driver);
		cpo = new CartPO(driver);
		cipo = new CheckoutInfoPO(driver);
		cop = new CheckoutOwPO(driver);
		
		//Login to web page
		StandardLogin(driver);
		
		// Enter unique word of product desired to be added (Backpack, Bike, Bolt, Fleece, Onesie, Red)
		String[] list = { "Backpack", "Onesie" };
		String[] productsAdded = ppo.AddProductsToCart(list);
				
		//Validate error message is displayed.
		ppo.getShoppingCart().click();
		cpo.getBtnCheckout().click();
		cipo.getFirstName().sendKeys(name);
		cipo.getLastName().sendKeys(lastName);
		cipo.getBtnContinue().click();			
		Assert.assertTrue(cipo.getErrorMessage().isDisplayed());		
		
		//Validate user information is successfully filled out
		cipo.getPostalCode().sendKeys(zipcode);
		cipo.getBtnContinue().click();
		Assert.assertTrue(cop.getOverviewHeader().isDisplayed());
		
		//Validate user overview products match with products added to cart.
		String[] finalProducts = cop.getAddedProducts(list.length);
		Assert.assertEquals(productsAdded, finalProducts);;
		
		//Complete a purchase
		cop.getBtnFinish().click();
		Assert.assertEquals(cop.getOrderMessage().getText(), "THANK YOU FOR YOUR ORDER");
		
	}
	
	@AfterTest
	public void teardown() {
		driver.close();
		driver = null;
	}
}
