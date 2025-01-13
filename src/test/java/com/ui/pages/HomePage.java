package com.ui.pages;

import static com.constants.Env.QA;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

/**
 * HomePage class represents the Home page of the application and provides methods to interact with it.
 * Implements the Page Object Model design pattern for better maintainability and reusability.
 */
public final class HomePage extends BrowserUtility {

    // Logger instance to log important activities on the HomePage.
    private final Logger logger = LoggerUtility.getLogger(this.getClass());

    // Locator for the "Sign in" link on the homepage.
    private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign in')]");

    /**
     * Constructor for initializing the HomePage with a specific browser.
     * 
     * @param browser The browser to be used (e.g., Chrome, Firefox, etc.).
     * @param isHeadless A boolean flag indicating whether to launch the browser in headless mode.
     */
    public HomePage(Browser browser, boolean isHeadless) {
        super(browser, isHeadless);

        // Navigate to the website's URL for the QA environment.
        goToWebsite(JSONUtility.readJSON(QA).getUrl());
    }

    /**
     * Constructor for initializing the HomePage with an existing WebDriver instance.
     * 
     * @param driver The WebDriver instance to be used for browser interaction.
     */
    public HomePage(WebDriver driver) {
        super(driver);

        // Navigate to the website's URL for the QA environment.
        goToWebsite(JSONUtility.readJSON(QA).getUrl());
    }

    /**
     * Navigates to the login page by clicking the "Sign in" link.
     * 
     * @return A new instance of LoginPage to interact with the login page.
     */
    public LoginPage goToLoginPage() {
        // Logs the action of clicking on the "Sign in" link.
        logger.info("Attempting to navigate to the Sign in page by clicking the link.");

        // Perform the click action on the "Sign in" link.
        clickOn(SIGN_IN_LINK_LOCATOR);

        // Return a new instance of LoginPage.
        return new LoginPage(getDriver());
    }
}
