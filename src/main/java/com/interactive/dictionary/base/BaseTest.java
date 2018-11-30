package com.interactive.dictionary.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;

import com.interactive.dictionary.utils.ReadConfig;

public class BaseTest {
	
	protected WebDriver driver;
	
	@BeforeSuite
	public void setup() {
		System.setProperty("webdriver.gecko.driver", ReadConfig.getProperty("firefoxDriver"));
		
		this.driver = new FirefoxDriver();
		this.driver.manage().window().maximize();
	}

}
