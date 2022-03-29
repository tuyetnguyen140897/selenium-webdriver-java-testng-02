
package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic18_Handling_Custom_Dropdown_List {

	WebDriver driver;

	WebDriverWait expliciWait;

	JavascriptExecutor jsExcutor;

	String projectPath = System.getProperty("user.dir");

	@BeforeClass

	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		// open browser
		driver = new FirefoxDriver();

		// driverID

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
	public void TC_01_Jquery() {

		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		By parent = By.id("number-button");

		By child = By.cssSelector("ul#number-menu div");

		selectedItemDropdown(parent, child, "19");

		sleepInSecond(2);

		Assert.assertTrue(isElementDisplayed(
				By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")));

		selectedItemDropdown(parent, child, "5");

		sleepInSecond(2);

		Assert.assertTrue(isElementDisplayed(
				By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='5']")));

		selectedItemDropdown(parent, child, "7");

		sleepInSecond(2);

		Assert.assertTrue(isElementDisplayed(
				By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='7']")));

	}

	// @Test
	public void TC_02_ReactJS() {

		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		By parent = By.cssSelector("i.dropdown.icon");

		By child = By.xpath("//div[@role='option']//span");

		selectedItemDropdown(parent, child, "Jenny Hess");

		sleepInSecond(2);

		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text()='Jenny Hess']")));

		selectedItemDropdown(parent, child, "Elliot Fu");

		sleepInSecond(2);

		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text()='Elliot Fu']")));

		selectedItemDropdown(parent, child, "Stevie Feliciano");

		sleepInSecond(2);

		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text()='Stevie Feliciano']")));

	}

	// @Test
	public void TC_03_VueJS() {

		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		By parent = By.xpath("//li[@class='dropdown-toggle']");

		By child = By.xpath("//ul[@class='dropdown-menu']//li");

		selectedItemDropdown(parent, child, "First Option");

		sleepInSecond(2);

		Assert.assertTrue(
				isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and contains (text(),'First Option')]")));

		selectedItemDropdown(parent, child, "Second Option");

		sleepInSecond(2);

		Assert.assertTrue(
				isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and contains (text(),'Second Option')]")));

		selectedItemDropdown(parent, child, "Third Option");

		sleepInSecond(2);

		Assert.assertTrue(
				isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and contains (text(),'Third Option')]")));

	}

	/*
	 * @Test
	 * 
	 * public void TC_04_Angular() {
	 * 
	 * driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
	 * 
	 * // get hidden text
	 * 
	 * // String script = "return document.getElementById('hidden_div').innerHTML";
	 * 
	 * By parent = By.xpath("//div[@class='col-lg-3 col-md-3']");
	 * 
	 * By child = By.xpath(
	 * "//div[@class='col-lg-3 col-md-3']//div[@class='form-group']//ng-dropdown-panel[@class='ng-dropdown-panel ng-star-inserted ng-select-bottom']/child::div//div[@role='option']"
	 * );
	 * 
	 * selectedItemDropdown(parent, child, "Mũi tiêm thứ nhất");
	 * 
	 * sleepInSecond(2);
	 * 
	 * Assert.assertTrue(isElementDisplayed(By.xpath(
	 * "//div[@class='col-lg-3 col-md-3']//div[@class='form-group']//ng-dropdown-panel[@class='ng-dropdown-panel ng-star-inserted ng-select-bottom']/child::div//div[@role='option' and contains (string(),'Mũi tiêm thứ nhất')]"
	 * )));
	 * 
	 * selectedItemDropdown(parent, child, "Mũi tiêm tiếp theo");
	 * 
	 * sleepInSecond(2);
	 * 
	 * Assert.assertTrue(isElementDisplayed(By.xpath(
	 * "//div[@class='col-lg-3 col-md-3']//div[@class='form-group']//ng-dropdown-panel[@class='ng-dropdown-panel ng-star-inserted ng-select-bottom']/child::div//div[@role='option' and contains (string(),'Mũi tiêm tiếp theo')]"
	 * )));
	 * 
	 * }
	 */

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

	public void selectedItemDropdown(By parentElement, By childElement, String itemSelected) {

		// 1. Click vao 1 element cho n hien thi tat ca cac item

		driver.findElement(parentElement).click();

		// 2. Wait cho tat ca item load ra ( co trong DOM/HTML)
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childElement));

		List<WebElement> allItems = driver.findElements(childElement);

		// System.out.println("All item = " + allItems.size());

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(itemSelected)) {
				if (item.isDisplayed()) {
					// 3. Neu item minh can chon no nam trong view (nhin thay duoc) thi click vao

					item.click();
				} else {

					// 4. Neu nhu item minh chon khong nhin thay ( che ben duoi) thi scroll xuong va

					jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);

					item.click();

					sleepInSecond(2);

					break;

				}

			}

		}

	}

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

}