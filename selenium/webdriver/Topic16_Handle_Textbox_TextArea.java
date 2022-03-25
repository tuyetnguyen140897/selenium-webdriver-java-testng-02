
package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic16_Handle_Textbox_TextArea {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	JavascriptExecutor jsExecutor;

	String loginPageUrl, customerID, userID, password, name, gender, DOB_input, DOB_output, address_input,
			address_output, city, state, pin, phone, email, address_edited_input, address_edited_output, city_edited,
			state_edited, pin_edited, phone_edited, email_edited, address_output1, address_output2;

	By customerNameTextbox = By.name("name");

	By genderRadio = By.xpath("//input[@value='f']");

	By genderTexboxby = By.name("gender");

	By dateOfBirthTextbox = By.id("dob");

	By dobEdited = By.name("dob");

	By addressTextArea = By.name("addr");

	By cityTextArea = By.name("city");

	By stateTextArea = By.name("state");

	By pinTextArea = By.name("pinno");

	By phoneTextArea = By.name("telephoneno");

	By emailTextArea = By.name("emailid");

	By passwordInput = By.name("password");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		name = "Tuyet nguyen";

		gender = "female";

		DOB_input = "12/12/1990";

		DOB_output = "1990-12-12";

		address_input = "123 \r\n" + "PO";

		address_output1 = "123 PO";

		address_output2 = "123 \r\n" + "\r\n" + "PO";

		city = "LosAngles";

		state = "USA";

		pin = "123465";

		phone = "0123456789";

		email = "lamlam" + randomInt() + "@gmail.com";

		address_edited_input = "895 PO \r\n" + "California\r\n" + "New York";

		address_edited_output = "895 PO California New York";

		city_edited = "Miami";

		state_edited = "United States";

		pin_edited = "456123";

		phone_edited = "0985123456";

		email_edited = "lamlam" + randomInt() + "@hotmail.com";

		// open browser
		driver = new FirefoxDriver();

		jsExecutor = (JavascriptExecutor) driver;

		// Set timeout to find element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("https://demo.guru99.com/v4/");

	}

	@Test
	public void TC_01_Register() {

		driver.navigate().refresh();

		loginPageUrl = driver.getCurrentUrl();

		driver.findElement(By.xpath("//a[text()='here']")).click();

		driver.findElement(By.name("emailid")).sendKeys(email);

		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();

		System.out.println(userID);

		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		System.out.println(password);

	}

	@Test
	public void TC_02_Login() {

		driver.get(loginPageUrl);

		driver.findElement(By.name("uid")).sendKeys(userID);

		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.name("btnLogin")).click();

		Assert.assertTrue(driver.findElement(
				(By.xpath("//marquee[@class='heading3' and text()=\"Welcome To Manager's Page of Guru99 Bank\"]")))
				.isDisplayed());

	}

	@Test
	public void TC_03_New_Customer() {

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(customerNameTextbox).sendKeys(name);

		driver.findElement(genderRadio).click();

		jsExecutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(dateOfBirthTextbox));

		driver.findElement(dateOfBirthTextbox).sendKeys(DOB_input);

		driver.findElement(addressTextArea).sendKeys(address_input);

		driver.findElement(cityTextArea).sendKeys(city);

		driver.findElement(stateTextArea).sendKeys(state);

		driver.findElement(pinTextArea).sendKeys(pin);

		driver.findElement(phoneTextArea).sendKeys(phone);

		driver.findElement(emailTextArea).sendKeys(email);

		driver.findElement(passwordInput).sendKeys(password);

		driver.findElement(By.name("sub")).click();

		Assert.assertTrue(driver
				.findElement((By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")))
				.isDisplayed());

		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				gender);

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				DOB_output);

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				address_output1);

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				phone);

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				email);

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(),
				customerID);

	}

	@Test
	public void TC_04_Edit_Customer() {

		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

		driver.findElement(By.name("cusid")).sendKeys(customerID);

		driver.findElement(By.name("AccSubmit")).click();

		Assert.assertEquals(driver.findElement(customerNameTextbox).getAttribute("value"), name);

		Assert.assertEquals(driver.findElement(genderTexboxby).getAttribute("value"), gender);

		Assert.assertEquals(driver.findElement(dobEdited).getAttribute("value"), DOB_output);

		Assert.assertEquals(driver.findElement(addressTextArea).getText(), address_output2);

		Assert.assertEquals(driver.findElement(cityTextArea).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextArea).getAttribute("value"), state);

		Assert.assertEquals(driver.findElement(pinTextArea).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(phoneTextArea).getAttribute("value"), phone);

		Assert.assertEquals(driver.findElement(emailTextArea).getAttribute("value"), email);

		driver.findElement(addressTextArea).clear();

		driver.findElement(addressTextArea).sendKeys(address_edited_input);

		driver.findElement(cityTextArea).clear();

		driver.findElement(cityTextArea).sendKeys(city_edited);

		driver.findElement(stateTextArea).clear();

		driver.findElement(stateTextArea).sendKeys(state_edited);

		driver.findElement(pinTextArea).clear();

		driver.findElement(pinTextArea).sendKeys(pin_edited);

		driver.findElement(phoneTextArea).clear();

		driver.findElement(phoneTextArea).sendKeys(phone_edited);

		driver.findElement(emailTextArea).clear();

		driver.findElement(emailTextArea).sendKeys(email_edited);

		
		 driver.findElement(By.name("sub")).click();
		 
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