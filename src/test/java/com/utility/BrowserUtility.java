package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public abstract class BrowserUtility {
	Logger logger = LoggerUtility.getLogger(this.getClass());

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();// instance variable driver

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {// constructor to initialize instance variable driver
		super();
		this.driver.set(driver);
		;
	}

	public BrowserUtility(String browserName) {
		logger.info("Launching browser for " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
		} else {
			logger.error("Invalid Browser Name........ Please select chrome or edge only!");

			System.err.println("Invalid Browser Name........ Please select chrome or edge only!");
		}

	}

	public BrowserUtility(Browser browserName) {
		logger.info("Launching browser for " + browserName);

		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
		} else if (browserName == Browser.FIREFOX) {
			driver.set(new FirefoxDriver());

		}

	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching browser for " + browserName);

		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=old");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
			} else {
				driver.set(new ChromeDriver());
			}
		} else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable--gpu");
				driver.set(new EdgeDriver(options));
			} else {
				driver.set(new EdgeDriver());
			}
		} else if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old");
				driver.set(new FirefoxDriver(options));
			} else {
				driver.set(new FirefoxDriver());

			}
		}

	}

	public void goToWebsite(String url) {
		logger.info("Visiting the website " + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximizing the browser window");

		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding element with the locator " + locator);

		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now performing click");

		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding element with the locator " + locator);

		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now entering text " + textToEnter);

		element.sendKeys(textToEnter);

	}

	public String getVisibleText(By locator) {
		logger.info("Finding element with the locator " + locator);

		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now returning the visible text " + element.getText());

		return element.getText();
	}

	public String takeScreenshot(String name) {
		// 1. Create TakesScreenshot Object by casting the WebDriver
		// Cast the driver to TakesScreenshot to enable screenshot functionality
		TakesScreenshot screenshot = (TakesScreenshot) driver;

		// 2. Get Current Date and Time for Timestamp
		// Create a Date object and use SimpleDateFormat to format the timestamp
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date); // Get the formatted timestamp

		// 3. Capture Screenshot as a Temporary File
		// Capture the screenshot and store it as a temporary file
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);

		// 4. Define Destination Path for the Screenshot
		// Construct the path where the screenshot will be saved, using the name and
		// timestamp
		String path = "./screenshots/" + name + " - " + timeStamp + ".png";// changed here

		// 5. Create Destination File Object
		// Create a File object that points to the destination where the screenshot will
		// be stored
		File screenshotFile = new File(path);

		// 6. Save Screenshot by Copying from Source to Destination
		// Use FileUtils to copy the temporary screenshot file to the destination
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			e.printStackTrace(); // Handle any file copy exceptions
		}

		// 7. Return the Screenshot File Path
		// Return the path of the saved screenshot file
		return path;
	}
	
	public void quit() {
		driver.get().quit();
	}

}
