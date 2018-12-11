package com.interactive.dictionary.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.interactive.dictionary.base.BaseTest;
import com.interactive.dictionary.pages.AddSetPage;
import com.interactive.dictionary.pages.SetPage;
import com.interactive.dictionary.utils.Utils;

public class AddSet2 extends BaseTest {

	@Test(priority = 1)
	public void emptySetName() {
		SetPage setPage = new SetPage(driver);
		setPage.clickAddSet();
		
		String urlExpected = driver.getCurrentUrl();
		
		AddSetPage addSetPage = new AddSetPage(driver);
		
		test.info("Empty set name");
		addSetPage.clickAddSet();
		
		test.info("Checking proper url");
		Assert.assertEquals(driver.getCurrentUrl(), urlExpected);
	}
	
	@Test(priority = 2)
	public void emptyWordsList() {
		AddSetPage addSetPage = new AddSetPage(driver);
		String setName = Utils.getStringFromJson("AddSet2", "setName1", "testData");
		addSetPage.setSetName(setName);
		
		test.info("Empty words list");
		addSetPage.clickAddSet();
		
		Assert.assertEquals(addSetPage.getNameError(), 
					Utils.getStringFromJson("AddSet2", "emptyWordsErrorMessage", "expectedData"));
	}
	
	@Test(priority = 3)
	public void setAlreadyExists() {
		AddSetPage addSetPage = new AddSetPage(driver);
		String setName = Utils.getStringFromJson("AddSet2", "setName2", "testData");
		addSetPage.clearSetName();
		addSetPage.setSetName(setName);
		
		test.info("Upload txt file");
		addSetPage.setUpload(Utils.getStringFromJson("AddSet", "filePath", "testData"));
		
		test.info("Set already exists");
		addSetPage.clickAddSet();
		
		Assert.assertEquals(addSetPage.getNameError(), 
						Utils.getStringFromJson("AddSet2", "setExistsErrorMessage", "expectedData"));
		addSetPage.clickGoBack();
	}
	
}
