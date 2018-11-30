package com.interactive.dictionary.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;

import com.interactive.dictionary.utils.ReadConfig;

public class BaseTest {
	
	public static WebDriver driver;
	public static String username;
	public static String email;
	public static String password;
	
	@BeforeSuite
	public void setupBefore() {
		System.setProperty("webdriver.gecko.driver", ReadConfig.getProperty("firefoxDriver"));
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

}
