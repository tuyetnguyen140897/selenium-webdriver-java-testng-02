
package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.openqa.selenium.support.Color;

public class Lesson22_Handle_Alert_Authentication_Alert {

	WebDriver driver;

	WebDriverWait expliciWait;

	JavascriptExecutor jsExcutor;

	Alert alert;

	String projectPath = System.getProperty("user.dir");

	@BeforeClass

	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		// open browser
		driver = new FirefoxDriver();

		// Wait de apply cho cac trang thai cua
		// element(visible/invisible/presence/clickable..)

		expliciWait = new WebDriverWait(driver, 15);

		// Reference casting ( ep kieu tuong minh)

		jsExcutor = (JavascriptExecutor) driver;

		// Set timeout to find element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_acceptAlert() {

		driver.get("https://demo.guru99.com/v4/index.php");

		driver.findElement(By.name("btnLogin")).click();

		sleepInSecond(2);

		// Vua wait vua wait cho alert xuat hien trong vong 15s

		alert = expliciWait.until(ExpectedConditions.alertIsPresent());

		// switch qua 1 alert

		// alert = driver.switchTo().alert();

		System.out.println(alert.getText());

		// Get text

		Assert.assertEquals(alert.getText(), "User or Password is not valid");

		// Accept alert

		alert.accept();

	}

	// @Test
	public void TC_02_acceptAlertJS() {

		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

		sleepInSecond(2);

		// Vua wait vua wait cho alert xuat hien trong vong 15s

		alert = expliciWait.until(ExpectedConditions.alertIsPresent());

		// switch qua 1 alert

		// alert = driver.switchTo().alert();

		System.out.println(alert.getText());

		// Get text

		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		// Accept alert

		alert.accept();

		// Verify message

		// You clicked an alert successfully

		Assert.assertEquals(driver.findElement(By.id("result")).getText().trim(), "You clicked an alert successfully");

	}

	// @Test
	public void TC_03_confirmAlert() {

		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		sleepInSecond(2);

		// Vua wait vua wait cho alert xuat hien trong vong 15s

		alert = expliciWait.until(ExpectedConditions.alertIsPresent());

		// switch qua 1 alert

		// alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		// Accept alert

		alert.accept();

		Assert.assertEquals(driver.findElement(By.id("result")).getText().trim(), "You clicked: Ok");

		driver.navigate().refresh();

		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		sleepInSecond(2);

		alert = expliciWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		// Accept alert

		alert.dismiss();

		Assert.assertEquals(driver.findElement(By.id("result")).getText().trim(), "You clicked: Cancel");

	}

	// @Test
	public void TC_04_promptAlert() {

		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		sleepInSecond(2);

		// Vua wait vua wait cho alert xuat hien trong vong 15s

		alert = expliciWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		// sendkey

		String addressName = "Tuyet";

		alert.sendKeys(addressName);
		alert.accept();

		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText().trim(),
				"You entered: " + addressName.trim());

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		sleepInSecond(2);

		alert = expliciWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		// sendkey

		String addressName1 = "HoChiMinh city";

		alert.sendKeys(addressName1);

		alert.dismiss();

		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText().trim(), "You entered: null");

	}

	@Test
	public void TC_05_authenticationAlert() {

		// Method 1 : Use direct link , edit directly link

		String username = "admin";

		String password = "admin";

		// driver.get("http://" +username+ ":" + password+ "@"
		// +"the-internet.herokuapp.com/basic_auth");

		// Method 2 : If can't get link , we write function to get link

		driver.get("http://the-internet.herokuapp.com");

		String basicAuthenticationLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

		driver.get(getLinkByUserPass(basicAuthenticationLink, username, password));

		Assert.assertTrue(driver
				.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credenti')]"))
				.isDisplayed());

	}

	public String getLinkByUserPass(String link, String username, String password) {
		String[] links = link.split("//");
		return links[0] + "//" + username + ":" + password + "@" + links[1];
	}

	@AfterClass
	public void afterClass() {

		driver.quit();
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond + 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}