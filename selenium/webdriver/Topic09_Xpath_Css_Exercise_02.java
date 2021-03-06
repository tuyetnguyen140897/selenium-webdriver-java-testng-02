
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
		Assert.assertEquals(driver.findElement(error_msg_name).getText(), "Vui l??ng nh???p h??? t??n");
		Assert.assertEquals(driver.findElement(error_msg_Email).getText(), "Vui l??ng nh???p email");
		Assert.assertEquals(driver.findElement(error_msg_CEmail).getText(), "Vui l??ng nh???p l???i ?????a ch??? email");
		Assert.assertEquals(driver.findElement(error_msg_Password).getText(), "Vui l??ng nh???p m???t kh???u");
		Assert.assertEquals(driver.findElement(error_msg_CPassword).getText(), "Vui l??ng nh???p l???i m???t kh???u");
		Assert.assertEquals(driver.findElement(error_msg_Phone).getText(), "Vui l??ng nh???p s??? ??i???n tho???i.");

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
		Assert.assertEquals(driver.findElement(error_msg_Email).getText(), "Vui l??ng nh???p email h???p l???");
		Assert.assertEquals(driver.findElement(error_msg_CEmail).getText(), "Email nh???p l???i kh??ng ????ng");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();

		// Get and verify error message text of all fields

		Assert.assertEquals(driver.findElement(error_msg_Email).getText(), "Vui l??ng nh???p email h???p l???");
		Assert.assertEquals(driver.findElement(error_msg_CEmail).getText(), "Email nh???p l???i kh??ng ????ng");

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

		Assert.assertEquals(driver.findElement(error_msg_CEmail).getText(), "Email nh???p l???i kh??ng ????ng");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();

		// Get and verify error message text of all fields

		Assert.assertEquals(driver.findElement(error_msg_CEmail).getText(), "Email nh???p l???i kh??ng ????ng");

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

		Assert.assertEquals(driver.findElement(error_msg_Password).getText(), "M???t kh???u ph???i c?? ??t nh???t 6 k?? t???");
		Assert.assertEquals(driver.findElement(error_msg_CPassword).getText(), "M???t kh???u ph???i c?? ??t nh???t 6 k?? t???");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();

		// Get and verify error message text of all fields

		Assert.assertEquals(driver.findElement(error_msg_Password).getText(), "M???t kh???u ph???i c?? ??t nh???t 6 k?? t???");
		Assert.assertEquals(driver.findElement(error_msg_CPassword).getText(), "M???t kh???u ph???i c?? ??t nh???t 6 k?? t???");

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
		Assert.assertEquals(driver.findElement(error_msg_CPassword).getText(), "M???t kh???u b???n nh???p kh??ng kh???p");

		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();

		// Get and verify error message text of all fields

		Assert.assertEquals(driver.findElement(error_msg_CPassword).getText(), "M???t kh???u b???n nh???p kh??ng kh???p");

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
		Assert.assertEquals(driver.findElement(error_msg_Phone).getText(), "Vui l??ng nh???p con s???");
		// clear du lieu
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("09821456");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();
		Assert.assertEquals(driver.findElement(error_msg_Phone).getText(), "S??? ??i???n tho???i ph???i t??? 10-11 s???.");

		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("0982145645689999");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();
		Assert.assertEquals(driver.findElement(error_msg_Phone).getText(), "S??? ??i???n tho???i ph???i t??? 10-11 s???.");

		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("123456");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();
		Assert.assertEquals(driver.findElement(error_msg_Phone).getText(),
				"S??? ??i???n tho???i b???t ?????u b???ng: 09 - 03 - 012 - 016 - 018 - 019");

	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}