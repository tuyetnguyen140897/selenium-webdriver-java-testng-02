
package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic17_Handling_Default_Dropdown {

	WebDriver driver;

	String projectPath = System.getProperty("user.dir");

	Select select;

	@BeforeClass

	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		// open browser
		driver = new FirefoxDriver();

		// Set timeout to find element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		driver.get("https://demo.nopcommerce.com/register");

	}

	@Test
	public void TC_Signup() {

		String gender, firstName, lastName, Day, Month, Year, email, company_name, password;

		firstName = "Tuyet";

		lastName = "Nguyen";

		Day = "1";

		Month = "May";

		Year = "1980";

		email = "Tuyet" + randomInt() + "@gmail.net";

		company_name = "Automation VN";

		password = "123456";

		By genderRadio = By.id("gender-male");

		By firstNameTextbox = By.id("FirstName");

		By lastNameTextbox = By.id("LastName");

		By dateDropdown = By.name("DateOfBirthDay");

		By monthDropdown = By.name("DateOfBirthMonth");

		By yearDropdown = By.name("DateOfBirthYear");

		By emailTextbox = By.id("Email");

		By companyNameTextbox = By.id("Company");

		driver.findElement(genderRadio).click();

		driver.findElement(firstNameTextbox).sendKeys(firstName);

		driver.findElement(lastNameTextbox).sendKeys(lastName);

		// Select dropdown Day

		select = new Select(driver.findElement(By.name("DateOfBirthDay")));

		select.selectByVisibleText(Day);

		Assert.assertEquals(select.getOptions().size(), 32);

		// Select dropdown Month

		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));

		select.selectByVisibleText(Month);

		Assert.assertEquals(select.getOptions().size(), 13);

		// Select dropdown Year

		select = new Select(driver.findElement(By.name("DateOfBirthYear")));

		select.selectByVisibleText(Year);

		Assert.assertEquals(select.getOptions().size(), 112);

		driver.findElement(By.id("Email")).sendKeys(email);

		driver.findElement(By.id("Company")).sendKeys(company_name);

		driver.findElement(By.id("Password")).sendKeys(password);

		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

		driver.findElement(By.id("register-button")).click();

		// verify success message

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),
				"Your registration completed");

		// Click My Account

		driver.findElement(By.xpath("//a[@class='button-1 register-continue-button']")).click();

		driver.findElement(By.cssSelector("a.ico-account")).click();

		// Verify Day , Month , Year

		Assert.assertTrue(driver.findElement(genderRadio).isSelected());

		Assert.assertEquals(driver.findElement(firstNameTextbox).getAttribute("value"), firstName);

		Assert.assertEquals(driver.findElement(lastNameTextbox).getAttribute("value"), lastName);

		select = new Select(driver.findElement(By.name("DateOfBirthDay")));

		Assert.assertEquals(select.getFirstSelectedOption().getText(), Day);

		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));

		Assert.assertEquals(select.getFirstSelectedOption().getText(), Month);

		select = new Select(driver.findElement(By.name("DateOfBirthYear")));

		Assert.assertEquals(select.getFirstSelectedOption().getText(), Year);

		Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"), email);

		Assert.assertEquals(driver.findElement(companyNameTextbox).getAttribute("value"), company_name);

	}

	@AfterClass
	public void afterClass() {

		// driver.quit();
	}

	public int randomInt() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);
		return randomInt;
	}
}