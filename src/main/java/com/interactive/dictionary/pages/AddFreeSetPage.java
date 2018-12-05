package com.interactive.dictionary.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddFreeSetPage {

private WebDriver driver;
	
	@FindBy(id = "name")
	private WebElement setNameEl;
	
	String srcLanguageId = "srcLanguage";
	
	String targetLanguageId = "targetLanguage";
	
	String targetSideId = "target-side";
	
	String countdownDurationId = "countdownDuration";
	
	String fileUploadId = "upload";
	
	@FindBy(id = "add-set")
	private WebElement addSetButt;
	
	public AddFreeSetPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setSetName(String setName) {
		setNameEl.sendKeys(setName);
	}
	
	public void chooseSrcLanguage(String element) {
		Select dropdown = new Select(driver.findElement(By.id(srcLanguageId)));
		dropdown.selectByVisibleText(element);
	}
	
	public void chooseTargetLanguage(String element) {
		Select dropdown = new Select(driver.findElement(By.id(targetLanguageId)));
		dropdown.selectByVisibleText(element);
	}
	
	public void chooseCountdownDuration(String element) {
		Select dropdown = new Select(driver.findElement(By.id(countdownDurationId)));
		dropdown.selectByVisibleText(element);
	}
	
	public void setUpload(String path) {
		WebElement fileInput = driver.findElement(By.id(fileUploadId));
		fileInput.sendKeys(path);
	}
	
	public void clickAddSet() {
		addSetButt.click();
	}
	
}
