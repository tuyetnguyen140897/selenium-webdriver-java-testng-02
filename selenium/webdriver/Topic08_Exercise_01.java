
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

public class Topic08_Exercise_01 {
	// declare1 var to be for Selenium driver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		// open browser
		driver = new FirefoxDriver();

		// Set timeout to find element
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Mo application len AUT/SUT
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Empty_Email_Password() {
		// Input data into textbox
		driver.findElement(By.id("txtFirstname")).sendKeys("");
		driver.findElement(By.id("txtEmail")).sendKeys("");
		driver.findElement(By.id("txtCEmail")).sendKeys("");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.id("txtCPassword")).sendKeys("");
		driver.findElement(By.id("txtPhone")).sendKeys("");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// Get and verify error message text of all fields
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC_02_Invalid_Email_CEmail() {
		// refresh current page
		driver.navigate().refresh();
		// Input data into textbox
		driver.findElement(By.id("txtFirstname")).sendKeys("Tuyet");
		driver.findElement(By.id("txtEmail")).sendKeys("tuyet.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("tuyet.com");
		driver.findElement(By.id("txtPassword")).sendKeys("Tuyet123456_");
		driver.findElement(By.id("txtCPassword")).sendKeys("Tuyet123456_");
		driver.findElement(By.id("txtPhone")).sendKeys("0985312877");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// Get and verify error message text of all fields

		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

	}

	@Test

	public void TC_03_Incorrect_CEmail() {
		// refresh current page
		driver.navigate().refresh();
		// Input data into textbox
		driver.findElement(By.id("txtFirstname")).sendKeys("Tuyet");
		driver.findElement(By.id("txtEmail")).sendKeys("tuyet@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("tuyet123@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("Teo123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("Teo123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0985312877");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// Get and verify error message text of all fields

		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),
				"Email nhập lại không đúng");
		

	}

	public void TC_04_Empty_Email_Password() {
		// refresh current page
		driver.navigate().refresh();
		// Input data into textbox
		driver.findElement(By.id("txtFirstname")).sendKeys("Tuyet");
		driver.findElement(By.id("txtEmail")).sendKeys("tuyet@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("tuyet@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("Teo12");
		driver.findElement(By.id("txtCPassword")).sendKeys("Teo12");
		driver.findElement(By.id("txtPhone")).sendKeys("0985312877");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// Get and verify error message text of all fields

		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}