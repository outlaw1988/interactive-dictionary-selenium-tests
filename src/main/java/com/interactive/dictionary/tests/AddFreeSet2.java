package com.interactive.dictionary.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.interactive.dictionary.base.BaseTest;
import com.interactive.dictionary.pages.AddFreeSetPage;
import com.interactive.dictionary.pages.FreeSetsPage;
import com.interactive.dictionary.utils.Utils;

public class AddFreeSet2 extends BaseTest {
	
	@Test(priority = 1)
	public void emptySetName() {
		FreeSetsPage freeSetsPage = new FreeSetsPage(driver);
		freeSetsPage.addFreeSet();
		
		String urlExpected = driver.getCurrentUrl();
		
		test.info("Add empty set name");
		AddFreeSetPage addFreeSetPage = new AddFreeSetPage(driver);
		addFreeSetPage.clickAddSet();
		
		Assert.assertEquals(driver.getCurrentUrl(), urlExpected);
	}
	
	@Test(priority = 2)
	public void emptyWordsList() {
		AddFreeSetPage addFreeSetPage = new AddFreeSetPage(driver);
		
		String setName = Utils.getStringFromJson("AddFreeSet2", "setName1", "testData");
		addFreeSetPage.setSetName(setName);
		addFreeSetPage.chooseSrcLanguage(Utils.getStringFromJson("AddFreeSet2", 
											 "srcLanguage", "testData"));
		addFreeSetPage.chooseTargetLanguage(Utils.getStringFromJson("AddFreeSet2", 
											"targetLanguage", "testData"));
		
		addFreeSetPage.clickAddSet();
		
		test.info("Empty words list");
		Assert.assertEquals(addFreeSetPage.getErrorName(), Utils.getStringFromJson("AddFreeSet2", 
														"emptyWordsErrorMessage", "expectedData"));
		addFreeSetPage.clickGoBack();
	}
	
	@Test(priority = 3)
	public void setAlreadyExist() {
		FreeSetsPage freeSetsPage = new FreeSetsPage(driver);
		freeSetsPage.addFreeSet();
		
		AddFreeSetPage addFreeSetPage = new AddFreeSetPage(driver);
		String setName = Utils.getStringFromJson("AddFreeSet2", "setName2", "testData");
		
		test.info("Setting set name - already exist");
		addFreeSetPage.setSetName(setName);
		
		addFreeSetPage.chooseSrcLanguage(Utils.getStringFromJson("AddFreeSet2", 
				 "srcLanguage", "testData"));
		addFreeSetPage.chooseTargetLanguage(Utils.getStringFromJson("AddFreeSet2", 
				 "targetLanguage", "testData"));
		
		ArrayList<String> srcWords = Utils.getArrayFromJson("AddFreeSet2", "srcWords", "testData");
		ArrayList<String> targetWords = Utils.getArrayFromJson("AddFreeSet2", "targetWords", "testData");
		
		test.info("Putting words into table");
		addFreeSetPage.putWordsIntoTable(srcWords, "left");
		addFreeSetPage.putWordsIntoTable(targetWords, "right");
		
		test.info("Clicking add set");
		addFreeSetPage.clickAddSet();
		
		Assert.assertEquals(addFreeSetPage.getErrorName(), Utils.getStringFromJson("AddFreeSet2", 
							"setExistsErrorMessage", "expectedData"));
		addFreeSetPage.clickGoBack();
	}
	
	@Test(priority = 4)
	public void languagesNull() {
		FreeSetsPage freeSetsPage = new FreeSetsPage(driver);
		freeSetsPage.addFreeSet();
		
		AddFreeSetPage addFreeSetPage = new AddFreeSetPage(driver);
		String setName = Utils.getStringFromJson("AddFreeSet2", "setName1", "testData");
		addFreeSetPage.setSetName(setName);
		
		ArrayList<String> srcWords = Utils.getArrayFromJson("AddFreeSet2", "srcWords", "testData");
		ArrayList<String> targetWords = Utils.getArrayFromJson("AddFreeSet2", "targetWords", "testData");
		
		test.info("Putting words into table");
		addFreeSetPage.putWordsIntoTable(srcWords, "left");
		addFreeSetPage.putWordsIntoTable(targetWords, "right");
		
		test.info("Clicking add set");
		addFreeSetPage.clickAddSet();
		
		Assert.assertEquals(addFreeSetPage.getErrorSrcLanguage(), Utils.getStringFromJson("AddFreeSet2", 
							"emptyLanguageError", "expectedData"));
		Assert.assertEquals(addFreeSetPage.getErrorTargetLanguage(), Utils.getStringFromJson("AddFreeSet2", 
							"emptyLanguageError", "expectedData"));
		
		addFreeSetPage.clickGoBack();
	}
	
	@Test(priority = 5)
	public void sameLanguages() {
		FreeSetsPage freeSetsPage = new FreeSetsPage(driver);
		freeSetsPage.addFreeSet();
		
		AddFreeSetPage addFreeSetPage = new AddFreeSetPage(driver);
		String setName = Utils.getStringFromJson("AddFreeSet2", "setName1", "testData");
		addFreeSetPage.setSetName(setName);
		
		test.info("Adding same languages");
		addFreeSetPage.chooseSrcLanguage(Utils.getStringFromJson("AddFreeSet2", 
				 "srcLanguage", "testData"));
		addFreeSetPage.chooseTargetLanguage(Utils.getStringFromJson("AddFreeSet2", 
				 "srcLanguage", "testData"));
		
		ArrayList<String> srcWords = Utils.getArrayFromJson("AddFreeSet2", "srcWords", "testData");
		ArrayList<String> targetWords = Utils.getArrayFromJson("AddFreeSet2", "targetWords", "testData");
		
		test.info("Putting words into table");
		addFreeSetPage.putWordsIntoTable(srcWords, "left");
		addFreeSetPage.putWordsIntoTable(targetWords, "right");
		
		test.info("Clicking add set");
		addFreeSetPage.clickAddSet();
		
		Assert.assertEquals(addFreeSetPage.getErrorTargetLanguage(), Utils.getStringFromJson("AddFreeSet2", 
							"sameLanguagesError", "expectedData"));
		addFreeSetPage.clickGoBack();
	}

}
