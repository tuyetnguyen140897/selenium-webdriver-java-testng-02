
package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic09_Xpath_Css_Exercise_02 {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String name, email, CEmail, password, CPassword, phone;

	// fields
	By txtFirstname = By.id("txtFirstname");
	By txtEmail = By.id("txtEmail");
	By txtCEmail = By.id("txtCEmail");
	By txtPassword = By.id("txtPassword");
	By txtCPassword = By.id("txtCPassword");
	By txtPhone = By.id("txtPhone");
	By register_button = By.xpath("//form[@id='frmLogin']//button");

	// Error message
	By error_msg_name = By.id("txtFirstname-error");
	By error_msg_Email = By.id("txtEmail-error");
	By error_msg_CEmail = By.id("txtCEmail-error");
	By error_msg_Password = By.id("txtPassword-error");
	By error_msg_CPassword = By.id("txtCPassword-error");
	By error_msg_Phone = By.id("txtPhone-error");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		// open browser
		driver = new FirefoxDriver();

		// Set timeout to find element
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		name = "Tuyet";
		email = "automation@gmail.net";
		CEmail = "automation@gmail.net";
		password = "Tuyet123456_";
//		CPassword = "Tuyet123456";
		phone = "0912345678";

	}

	@BeforeMethod
	// before method - antonnation run for all every begins
	public void BeforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Empty_Email_Password() {

		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();

		// Get and verify error message text of all fields
		Assert.assertEquals(driver.findElement(error_msg_name).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(error_msg_Email).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(error_msg_CEmail).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(error_msg_Password).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(error_msg_CPassword).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(error_msg_Phone).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC_02_Invalid_Email_CEmail() {

		// Input data into textbox
		driver.findElement(By.id("txtFirstname")).sendKeys(name);
		driver.findElement(By.id("txtEmail")).sendKeys("teo123@@");
		driver.findElement(By.id("txtCEmail")).sendKeys("teo123@@");
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("txtCPassword")).sendKeys(password);
		driver.findElement(By.id("txtPhone")).sendKeys(phone);
		Assert.assertEquals(driver.findElement(error_msg_Email).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(error_msg_CEmail).getText(), "Email nhập lại không đúng");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();

		// Get and verify error message text of all fields

		Assert.assertEquals(driver.findElement(error_msg_Email).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(error_msg_CEmail).getText(), "Email nhập lại không đúng");

	}

	@Test

	public void TC_03_Incorrect_CEmail() {

		// Input data into textbox
		driver.findElement(By.id("txtFirstname")).sendKeys(name);
		driver.findElement(By.id("txtEmail")).sendKeys(email);
		driver.findElement(By.id("txtCEmail")).sendKeys("teo123");
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("txtCPassword")).sendKeys(password);
		driver.findElement(By.id("txtPhone")).sendKeys(phone);

		Assert.assertEquals(driver.findElement(error_msg_CEmail).getText(), "Email nhập lại không đúng");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();

		// Get and verify error message text of all fields

		Assert.assertEquals(driver.findElement(error_msg_CEmail).getText(), "Email nhập lại không đúng");

	}

	@Test

	public void TC_04_Password_Less_Than_6Chars() {

		// Input data into textbox
		driver.findElement(By.id("txtFirstname")).sendKeys(name);
		driver.findElement(By.id("txtEmail")).sendKeys(email);
		driver.findElement(By.id("txtCEmail")).sendKeys(email);
		driver.findElement(By.id("txtPassword")).sendKeys("1234");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234");
		driver.findElement(By.id("txtPhone")).sendKeys(phone);

		Assert.assertEquals(driver.findElement(error_msg_Password).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(error_msg_CPassword).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();

		// Get and verify error message text of all fields

		Assert.assertEquals(driver.findElement(error_msg_Password).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(error_msg_CPassword).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

	}

	@Test

	public void TC_05_Incorrect_Confirm_Password() {
		// Input data into textbox
		driver.findElement(By.id("txtFirstname")).sendKeys(name);
		driver.findElement(By.id("txtEmail")).sendKeys(email);
		driver.findElement(By.id("txtCEmail")).sendKeys(CEmail);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone")).sendKeys(phone);
		Assert.assertEquals(driver.findElement(error_msg_CPassword).getText(), "Mật khẩu bạn nhập không khớp");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();

		// Get and verify error message text of all fields

		Assert.assertEquals(driver.findElement(error_msg_CPassword).getText(), "Mật khẩu bạn nhập không khớp");

	}

	@Test

	public void TC_06_Incorrect_Phonenumber() {

		driver.findElement(By.id("txtFirstname")).sendKeys(name);
		driver.findElement(By.id("txtEmail")).sendKeys(email);
		driver.findElement(By.id("txtCEmail")).sendKeys(CEmail);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("txtCPassword")).sendKeys(password);
		driver.findElement(By.id("txtPhone")).sendKeys("abcdf");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();

		// Get and verify error message text of all fields
		Assert.assertEquals(driver.findElement(error_msg_Phone).getText(), "Vui lòng nhập con số");
		// clear du lieu
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("09821456");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();
		Assert.assertEquals(driver.findElement(error_msg_Phone).getText(), "Số điện thoại phải từ 10-11 số.");

		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("0982145645689999");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();
		Assert.assertEquals(driver.findElement(error_msg_Phone).getText(), "Số điện thoại phải từ 10-11 số.");

		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("123456");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();
		Assert.assertEquals(driver.findElement(error_msg_Phone).getText(),
				"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");

	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}