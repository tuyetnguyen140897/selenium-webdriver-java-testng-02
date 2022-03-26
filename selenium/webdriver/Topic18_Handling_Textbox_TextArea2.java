
package webdriver;

import java.util.List;
import java.util.Random;
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

public class Topic18_Handling_Textbox_TextArea2 {

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

		driver.get("https://opensource-demo.orangehrmlive.com/");

	}

	@Test
	public void TC_checkDropdown() {

		String firstName, lastName;

		firstName = "Tuyet";

		lastName = "Nguyen";

		driver.findElement(By.id("txtUsername")).sendKeys("Admin");

		driver.findElement(By.id("txtPassword")).sendKeys("admin123");

		driver.findElement(By.id("btnLogin")).click();

		driver.findElement(By.id("menu_pim_viewPimModule")).click();

		driver.findElement(By.id("menu_pim_addEmployee")).click();

		driver.findElement(By.id("firstName")).sendKeys(firstName);

		driver.findElement(By.id("lastName")).sendKeys(lastName);

		String employeeID = driver.findElement(By.id("employeeId")).getAttribute("value");

		driver.findElement(By.id("btnSave")).click();

		// verify the same data at 2 screens

		By firstNameTextbox = By.id("personal_txtEmpFirstName");

		By lastNameTextbox = By.id("personal_txtEmpLastName");

		By employeeIdTextbox = By.id("personal_txtEmployeeId");

		Assert.assertEquals(driver.findElement(firstNameTextbox).getAttribute("value"), firstName);

		Assert.assertEquals(driver.findElement(lastNameTextbox).getAttribute("value"), lastName);

		Assert.assertEquals(driver.findElement(employeeIdTextbox).getAttribute("value"), employeeID);

		// verify disable

		Assert.assertFalse(driver.findElement(firstNameTextbox).isEnabled());

		Assert.assertFalse(driver.findElement(lastNameTextbox).isEnabled());

		Assert.assertFalse(driver.findElement(employeeIdTextbox).isEnabled());

		// click Edit button

		driver.findElement(By.id("btnSave")).click();

		// input 2 new data at 2 fields

		driver.findElement(firstNameTextbox).clear();

		driver.findElement(firstNameTextbox).sendKeys("Minh");

		driver.findElement(lastNameTextbox).clear();

		driver.findElement(lastNameTextbox).sendKeys("Lam");

		Assert.assertTrue(driver.findElement(firstNameTextbox).isEnabled());

		Assert.assertTrue(driver.findElement(lastNameTextbox).isEnabled());

		// Click button Save

		driver.findElement(By.id("btnSave")).click();

		// Verify 2 new data after editing
		Assert.assertEquals(driver.findElement(firstNameTextbox).getAttribute("value"), "Minh");

		Assert.assertEquals(driver.findElement(lastNameTextbox).getAttribute("value"), "Lam");

		// verify 3 fields disable

		Assert.assertFalse(driver.findElement(firstNameTextbox).isEnabled());

		Assert.assertFalse(driver.findElement(lastNameTextbox).isEnabled());

		Assert.assertFalse(driver.findElement(employeeIdTextbox).isEnabled());

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}