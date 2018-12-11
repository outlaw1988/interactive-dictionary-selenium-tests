package com.interactive.dictionary.pages;

import java.util.ArrayList;

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
	
	@FindBy(id = "add-word")
	private WebElement addWordEl;
	
	@FindBy(id = "left-label")
	private WebElement leftLabelEl;
	
	@FindBy(id = "right-label")
	private WebElement rightLabelEl;
	
	private final String srcLanguageId = "srcLanguage";
	
	private final String targetLanguageId = "targetLanguage";
	
	private final String targetSideId = "target-side";
	
	private final String countdownDurationId = "countdownDuration";
	
	private final String fileUploadId = "upload";
	
	private final String tableXpath = "//table[@id='set_def_table']";
	
	@FindBy(id = "add-set")
	private WebElement addSetButt;
	
	@FindBy(id = "name.errors")
	private WebElement nameErrorEl;
	
	@FindBy(id = "go-back")
	private WebElement goBackEl;
	
	@FindBy(id = "srcLanguage.errors")
	private WebElement srcLanguageErrorsEl;
	
	@FindBy(id = "targetLanguage.errors")
	private WebElement targetLanguageErrorsEl;
	
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
	
	public void chooseTargetSide(String element) {
		Select dropdown = new Select(driver.findElement(By.id(targetSideId)));
		dropdown.selectByVisibleText(element);
	}
	
	public void setUpload(String path) {
		WebElement fileInput = driver.findElement(By.id(fileUploadId));
		fileInput.sendKeys(path);
	}
	
	public void clickAddSet() {
		addSetButt.click();
	}
	
	public String getErrorName() {
		return nameErrorEl.getText();
	}
	
	public String getErrorSrcLanguage() {
		return srcLanguageErrorsEl.getText();
	}
	
	public String getErrorTargetLanguage() {
		return targetLanguageErrorsEl.getText();
	}
	
	public void clearSetName() {
		setNameEl.clear();
	}
	
	public void clickGoBack() {
		goBackEl.click();
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
	
	public String getLeftLabel() {
		return leftLabelEl.getText();
	}
	
	public String getRightLabel() {
		return rightLabelEl.getText();
	}
	
	public void addWord(String leftWord, String rightWord) {
		
		int size = getTableSize();
		clickAddWord();
		
		String leftFieldXpath = tableXpath + "/tbody/tr[" + (size + 1) + "]/td[2]/input";
		String rightFieldXpath = tableXpath + "/tbody/tr[" + (size + 1) + "]/td[3]/input";
		
		driver.findElement(By.xpath(leftFieldXpath)).sendKeys(leftWord);
		driver.findElement(By.xpath(rightFieldXpath)).sendKeys(rightWord);
	}
	
	public void removeWord(String wordToRemove) {
		
		String leftFieldXpath;
		String rightFieldXpath;
		
		int size = getTableSize();
		
		for (int i = 2; i <= size; i++) {
			leftFieldXpath = tableXpath + "/tbody/tr[" + i + "]/td[2]/input";
			rightFieldXpath = tableXpath + "/tbody/tr[" + i + "]/td[3]/input";
			
			WebElement leftField = driver.findElement(By.xpath(leftFieldXpath));
			WebElement rightField = driver.findElement(By.xpath(rightFieldXpath));
			
			if (leftField.getAttribute("value").equals(wordToRemove) || 
				rightField.getAttribute("value").equals(wordToRemove)) {
				
				String removeElXpath = tableXpath + "/tbody/tr[" + i + "]/td[3]/img";
				driver.findElement(By.xpath(removeElXpath)).click();
				break;
			}
		}
	}
	
	public boolean checkWordsInTable(ArrayList<String> words, String side) {
		
		int size = words.size();
		int offset = 2;
		
		for (int i = offset; i < (size + offset); i++) {
			String word = words.get(i - offset);
			String fieldXpath = "";
			
			if (side.equals("left")) {
				fieldXpath = tableXpath + "/tbody/tr[" + i + "]/td[2]/input";
			} else if (side.equals("right")) {
				fieldXpath = tableXpath + "/tbody/tr[" + i + "]/td[3]/input";
			}
			
			String wordFromField = driver.findElement(By.xpath(fieldXpath)).getAttribute("value");
			
			if (!wordFromField.equals(word)) {
				return false;
			}
		}
		
		return true;
	}
	
	private int getTableSize() {
		
		int i = 2;
		
		String leftFieldXpath;
		String rightFieldXpath;
		
		while(true) {
			leftFieldXpath = tableXpath + "/tbody/tr[" + i + "]/td[2]/input";
			rightFieldXpath = tableXpath + "/tbody/tr[" + i + "]/td[3]/input";
			
			boolean leftFieldNotExist = driver.findElements(By.xpath(leftFieldXpath)).size() == 0;
			boolean rightFieldNotExist = driver.findElements(By.xpath(rightFieldXpath)).size() == 0;
			
			if (leftFieldNotExist || rightFieldNotExist) {
				return (i - 1);
			}
			
			i++;
		}
	}
	
}
