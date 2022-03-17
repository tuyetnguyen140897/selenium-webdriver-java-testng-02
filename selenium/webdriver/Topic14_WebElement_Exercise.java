
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

public class Topic14_WebElement_Exercise {

	WebDriver driver;

	String projectPath = System.getProperty("user.dir");

	By emailTextbox = By.id("mail");

	By ageUnder18RadioBy = By.id("under_18");

	By ageRadioButtonDisable = By.id("radio-disabled");

	By educationTextArea = By.id("edu");

	By user5Text = (By.xpath("//h5[text()='Name: User5']"));

	By passwordTextbox_Disable = By.id("disable_password");

	By jobRole01Textbox = By.id("job1");

	By jobRole02Textbox = By.id("job2");

	By jobRole03Textbox_Disable = By.id("job3");

	// bio
	By biographyDisableTextbox = By.id("bio");

	By interestDevelopmentCheckbox = By.id("development");

	// check-disbaled

	By interestDevelopmentCheckbox_Disabled = By.id("check-disbaled");

	By sliderProgressBar_01 = By.id("slider-1");

	By sliderProgressBar_02_Disable = By.id("slider-2");

	// id="java"

	By javaCheckbox_Selected = By.id("java");

	@BeforeClass

	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		// open browser
		driver = new FirefoxDriver();

		// Set timeout to find element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		driver.get("https://automationfc.github.io/basic-form/index.html");

	}

	@Test
	public void TC_01_Check_Display() {

		WebElement emailTextbox = driver.findElement(By.id("mail"));

		if (emailTextbox.isDisplayed()) {

			emailTextbox.sendKeys("Automation Testing");

			System.out.println("Email is displayed");
		}

		else {
			System.out.println("Email is not displayed");
		}

		WebElement ageUnderTextbox = driver.findElement(By.id("under_18"));

		if (ageUnderTextbox.isDisplayed()) {

			ageUnderTextbox.click();

			System.out.println("Age Under is displayed");
		}

		else {

			System.out.println("Age Under is not displayed");

		}

		WebElement educationTextbox = driver.findElement(By.id("edu"));

		if (educationTextbox.isDisplayed()) {

			educationTextbox.sendKeys("automation@gmail.com");

			System.out.println("Education is displayed");
		}

		else {
			System.out.println("Education is not displayed");
		}

		WebElement user5Textbox = driver.findElement(By.xpath("//h5[text()='Name: User5']"));

		if (user5Textbox.isDisplayed()) {

			System.out.println("User 5  is displayed");
		}

		else {
			System.out.println("User 5 is not displayed");
		}

	}

	@Test
	public void TC_02_is_Displayed_Refactor() {

		if (isElementDisplayed(emailTextbox)) {

			sendKeyToElement(emailTextbox, "Automation Testing");

		}

		if (isElementDisplayed(ageUnder18RadioBy)) {

			clickToElement(ageUnder18RadioBy);

		}

		Assert.assertFalse(isElementDisplayed(user5Text));

	}

	@Test
	public void TC_03_check_Enabled() {

		// check enable fields

		Assert.assertTrue(isElementEnabled(emailTextbox));

		Assert.assertTrue(isElementEnabled(ageUnder18RadioBy));

		Assert.assertTrue(isElementEnabled(educationTextArea));

		Assert.assertTrue(isElementEnabled(jobRole01Textbox));

		Assert.assertTrue(isElementEnabled(jobRole02Textbox));

		Assert.assertTrue(isElementEnabled(interestDevelopmentCheckbox));

		Assert.assertTrue(isElementEnabled(sliderProgressBar_01));

		// check disable fields

		Assert.assertFalse(isElementEnabled(ageRadioButtonDisable));

		Assert.assertFalse(isElementEnabled(passwordTextbox_Disable));

		Assert.assertFalse(isElementEnabled(jobRole03Textbox_Disable));

		Assert.assertFalse(isElementEnabled(biographyDisableTextbox));

		Assert.assertFalse(isElementEnabled(interestDevelopmentCheckbox_Disabled));

		Assert.assertFalse(isElementEnabled(sliderProgressBar_02_Disable));

	}

	@Test
	public void TC_04_checkSelected() {

		clickToElement(ageUnder18RadioBy);

		clickToElement(javaCheckbox_Selected);

		Assert.assertTrue(isElementSelected(ageUnder18RadioBy));

		Assert.assertTrue(isElementSelected(javaCheckbox_Selected));

		// click to unselect Java checkbox

		clickToElement(javaCheckbox_Selected);

		Assert.assertFalse(isElementSelected(javaCheckbox_Selected));

	}

	// create new function

	public boolean isElementDisplayed(By by) {

		WebElement element = driver.findElement(by);

		if (element.isDisplayed()) {
			System.out.println("Element" + by + " " + "is displayed");

			return true;

		} else {

			System.out.println("Element" + by + " " + "is not displayed");

			return false;

		}

	}

	public boolean isElementEnabled(By by) {

		WebElement element = driver.findElement(by);

		if (element.isEnabled()) {
			System.out.println("Element" + by + " " + "is enabled");

			return true;

		} else {

			System.out.println("Element" + by + " " + "is not enabled");

			return false;

		}

	}

	public boolean isElementSelected(By by) {

		WebElement element = driver.findElement(by);

		if (element.isSelected()) {
			System.out.println("Element" + by + " " + "is selected");

			return true;

		} else {

			System.out.println("Element" + by + " " + "is not selected");

			return false;

		}

	}

	public void sendKeyToElement(By by, String value) {

		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);

	}

	public void clickToElement(By by) {

		WebElement element = driver.findElement(by);

		element.click();

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}