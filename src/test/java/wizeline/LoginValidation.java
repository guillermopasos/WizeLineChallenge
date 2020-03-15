package wizeline;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LoginPO;
import pageObjects.ProductsPO;
import resources.base;

public class LoginValidation extends base {
	LoginPO loginRepo;
	ProductsPO productsRepo;

	@BeforeTest 
	public void initialize() throws IOException {
		driver = initializeDriver();
	}

	@Test(dataProvider = "getData")
	public void Login(String user, String pass) {

		// Getting to the URL setup on properties
		driver.get(prop.getProperty("url"));

		// Create object for the Page object repository.
		loginRepo = new LoginPO(driver);
		productsRepo = new ProductsPO(driver);

		// Sending user and password
		loginRepo.getUsername().clear();
		loginRepo.getUsername().sendKeys(user);
		loginRepo.getPassword().clear();
		loginRepo.getPassword().sendKeys(pass);

		// Clicking on Login button
		loginRepo.getBtnLogin().click();

		try {
			if (loginRepo.getErrorMessage().isDisplayed()) {
				if (user.contains("negative")) {
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false, "User or password is invalid");
					;
				}
			}

			else {
				Assert.assertTrue(true);
				productsRepo.getBtnOpenMenu().click();
				productsRepo.getBtnLogout().click();
				Assert.assertTrue(loginRepo.getBtnLogin().isDisplayed(), "Logout was successfull");
			}

		} catch (Exception e) {
			e.getMessage();
		}
	}

	@DataProvider()
	public Object[][] getData() {
		String password = prop.getProperty("pass");
		Object[][] data = new Object[5][2];
		// 1st set of parameters
		data[0][0] = "standard_user";
		data[0][1] = password;

		// 2nd set of parameters
		data[1][0] = "locked_out_user";
		data[1][1] = password;

		// 3rd set of parameters
		data[2][0] = "problem_user";
		data[2][1] = password;

		// 4th set of parameters
		data[3][0] = "performance_glitch_user";
		data[3][1] = password;

		// 5th set of parameters
		data[4][0] = "negative_scenario";
		data[4][1] = "negative_scenario";

		// Return set of data.
		return data;
	}

	@AfterTest
	public void teardown() {
		driver.close();
		driver = null;
	}

}
