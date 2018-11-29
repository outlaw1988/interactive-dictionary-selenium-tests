package com.interactive.dictionary.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.interactive.dictionary.utils.ReadConfig;

public class Entry {

	@Test
	public void Test1() {

		System.setProperty("webdriver.gecko.driver", ReadConfig.getProperty("firefoxDriver"));
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(ReadConfig.getProperty("url"));
		
		driver.findElement(By.id("username")).sendKeys(ReadConfig.getProperty("username"));
		driver.findElement(By.id("password")).sendKeys(ReadConfig.getProperty("password"));
		
		driver.findElement(By.id("sign-in")).click();
	}
	
}
