package com.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaTestUtility {
    // The URL for the LambdaTest hub to connect to the remote grid
    private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
    
    // Using ThreadLocal to ensure each thread (test case) gets its own WebDriver instance
    private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();;
    
    // Using ThreadLocal to hold the DesiredCapabilities for each thread (test case)
    private static ThreadLocal<DesiredCapabilities> capabilitiesLocal = new ThreadLocal<DesiredCapabilities>();

    // Method to initialize a LambdaTest session with a given browser and test name
    public static WebDriver intializeLambdaTestSession(String browser, String testName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        capabilities.setCapability("browserName", browser);
        
        capabilities.setCapability("browserVersion", "127");
        
        Map<String, Object> ltOptions = new HashMap();
        
        ltOptions.put("user", "mohitbhatia.bina");
        
        // LambdaTest access key, used for authentication during session creation
        ltOptions.put("accessKey", "lxstEhlHvY4Kdoad1AAznNPWYtok9izA1VKvwfxjWW699rwtkF");

        ltOptions.put("build", "Selenium 4");
        
        // Name of the test case, displayed in the LambdaTest dashboard for easier identification
        ltOptions.put("name", testName);
        
        ltOptions.put("platformName", "Windows 10");
        
        ltOptions.put("seCdp", true);
        
        ltOptions.put("selenium_version", "4.23.0");
        
        capabilities.setCapability("LT:Options", ltOptions);
        
        capabilitiesLocal.set(capabilities);
        
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(HUB_URL), capabilitiesLocal.get());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        
        driverLocal.set(driver);

        return driverLocal.get();
    }

    public static void quitSession() {
        if (driverLocal.get() != null) {
            driverLocal.get().quit();
        }
    }
}
