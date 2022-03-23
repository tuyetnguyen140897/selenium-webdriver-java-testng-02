
package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic15_WebElement_Exercise2 {

	WebDriver driver;

	String projectPath = System.getProperty("user.dir");

	@BeforeClass

	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		// open browser
		driver = new FirefoxDriver();

		// Set timeout to find element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		driver.get("https://login.mailchimp.com/signup/");

	}

	@Test
	public void TC_01_SignUp() {

		driver.findElement(By.id("email")).sendKeys("tuyetnguyen@gmail.com");

		driver.findElement(By.id("new_username")).sendKeys("tuyet nguyen");

		By passwordTextbox = By.id("new_password");

		By buttonSignUp = By.id("create-account");

		By newsCheckbox = By.name("marketing_newsletter");

		// input lower : lowercase-char : One lowercase character

		driver.findElement(passwordTextbox).sendKeys("automation");

		Assert.assertTrue(driver
				.findElement((By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")))
				.isDisplayed());

		Assert.assertFalse(driver.findElement(buttonSignUp).isEnabled());

		// input upper : uppercase-char : One uppercase character

		driver.findElement(passwordTextbox).clear();

		driver.findElement(passwordTextbox).sendKeys("FC");

		Assert.assertTrue(driver
				.findElement((By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")))
				.isDisplayed());

		Assert.assertFalse(driver.findElement(buttonSignUp).isEnabled());

		/*
		 * // input numberic : number-char : One number
		 * driver.findElement(passwordTextbox).clear();
		 * 
		 * 
		 * 
		 * driver.findElement(passwordTextbox).sendKeys("1");
		 * 
		 * Assert.assertTrue(driver.findElement((By.
		 * xpath("//li[@class='number-char completed' and text()='One number']")))
		 * .isDisplayed());
		 * 
		 * Assert.assertFalse(driver.findElement(buttonSignUp).isEnabled());
		 * 
		 * 
		 * // input special : special-char : One special character : special-char
		 * 
		 * driver.findElement(passwordTextbox).clear();
		 * 
		 * driver.findElement(By.id("new_password")).sendKeys("@@");
		 * 
		 * Assert.assertTrue(driver .findElement((By.
		 * xpath("//li[@class='special-char completed' and text()='One special character']"
		 * ))) .isDisplayed());
		 * 
		 * Assert.assertFalse(driver.findElement(buttonSignUp).isEnabled());
		 * 
		 * // input 8 char : 8-char : 8 characters minimum
		 * 
		 * driver.findElement(passwordTextbox).clear();
		 * 
		 * driver.findElement(By.id("new_password")).sendKeys("webpages");
		 * 
		 * Assert.assertTrue( driver.findElement((By.
		 * xpath("//li[@class='8-char completed' and text()='8 characters minimum']")))
		 * .isDisplayed());
		 * 
		 * Assert.assertFalse(driver.findElement(buttonSignUp).isEnabled());
		 * 
		 * // 50 maximum error : plus-50 error
		 * 
		 * driver.findElement(passwordTextbox).clear();
		 * 
		 * driver.findElement(By.id("new_password")).
		 * sendKeys("Click on the arrows to change the translation direction.");
		 * 
		 * Assert.assertTrue( driver.findElement((By.
		 * xpath("//li[@class='plus-50 error' and text()='50 characters maximum']")))
		 * .isDisplayed());
		 * 
		 * Assert.assertFalse(driver.findElement(buttonSignUp).isEnabled());
		 * 
		 * // full valid data
		 * 
		 * driver.findElement(passwordTextbox).clear();
		 * 
		 * driver.findElement(By.id("new_password")).sendKeys("AutomationFC123_");
		 * 
		 * Assert.assertFalse(driver .findElement((By.
		 * xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']"
		 * ))) .isDisplayed());
		 * 
		 * Assert.assertFalse(driver .findElement((By.
		 * xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']"
		 * ))) .isDisplayed());
		 * 
		 * Assert.assertFalse(driver .findElement((By.
		 * xpath("//li[@class='number-char completed' and text()='One number']"))).
		 * isDisplayed());
		 * 
		 * Assert.assertFalse(driver .findElement((By.
		 * xpath("//li[@class='lowercase-char completed' and text()='One special character']"
		 * ))) .isDisplayed());
		 * 
		 * Assert.assertFalse( driver.findElement((By.
		 * xpath("//li[@class='8-char completed' and text()='8 characters minimum']")))
		 * .isDisplayed());
		 * 
		 * Assert.assertFalse( driver.findElement((By.
		 * xpath("//li[@class='plus-50 error' and text()='50 characters maximum']")))
		 * .isDisplayed());
		 * 
		 * Assert.assertTrue(driver.findElement(buttonSignUp).isEnabled());
		 * 
		 * driver.findElement(newsCheckbox).click();
		 * 
		 * Assert.assertTrue(driver.findElement(newsCheckbox).isSelected());
		 */
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}