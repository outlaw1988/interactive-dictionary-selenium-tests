package com.interactive.dictionary.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.interactive.dictionary.base.BaseTest;
import com.interactive.dictionary.components.Navigation;
import com.interactive.dictionary.pages.AddFreeSetPage;
import com.interactive.dictionary.pages.FreeSetsPage;
import com.interactive.dictionary.utils.Utils;

public class AddFreeSet extends BaseTest {
	
	private ArrayList<String> srcWords;
	private ArrayList<String> targetWords;
	
	@Test(priority = 1)
	public void freeSetInit() {
		
		Navigation navigation = new Navigation(driver);
		navigation.getIntoFreeSets();
		
		FreeSetsPage freeSetsPage = new FreeSetsPage(driver);
		freeSetsPage.addFreeSet();
		
		AddFreeSetPage addFreeSetPage = new AddFreeSetPage(driver);
		
		test.info("Adding set name");
		String setName = Utils.getStringFromJson("AddFreeSet", "setName", "testData");
		addFreeSetPage.setSetName(setName);
		addFreeSetPage.chooseSrcLanguage(Utils.getStringFromJson("AddFreeSet", 
											"srcLanguage", "testData"));
		addFreeSetPage.chooseTargetLanguage(Utils.getStringFromJson("AddFreeSet", 
											"targetLanguage", "testData"));
		
		test.info("Checking labels");
		Assert.assertEquals(addFreeSetPage.getLeftLabel(), Utils.getStringFromJson("AddFreeSet", 
							"sourceLabel", "expectedData"));
		Assert.assertEquals(addFreeSetPage.getRightLabel(), Utils.getStringFromJson("AddFreeSet", 
							"targetLabel", "expectedData"));
		
	}
	
	@Test(priority = 2)
	public void enterWords() {
		AddFreeSetPage addFreeSetPage = new AddFreeSetPage(driver);
		srcWords = Utils.getArrayFromJson("AddFreeSet", "srcWords", "testData");
		targetWords = Utils.getArrayFromJson("AddFreeSet", "targetWords", "testData");
		
		test.info("Passing words into table");
		
		addFreeSetPage.putWordsIntoTable(srcWords, "left");
		addFreeSetPage.putWordsIntoTable(targetWords, "right");
	}
	
	@Test(priority = 3)
	public void changeTargetSide() {
		AddFreeSetPage addFreeSetPage = new AddFreeSetPage(driver);
		
		test.info("Changing target side to 2nd mode");
		addFreeSetPage.chooseTargetSide("left");
		
		test.info("Checking labels");
		Assert.assertEquals(addFreeSetPage.getLeftLabel(), Utils.getStringFromJson("AddFreeSet", 
							"targetLabel", "expectedData"));
		Assert.assertEquals(addFreeSetPage.getRightLabel(), Utils.getStringFromJson("AddFreeSet", 
							"sourceLabel", "expectedData"));
		
		test.info("Checking words in table");
		Assert.assertTrue(addFreeSetPage.checkWordsInTable(srcWords, "right"));
		Assert.assertTrue(addFreeSetPage.checkWordsInTable(targetWords, "left"));
		
		test.info("Changing target side to 1st mode");
		addFreeSetPage.chooseTargetSide("right");
	}
	
	@Test(priority = 4)
	public void removeWords() {
		AddFreeSetPage addFreeSetPage = new AddFreeSetPage(driver);
		
		test.info("Removing words from table");
		ArrayList<String> wordsToRemove = Utils.getArrayFromJson("AddFreeSet", "wordsToRemoveAndAddSrc", 
																 "testData");
		for (String wordToRemove : wordsToRemove) {
			addFreeSetPage.removeWord(wordToRemove);
		}
		
		test.info("Checking table after removal");
		srcWords = Utils.getArrayFromJson("AddFreeSet", "srcWordsAfterRemove", "testData");
		targetWords = Utils.getArrayFromJson("AddFreeSet", "targetWordsAfterRemove", "testData");
		Assert.assertTrue(addFreeSetPage.checkWordsInTable(srcWords, "left"));
		Assert.assertTrue(addFreeSetPage.checkWordsInTable(targetWords, "right"));
	}
	
	@Test(priority = 5)
	public void addNewWords() {
		AddFreeSetPage addFreeSetPage = new AddFreeSetPage(driver);
		
		ArrayList<String> srcWordsToAdd = Utils.getArrayFromJson("AddFreeSet", 
																 "wordsToRemoveAndAddSrc", "testData");
		ArrayList<String> targetWordsToAdd = Utils.getArrayFromJson("AddFreeSet", 
																 "wordsToRemoveAndAddTarget", "testData");
		
		test.info("Adding words");
		for (int i = 0; i < srcWordsToAdd.size(); i++) {
			addFreeSetPage.addWord(srcWordsToAdd.get(i), targetWordsToAdd.get(i));
		}
		
		test.info("Checking table after adding");
		srcWords = Utils.getArrayFromJson("AddFreeSet", "srcWordsAfterRemoveAndAdd", "testData");
		targetWords = Utils.getArrayFromJson("AddFreeSet", "targetWordsAfterRemoveAndAdd", "testData");
		Assert.assertTrue(addFreeSetPage.checkWordsInTable(srcWords, "left"));
		Assert.assertTrue(addFreeSetPage.checkWordsInTable(targetWords, "right"));
		
		addFreeSetPage.clickAddSet();
	}
	
	@Test(priority = 6)
	public void checkSet() {
		FreeSetsPage freeSetsPage = new FreeSetsPage(driver);
		
		String setName = Utils.getStringFromJson("AddFreeSet", "setName", "testData");
		
		test.info("Checking set exists");
		Assert.assertTrue(freeSetsPage.checkSetExist(setName));
		
		test.info("Checking words number");
		Assert.assertEquals(freeSetsPage.getWordsNum(setName), 
				Integer.parseInt(Utils.getStringFromJson("AddFreeSet", "wordsNum", "expectedData")));
	
	}

}
