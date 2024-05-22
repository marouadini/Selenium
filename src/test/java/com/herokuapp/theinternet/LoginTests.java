package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {
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

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		// close browser
		driver.close();
		System.out.println("test finished");
	}

	@Test(priority = 1, groups = { "positivetests", "smokeTests" })
	public void PositiveloginTest() {
		System.out.println("test started");

//		Create driver
		// System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
		// WebDriver driver = new ChromeDriver();
		System.out.println("Browser started");

//		open test page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);// open the browser //driver.navigate().to(url);
		// driver.manage().window().maximize();
		System.out.println("page is opened");

		// enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		// click on button login
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();

		// Verifications:
		// new URL
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String acualUrl = driver.getCurrentUrl();
		Assert.assertEquals(acualUrl, expectedUrl, "Acual page URl is not the same as expected");

		// logout button is visible
		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "Log out button is not visible!");

		// succesful login message
		WebElement successMessage = driver.findElement(By.xpath("//div[@id='flash']"));
		String expectedMessage = "You logged into a secure area!";
		String acualMessage = successMessage.getText();
		// Assert.assertEquals(acualMessage, expectedMessage,"Acual message is not the
		// same as expected");
		Assert.assertTrue(acualMessage.contains(expectedMessage),
				"Acual message does not contain expected message.\n AcualMessage = " + acualMessage
						+ "\n ExpectedMessage = " + expectedMessage);

	}

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 2, groups = { "negativetests", "smokeTests" })
	public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
		// create driver
		// WebDriver driver = new ChromeDriver();
		// WebDriver driver = new FirefoxDriver();

		// open web page
		driver.navigate().to("http://the-internet.herokuapp.com/login");
		System.out.println("Browser started");
		driver.manage().window().maximize();

		// enter incorrect username
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(username);
		System.out.println("entering user name");

		// enter password
		WebElement passwordElement = driver.findElement(By.name("password"));
		passwordElement.sendKeys(password);
		// click on button login
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		System.out.println("clicking button");

		// Verification
		// invalid user message
		WebElement invalidMessage = driver.findElement(By.id("flash"));
		// String expectedErrorMessage = "Your username is invalid!";
		String actualErrorMessage = invalidMessage.getText();

		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"Actual error message does not contain expected !");

		// close browser
		// driver.close();
		// System.out.println("test finished");
	}

}