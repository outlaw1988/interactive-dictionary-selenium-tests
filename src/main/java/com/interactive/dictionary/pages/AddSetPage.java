package com.interactive.dictionary.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddSetPage {

	private WebDriver driver;
	
	@FindBy(id = "name")
	private WebElement setNameEl;
	
	@FindBy(id = "target-language")
	private WebElement targetLanguageEl;
	
	@FindBy(id = "target-side")
	private WebElement targetSideEl;
	
	@FindBy(id = "countdownDuration")
	private WebElement countdownDurationEl;
	
	@FindBy(id = "separator")
	private WebElement separatorEl;
	
	@FindBy(id = "add-word")
	private WebElement addWordEl;
	
	@FindBy(id = "add-set")
	private WebElement addSetEl;
	
	public AddSetPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setSetName(String setName) {
		setNameEl.sendKeys(setName);
	}
	
	public void putWordsIntoTable(ArrayList<String> words, String side) {
		int size = words.size();
		for (int i = 1; i <= size; i++) {
			
			if (i > 10) {
				clickAddWord();
			}
			
			String fieldId = "";
			
			if (side.equals("left")) {
				fieldId = "left_field_" + i;
			} else if (side.equals("right")) {
				fieldId = "right_field_" + i;
			}
			
			driver.findElement(By.id(fieldId)).sendKeys(words.get(i - 1));
		}
	}
	
	public void clickAddWord() {
		addWordEl.click();
	}
	
	public void clickAddSet() {
		addSetEl.click();
	}
	
}
