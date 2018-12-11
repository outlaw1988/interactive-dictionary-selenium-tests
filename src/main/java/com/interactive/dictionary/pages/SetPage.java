package com.interactive.dictionary.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SetPage {

	private WebDriver driver;
	
	@FindBy(id = "add-set")
	private WebElement addSetEl;
	
	public SetPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickAddSet() {
		addSetEl.click();
	}
	
	public boolean checkSetExist(String setName) {
		String xpath = "//span[contains(text(),'" + setName + "')]";
		return driver.findElements(By.xpath(xpath)).size() > 0;
	}
	
	public void clickBox(String setName) {
		String xpath = "//span[contains(text(),'" + setName + "')]";
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public int getWordsNum(String setName) {
		
		String xpath = "//span[contains(text(),'" + setName + "')]//parent::a/parent::div/parent::div/span[1]/span";
		
		return Integer.parseInt(driver.findElement(By.xpath(xpath)).getText());
	}
	
	public String getLastResult(String setName) {
		
		String xpath = "//span[contains(text(),'" + setName + "')]//parent::a/parent::div/parent::div/span[2]/span";
	
		return driver.findElement(By.xpath(xpath)).getText();
	}
	
	public String getBestResult(String setName) {
		
		String xpath = "//span[contains(text(),'" + setName + "')]//parent::a/parent::div/parent::div/span[3]/span";
		
		return driver.findElement(By.xpath(xpath)).getText();
		
	}
}
