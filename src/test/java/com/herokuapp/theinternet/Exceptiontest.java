package com.herokuapp.theinternet;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Exceptiontest {
	private WebDriver driver;

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	private void setUp(String browser) {

		switch ("browser") {
		case "chrome":
			// Create driver
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		default:
			driver = new ChromeDriver();
			break;
		}

		// maximaze browser window
		driver.manage().window().maximize();
	}

	@Test
	public void noSuchElementExceptionTest() {
		// open test page
		String url = "https://practicetestautomation.com/practice-test-exceptions/";
		driver.get(url);// open the browser //driver.navigate().to(url);
		System.out.println("page is opened");

		// Click Add button
		WebElement addButton = driver.findElement(By.id("add_btn"));
		addButton.click();

		// implicit wait
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement row2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
		// Verify Row 2 input field is displayed
		Assert.assertTrue(row2.isDisplayed(), "Row 2 input field is not visible!");

	}

	@Test
	public void elementNotInteractableExceptionTest() {
		// open test page
		String url = "https://practicetestautomation.com/practice-test-exceptions/";
		driver.get(url);// open the browser //driver.navigate().to(url);
		System.out.println("page is opened");

		// Click Add button
		WebElement addButton = driver.findElement(By.id("add_btn"));
		addButton.click();
//		Wait for the second row to load
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement row2 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
		// Verify Row 2 input field is displayed
		Assert.assertTrue(row2.isDisplayed(), "Row 2 input field is not visible!");
//		Type text into the second input field
		row2.sendKeys("text");
//		Push Save button using locator By.name(“Save”)
		WebElement saveButton = driver.findElement(By.xpath("//div[@id='row2']//button[@id='save_btn']"));
		saveButton.click();
//		Verify text saved
		WebElement confirmation = driver.findElement(By.id("confirmation"));
		String messageText = confirmation.getText();
		Assert.assertEquals(messageText, "Row 2 was saved", "Confirmation message text is not expected");

	}

	@Test
	public void invalidElementStateExceptionTest() {
		// open test page
		driver.get("https://practicetestautomation.com/practice-test-exceptions/");// open the browser
																					// //driver.navigate().to(url);
		System.out.println("page is opened");
		// click edit button
		WebElement editButton = driver.findElement(By.xpath("//div[@id='row1']//button[@id='edit_btn']"));
		editButton.click();
		// create web element input field 1
		WebElement input1 = driver.findElement(By.xpath("//div[@id='row1']//input[@class='input-field']"));

		// wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9));
		wait.until(ExpectedConditions.elementToBeClickable(input1));

		// clear input field 1
		input1.clear();
		// Type text into the input field
		input1.sendKeys("sushi");
		// click save button
		WebElement saveButton = driver.findElement(By.xpath("//div[@id='row1']//button[@id='save_btn']"));
		saveButton.click();

		// Verify text changed
		String value = input1.getAttribute("value");
		Assert.assertEquals(value, "sushi", "input 1 field is not expected");
		// Verify text saved
		WebElement confirmation = driver.findElement(By.id("confirmation"));
		String messageText = confirmation.getText();
		Assert.assertEquals(messageText, "Row 1 was saved", "Confirmation message text is not expected");

	}

	@Test
	public void staleElementReferenceExceptionTest() {

//	Open page
		driver.get("https://practicetestautomation.com/practice-test-exceptions/");
//	Find the instructions text element
		// WebElement txtInstruction =
		// driver.findElement(By.xpath("//p[@id='instructions']"));

//	Push add button
		WebElement addButton = driver.findElement(By.xpath("//div[@id='row1']//button[@id='add_btn']"));
		addButton.click();
//	Verify instruction text element is no longer displayed
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9));
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("instructions"))),
				"text instruction is displayed");
	}

	@Test
	public void timeoutExceptionTests() {

		// open test page
		driver.get("https://practicetestautomation.com/practice-test-exceptions/");// open the browser
																					// //driver.navigate().to(url);
		System.out.println("page is opened");
		// Click Add button
		WebElement addButton = driver.findElement(By.id("add_btn"));
		addButton.click();

		// implicit wait
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		WebElement row2 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
		// Verify Row 2 input field is displayed
		Assert.assertTrue(row2.isDisplayed(), "Row 2 input field is not visible!");

	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		// close browser
		driver.close();
		System.out.println("test finished");
	}
}
