
package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic13_WebBrowser_Commands_Exercise01 {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		// open browser
		driver = new FirefoxDriver();

		// Set timeout to find element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("http://live.techpanda.org/");

	}

	
	  @Test public void TC_01_URL() {
	  
	  // div[@class='footer']//a[@title='My Account']
	  
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")
	  ).click();
	  
	  String loginPageUrl = driver.getCurrentUrl();
	  
	  Assert.assertEquals(loginPageUrl,
	  "http://live.techpanda.org/index.php/customer/account/login/");
	  
	  // div[@class='account-login']//a[@title='Create an Account']
	  
	  driver.findElement(By.
	  xpath("//div[@class='account-login']//a[@title='Create an Account']")).click(
	  );
	  
	  String registerPageUrl = driver.getCurrentUrl();
	  
	  Assert.assertEquals(registerPageUrl,
	  "http://live.techpanda.org/index.php/customer/account/create/");
	  
	  }
	  
	  @Test public void TC_02_Title() {
	  
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")
	  ).click();
	  
	  String loginPageTitle = driver.getTitle();
	  
	  Assert.assertEquals(loginPageTitle, "Customer Login");
	  
	  // div[@class='account-login']//a[@title='Create an Account']
	  
	  driver.findElement(By.
	  xpath("//div[@class='account-login']//a[@title='Create an Account']")).click(
	  );
	  
	  String registerPageTitle = driver.getTitle();
	  
	  Assert.assertEquals(registerPageTitle, "Create New Customer Account");
	  
	  }
	 

	@Test
	public void TC_03_Navigate() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// div[@class='account-login']//a[@title='Create an Account']

		driver.findElement(By.xpath("//div[@class='account-login']//a[@title='Create an Account']")).click();

		String registerPageUrl = driver.getCurrentUrl();

		Assert.assertEquals(registerPageUrl, "http://live.techpanda.org/index.php/customer/account/create/");

		driver.navigate().back();

		String loginPageUrl = driver.getCurrentUrl();

		Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");

		driver.navigate().forward();

		String registerPageTitle = driver.getTitle();

		Assert.assertEquals(registerPageTitle, "Create New Customer Account");

	}

	@Test
	public void TC_04_PageSource() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		String loginPageSource = driver.getPageSource();
		
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//div[@class='account-login']//a[@title='Create an Account']")).click();
		
		String registerPageSource = driver.getPageSource();
		
		Assert.assertTrue(registerPageSource.contains("Create an Account"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}