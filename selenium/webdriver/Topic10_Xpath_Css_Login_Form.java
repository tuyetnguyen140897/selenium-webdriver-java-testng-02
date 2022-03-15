
package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic10_Xpath_Css_Login_Form {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email, password;

	// fields

	By txtEmail = By.id("email");

	By txtPassword = By.id("pass");

	By login_button = By.xpath("//button[@title='Login']");

	// eror message when not fill

	By error_msg_Email1 = By.id("advice-required-entry-email");

	By error_msg_Password1 = By.id("advice-required-entry-pass");

	// Error message when validate

	By error_msg_Email2 = By.id("advice-validate-email-email");

	By error_msg_Password2 = By.id("advice-validate-password-pass");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		// open browser
		driver = new FirefoxDriver();

		// Set timeout to find element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// generate random email address

		// use for TC05

//		email = "automation" + randomInt() + "@gmail.com";

		email = "automation@gmail.com";

		password = "123456";

	}

	@BeforeMethod
	// before method - antonnation run for all every begins
	public void BeforeMethod() {
		driver.get("http://live.techpanda.org");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

	}

	
	  @Test public void TC_01_Empty_Email_Password() {
	  
	  driver.findElement(By.xpath("//button[@title='Login']")).click();
	  
	  // Get and verify error message text of all fields
	  
	  Assert.assertEquals(driver.findElement(error_msg_Email1).getText(),
	  "This is a required field.");
	  
	  Assert.assertEquals(driver.findElement(error_msg_Password1).getText(),
	  "This is a required field.");
	  
	  }
	  
	  @Test public void TC_02_Invalid_Emaill() {
	  
	  // Input data into textbox
	  
	  driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
	  driver.findElement(By.id("pass")).sendKeys(password);
	  
	  driver.findElement(By.xpath("//button[@title='Login']")).click();
	  
	  // Get and verify error message text of all fields
	  
	  Assert.assertEquals(driver.findElement(error_msg_Email2).getText(),
	  "Please enter a valid email address. For example johndoe@domain.com."); }
	  
	  @Test
	  
	  public void TC_03_Password_Less_than_6_Characters() {
	  
	  // Input data into textbox
	  
	  driver.findElement(By.id("email")).sendKeys(email);
	  
	  driver.findElement(By.id("pass")).sendKeys("1234");
	  
	  driver.findElement(By.xpath("//button[@title='Login']")).click();
	  
	  // Get and verify error message text of all fields
	  
	  Assert.assertEquals(driver.findElement(error_msg_Password2).getText(),
	  "Please enter 6 or more characters without leading or trailing spaces.");
	  
	  }
	  
	  @Test
	  
	  public void TC_04_Incorrect_Password() {
	  
	  // Input data into textbox
	  
	  driver.findElement(By.id("email")).sendKeys(email);
	  
	  driver.findElement(By.id("pass")).sendKeys("Teo1234");
	  
	  driver.findElement(By.xpath("//button[@title='Login']")).click();
	  
	  // Get and verify error message text of all fields
	  
	  Assert.assertEquals(driver.findElement(By.xpath(
	  "//li[@class='error-msg']//ul//li/child::span")).getText(),
	  "Invalid login or password.");
	  
	  }
	 
	@Test

	public void TC_05_06_Create_New_TC() {

		String firstName, lastName, emailAddress, Password, CPassword, fullName;

		firstName = "Nguyen";

		lastName = "Tuyet";

		emailAddress = "tuyet" + randomInt() + "@gmail.com";

		Password = "123456";

		CPassword = "123456";

		fullName = firstName + lastName;

		// fields

		By txtFirstName = By.id("firstname");

		By txtLastName = By.id("lastname");

		By txtEmail = By.id("email_address");

		By txtPassword = By.id("password");

		By txtCPassword = By.id("confirmation");

		// Input data into textbox

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		driver.findElement(By.id("firstname")).sendKeys(firstName);

		driver.findElement(By.id("lastname")).sendKeys(lastName);

		driver.findElement(By.id("email_address")).sendKeys(emailAddress);

		driver.findElement(By.id("password")).sendKeys(Password);

		driver.findElement(By.id("confirmation")).sendKeys(CPassword);

		driver.findElement(By.xpath("//button[@title='Register']")).click();

		// Verify message register thanh cong

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"Thank you for registering with Main Website Store.");

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='dashboard']//h1")).getText(), "MY DASHBOARD");

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//p//strong")).getText(),
				"Hello," + " " + firstName + " " + lastName + "!");
		Assert.assertTrue(driver
				.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'Nguyen Tuyet')]")).isDisplayed(),
				firstName + " " + lastName);
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'Nguyen Tuyet')]//br[1]"))
						.isDisplayed(),
				emailAddress);

		driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']")).click();

		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

		// Get and verify when login successfully

		// div[@class='page-title']/h2[contains(text(),'This is demo site for')]

		Assert.assertEquals(
				driver.findElement(By.xpath("//div[@class='page-title']/h2[contains(text(),'This is demo site for')]"))
						.getText(),
				"THIS IS DEMO SITE FOR   ");
		// Start TC06

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		// Input data into textbox

		driver.findElement(By.id("email")).sendKeys(emailAddress);

		driver.findElement(By.id("pass")).sendKeys(Password);

		driver.findElement(By.xpath("//button[@title='Login']")).click();

		// Get and verify error message text of all fields

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText(), "MY DASHBOARD");
		// p[@class='hello']/strong

		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']/strong")).getText(),
				"Hello," +" "+ firstName + " " + lastName+ "!");

		Assert.assertTrue(driver
				.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'Nguyen Tuyet')]")).isDisplayed(),
				firstName + " " + lastName);
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'Nguyen Tuyet')]//br[1]"))
						.isDisplayed(),
				emailAddress);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomInt() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);
		return randomInt;
	}
}