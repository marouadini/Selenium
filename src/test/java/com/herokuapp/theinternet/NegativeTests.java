package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTests {
	
	@Parameters({ "username","password","expectedMessage" })
	@Test(priority = 1,groups = {"negativetests","smokeTests"})
	public void negativeLoginTest(String username,String password,String expectedErrorMessage) {
		// create driver
		// WebDriver driver = new ChromeDriver();
		WebDriver driver = new FirefoxDriver();

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
		//String expectedErrorMessage = "Your username is invalid!";
		String actualErrorMessage = invalidMessage.getText();

		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"Actual error message does not contain expected !");

		// close browser
		driver.close();
		System.out.println("test finished");
	}

	
	/**
	 * 
	 */
	//@Test(priority = 2,groups = {"negativetests","smokeTests"})
/*	public void incorrectPasswordTest() {
		System.out.println("Starting incorrectPasswordTest");

//		Create driver
		//System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// maximize browser window
		driver.manage().window().maximize();

//		open test page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is opened.");

//		enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

//		enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("incorrectPassword!");

//		click login button
		WebElement logInButton = driver.findElement(By.tagName("button"));
		logInButton.click();

		// Verifications
		WebElement errorMessage = driver.findElement(By.id("flash"));
		String expectedErrorMessage = "Your password is invalid!";
		String actualErrorMessage = errorMessage.getText();

		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"Actual error message does not contain expected. \nActual: " + actualErrorMessage + "\nExpected: "
						+ expectedErrorMessage);

		// Close browser
		driver.quit();
	}*/

}
