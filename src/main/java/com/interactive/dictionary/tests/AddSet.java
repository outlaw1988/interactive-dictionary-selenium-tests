package com.interactive.dictionary.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.interactive.dictionary.base.BaseTest;
import com.interactive.dictionary.components.Navigation;
import com.interactive.dictionary.pages.AddCategoryPage;
import com.interactive.dictionary.pages.AddSetPage;
import com.interactive.dictionary.pages.CategoriesPage;
import com.interactive.dictionary.pages.SetPage;
import com.interactive.dictionary.pages.WordsPreviewPage;
import com.interactive.dictionary.utils.Utils;

public class AddSet extends BaseTest {
	
	@Test(priority = 1)
	public void addProperCategory() {
		
		Navigation navigation = new Navigation(driver);
		navigation.getIntoCategories();
		
		CategoriesPage categories = new CategoriesPage(driver);
		categories.clickAddCategory();
		
		AddCategoryPage addCategory = new AddCategoryPage(driver);
		addCategory.setCategoryName(Utils.getStringFromJson("AddSet", "categoryName", "testData"));
		addCategory.chooseDefaultLanguage(Utils.getStringFromJson("AddSet", 
										"srcLanguage1", "testData"), "srcLanguage");
		addCategory.chooseDefaultLanguage(Utils.getStringFromJson("AddSet", 
										"targetLanguage1", "testData"), "targetLanguage");
		addCategory.chooseDefaultTargetSide(Utils.getStringFromJson("AddSet", 
										"defaultTargetSide", "testData"));
		addCategory.chooseDefaultCountdownDuration(Utils.getStringFromJson("AddSet", 
										"defaultCountdownDuration", "testData"));
		
		test.info("Click add category");
		
		addCategory.clickAddCategory();
		
		Assert.assertTrue(categories.checkCategoryExist(Utils.getStringFromJson("AddSet", 
														"categoryName", "testData")));
		categories.clickBox(Utils.getStringFromJson("AddSet", "categoryName", "testData"));
	}
	
	@Test(priority = 2)
	public void addSet1() {
		SetPage setPage = new SetPage(driver);
		setPage.clickAddSet();
		
		test.info("Checking labels and languages");
		
		AddSetPage addSetPage = new AddSetPage(driver);
		addSetPage.setSetName(Utils.getStringFromJson("AddSet", "setName", "testData"));
		Assert.assertEquals(addSetPage.checkRightLabel(), Utils.getStringFromJson("AddSet", 
													"targetLabel", "expectedData"));
		Assert.assertEquals(addSetPage.checkRightLanguage(), Utils.getStringFromJson("AddSet", 
													"targetLanguage1", "expectedData"));
		Assert.assertEquals(addSetPage.checkLeftLabel(), Utils.getStringFromJson("AddSet", 
													"sourceLabel", "expectedData"));
		Assert.assertEquals(addSetPage.checkLeftLanguage(), Utils.getStringFromJson("AddSet", 
													"sourceLanguage1", "expectedData"));
	}
	
	@Test(priority = 3)
	public void addSet2() {
		AddSetPage addSetPage = new AddSetPage(driver);
		
		test.info("Choosing file to upload");
		
		addSetPage.setUpload(Utils.getStringFromJson("AddSet", "filePath", "testData"));
		
		test.info("Checking words in the table");
		
		Assert.assertTrue(addSetPage.checkWordsInTable(Utils.getArrayFromJson("AddSet", 
														"srcWords", "testData"), "left"));
		Assert.assertTrue(addSetPage.checkWordsInTable(Utils.getArrayFromJson("AddSet", 
														"targetWords", "testData"), "right"));
	}
	
	@Test(priority = 4)
	public void addSet3() {
		AddSetPage addSetPage = new AddSetPage(driver);
		
		test.info("Changing target language to 2nd mode");
		
		addSetPage.changeTargetLanguage(Utils.getStringFromJson("AddSet", "targetLanguage2", "testData"));
		
		Assert.assertTrue(addSetPage.checkWordsInTable(Utils.getArrayFromJson("AddSet", 
														"srcWords", "testData"), "right"));
		Assert.assertTrue(addSetPage.checkWordsInTable(Utils.getArrayFromJson("AddSet", 
														"targetWords", "testData"), "left"));
		Assert.assertEquals(addSetPage.checkRightLabel(), Utils.getStringFromJson("AddSet", 
						"targetLabel", "expectedData"));
		Assert.assertEquals(addSetPage.checkRightLanguage(), Utils.getStringFromJson("AddSet", 
						"targetLanguage2", "expectedData"));
		Assert.assertEquals(addSetPage.checkLeftLabel(), Utils.getStringFromJson("AddSet", 
						"sourceLabel", "expectedData"));
		Assert.assertEquals(addSetPage.checkLeftLanguage(), Utils.getStringFromJson("AddSet", 
						"sourceLanguage2", "expectedData"));
		
		test.info("Changing target language to 1st mode");
		
		addSetPage.changeTargetLanguage(Utils.getStringFromJson("AddSet", "targetLanguage1", "testData"));
	}
	
	@Test(priority = 5)
	public void addSet4() {
		AddSetPage addSetPage = new AddSetPage(driver);
		
		test.info("Changing target side to 2nd mode");
		
		addSetPage.changeTargetSide(Utils.getStringFromJson("AddSet", "targetSide2", "testData"));
		
		Assert.assertTrue(addSetPage.checkWordsInTable(Utils.getArrayFromJson("AddSet", 
														"srcWords", "testData"), "right"));
		Assert.assertTrue(addSetPage.checkWordsInTable(Utils.getArrayFromJson("AddSet", 
														"targetWords", "testData"), "left"));
		Assert.assertEquals(addSetPage.checkRightLabel(), Utils.getStringFromJson("AddSet", 
														"sourceLabel", "expectedData"));
		Assert.assertEquals(addSetPage.checkRightLanguage(), Utils.getStringFromJson("AddSet", 
														"sourceLanguage1", "expectedData"));
		Assert.assertEquals(addSetPage.checkLeftLabel(), Utils.getStringFromJson("AddSet", 
														"targetLabel", "expectedData"));
		Assert.assertEquals(addSetPage.checkLeftLanguage(), Utils.getStringFromJson("AddSet", 
														"targetLanguage1", "expectedData"));
		
		test.info("Changing target side to 1st mode");
		
		addSetPage.changeTargetSide(Utils.getStringFromJson("AddSet", "targetSide1", "testData"));
		
	}
	
	@Test(priority = 6)
	public void addSet5() {
		AddSetPage addSetPage = new AddSetPage(driver);
		
		test.info("Removing words from table");
		
		ArrayList<String> wordsToRemove = Utils.getArrayFromJson("AddSet", "wordsToRemoveAndAddSrc", 
																 "testData");
		for (String wordToRemove : wordsToRemove) {
			addSetPage.removeWord(wordToRemove);
		}
		
		test.info("Checking table after removal");
		
		Assert.assertTrue(addSetPage.checkWordsInTable(Utils.getArrayFromJson("AddSet", 
														"srcWordsAfterRemove", "testData"), "left"));
		Assert.assertTrue(addSetPage.checkWordsInTable(Utils.getArrayFromJson("AddSet", 
														"targetWordsAfterRemove", "testData"), "right"));
	}
	
	@Test(priority = 7)
	public void addSet6() {
		AddSetPage addSetPage = new AddSetPage(driver);
		
		ArrayList<String> wordsToAddSrc = Utils.getArrayFromJson("AddSet", "wordsToRemoveAndAddSrc", 
				 "testData");
		ArrayList<String> wordsToAddTarget = Utils.getArrayFromJson("AddSet", "wordsToRemoveAndAddTarget", 
				 "testData");
		
		for (int i = 0; i < wordsToAddSrc.size(); i++) {
			addSetPage.addWord(wordsToAddSrc.get(i), wordsToAddTarget.get(i));
		}
		
		Assert.assertTrue(addSetPage.checkWordsInTable(Utils.getArrayFromJson("AddSet", 
												"srcWordsAfterRemoveAndAdd", "testData"), "left"));
		Assert.assertTrue(addSetPage.checkWordsInTable(Utils.getArrayFromJson("AddSet", 
												"targetWordsAfterRemoveAndAdd", "testData"), "right"));
		
		addSetPage.clickAddSet();
	}
	
	@Test(priority = 8)
	public void checkingSetBox() {
		SetPage setPage = new SetPage(driver);
		
		test.info("Checking set exists, words number");
		
		String setName = Utils.getStringFromJson("AddSet", "setName", "testData");
		
		Assert.assertTrue(setPage.checkSetExist(setName));
		Assert.assertEquals(setPage.getWordsNum(setName), Integer.parseInt(Utils.getStringFromJson("AddSet", 
														"wordsNum", "expectedData")));
		setPage.clickBox(setName);
	}
	
	@Test(priority = 9)
	public void checkWordsPreview() {
		
		WordsPreviewPage preview = new WordsPreviewPage(driver);
		ArrayList<String> srcWords = Utils.getArrayFromJson("AddSet", "srcWordsAfterRemoveAndAdd", "testData");
		ArrayList<String> targetWords = Utils.getArrayFromJson("AddSet", "targetWordsAfterRemoveAndAdd", "testData");
		
		preview.checkElementsInTableMatch(srcWords, 1);
		preview.checkElementsInTableMatch(targetWords, 2);
		preview.clickGoBack();
		
	}

}
