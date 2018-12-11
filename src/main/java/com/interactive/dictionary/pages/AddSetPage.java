package com.interactive.dictionary.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddSetPage {

	private WebDriver driver;
	
	@FindBy(id = "name")
	private WebElement setNameEl;
	
	private final String targetLanguageId = "target-language";
	@FindBy(id = targetLanguageId)
	private WebElement targetLanguageEl;
	
	private final String targetSideId = "target-side";
	@FindBy(id = targetSideId)
	private WebElement targetSideEl;
	
	@FindBy(id = "countdownDuration")
	private WebElement countdownDurationEl;
	
	@FindBy(id = "separator")
	private WebElement separatorEl;
	
	@FindBy(id = "add-word")
	private WebElement addWordEl;
	
	@FindBy(id = "add-set")
	private WebElement addSetEl;
	
	@FindBy(id = "go-back")
	private WebElement goBackEl;
	
	@FindBy(id = "right-label")
	private WebElement rightLabelEl;
	
	@FindBy(id = "right-lan")
	private WebElement rightLanguageEl;
	
	@FindBy(id = "left-label")
	private WebElement leftLabelEl;
	
	@FindBy(id = "left-lan")
	private WebElement leftLanguageEl;
	
	@FindBy(id = "upload")
	private WebElement uploadEl;
	
	@FindBy(id = "name.errors")
	private WebElement nameErrorsEl;
	
	public AddSetPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setSetName(String setName) {
		setNameEl.sendKeys(setName);
	}
	
	public void clearSetName() {
		setNameEl.clear();
	}
	
	public void putWordsIntoTable(ArrayList<String> words, String side) {
		int size = words.size();
		for (int i = 1; i <= size; i++) {
			
			String fieldId = "";
			
			if (side.equals("left")) {
				fieldId = "left_field_" + i;
			} else if (side.equals("right")) {
				fieldId = "right_field_" + i;
			}
			
			if (driver.findElements(By.id(fieldId)).size() == 0) {
				clickAddWord();
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
	
	public void clickGoBack() {
		goBackEl.click();
	}
	
	public String checkRightLabel() {
		return rightLabelEl.getText();
	}
	
	public String checkRightLanguage() {
		return rightLanguageEl.getText();
	}
	
	public String checkLeftLabel() {
		return leftLabelEl.getText();
	}
	
	public String checkLeftLanguage() {
		return leftLanguageEl.getText();
	}
	
	public void setUpload(String path) {
		uploadEl.sendKeys(path);
	}
	
	public String getNameError() {
		return nameErrorsEl.getText();
	}
	
	public boolean checkWordsInTable(ArrayList<String> words, String side) {
		
		int size = words.size();
		int offset = 3;
		
		for (int i = offset; i < (size + offset); i++) {
			String word = words.get(i - offset);
			String fieldXpath = "";
			
			if (side.equals("left")) {
				fieldXpath = "//table[@id='set_def_table']/tbody/tr[" + i + "]/td[1]/input";
			} else if (side.equals("right")) {
				fieldXpath = "//table[@id='set_def_table']/tbody/tr[" + i + "]/td[2]/input";
			}
			
			String wordFromField = driver.findElement(By.xpath(fieldXpath)).getAttribute("value");
			
			if (!wordFromField.equals(word)) {
				return false;
			}
		}
		
		return true;
	}
	
	public void changeTargetLanguage(String element) {
		Select dropdown = new Select(driver.findElement(By.id(targetLanguageId)));
		dropdown.selectByVisibleText(element);
	}
	
	public void changeTargetSide(String element) {
		Select dropdown = new Select(driver.findElement(By.id(targetSideId)));
		dropdown.selectByVisibleText(element);
	}
	
	public void removeWord(String wordToRemove) {
		
		String leftFieldXpath;
		String rightFieldXpath;
		
		int size = getTableSize();
		
		for (int i = 3; i <= size; i++) {
			leftFieldXpath = "//table[@id='set_def_table']/tbody/tr[" + i + "]/td[1]/input";
			rightFieldXpath = "//table[@id='set_def_table']/tbody/tr[" + i + "]/td[2]/input";
			
			WebElement leftField = driver.findElement(By.xpath(leftFieldXpath));
			WebElement rightField = driver.findElement(By.xpath(rightFieldXpath));
			
			if (leftField.getAttribute("value").equals(wordToRemove) || 
				rightField.getAttribute("value").equals(wordToRemove)) {
				
				String removeElXpath = "//table[@id='set_def_table']/tbody/tr[" + i + "]/td[2]/img";
				driver.findElement(By.xpath(removeElXpath)).click();
				break;
			}
		}
	}
	
	public void addWord(String leftWord, String rightWord) {
		
		int size = getTableSize();
		clickAddWord();
		
		String leftFieldXpath = "//table[@id='set_def_table']/tbody/tr[" + (size + 1) + "]/td[1]/input";
		String rightFieldXpath = "//table[@id='set_def_table']/tbody/tr[" + (size + 1) + "]/td[2]/input";
		
		driver.findElement(By.xpath(leftFieldXpath)).sendKeys(leftWord);
		driver.findElement(By.xpath(rightFieldXpath)).sendKeys(rightWord);
	}
	
	private int getTableSize() {
		
		int i = 3;
		
		String leftFieldXpath;
		String rightFieldXpath;
		
		while(true) {
			leftFieldXpath = "//table[@id='set_def_table']/tbody/tr[" + i + "]/td[1]/input";
			rightFieldXpath = "//table[@id='set_def_table']/tbody/tr[" + i + "]/td[2]/input";
			
			boolean leftFieldNotExist = driver.findElements(By.xpath(leftFieldXpath)).size() == 0;
			boolean rightFieldNotExist = driver.findElements(By.xpath(rightFieldXpath)).size() == 0;
			
			if (leftFieldNotExist || rightFieldNotExist) {
				return (i - 1);
			}
			
			i++;
		}
	}
	
}
