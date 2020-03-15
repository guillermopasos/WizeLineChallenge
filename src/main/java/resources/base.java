package resources;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.LoginPO;

public class base {
	
	//Public variables
	public WebDriver driver;
	public Properties prop;	

	public WebDriver initializeDriver() throws IOException {
		
		//Getting resources.properties file with global variables.
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//data.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		System.out.println(browserName);

		if (browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//java//resources//chromedriver.exe");
			ChromeOptions options = new ChromeOptions();			
			//Statement if you want to run  Headless.
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}

			driver = new ChromeDriver(options);

		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//src//main//java//resources//geckodriver.exe");
			driver = new FirefoxDriver();		
		} 
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	
	public void StandardLogin(WebDriver driver) {
		String url = prop.getProperty("url");
		driver.get(url);
		LoginPO lpo = new LoginPO(driver);
		lpo.getUsername().sendKeys(prop.getProperty("user"));
		lpo.getPassword().sendKeys(prop.getProperty("pass"));
		lpo.getBtnLogin().click();
	}
}
