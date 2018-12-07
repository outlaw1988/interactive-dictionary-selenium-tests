package com.interactive.dictionary.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.interactive.dictionary.base.BaseTest;
import com.interactive.dictionary.pages.AddCategoryPage;
import com.interactive.dictionary.pages.CategoriesPage;
import com.interactive.dictionary.utils.Utils;

public class AddCategory extends BaseTest {

	@Test(priority = 1)
	public void getIntoAddCategory() {
		CategoriesPage categories = new CategoriesPage(driver);
		test.info("Clicking add category");
		categories.clickAddCategory();
	}
	
	@Test(priority = 2)
	public void emptyCategoryName() {
		AddCategoryPage addCategory = new AddCategoryPage(driver);
		test.info("Set empty category name");
		addCategory.setCategoryName(Utils.getStringFromJson("AddCategory", 
									"categoryNameEmpty", "testData"));
		addCategory.chooseDefaultLanguage(Utils.getStringFromJson("AddCategory", 
									"srcLanguage", "testData"), "srcLanguage");
		addCategory.chooseDefaultLanguage(Utils.getStringFromJson("AddCategory", 
									"targetLanguage", "testData"), "targetLanguage");
		addCategory.clickAddCategory();
		
		test.info("Checking proper url");
		Assert.assertEquals(driver.getCurrentUrl(), 
				Utils.getStringFromJson("AddCategory", "urlConfirm", "expectedData"));
		
		addCategory.clickGoBack();
	}
	
	@Test(priority = 3)
	public void longCategoryName() {
		
		CategoriesPage categories = new CategoriesPage(driver);
		test.info("Clicking add category");
		categories.clickAddCategory();
		
		AddCategoryPage addCategory = new AddCategoryPage(driver);
		test.info("Set long category name");
		addCategory.setCategoryName(Utils.getStringFromJson("AddCategory", 
							"categoryNameLong", "testData"));
		addCategory.chooseDefaultLanguage(Utils.getStringFromJson("AddCategory", 
							"srcLanguage", "testData"), "srcLanguage");
		addCategory.chooseDefaultLanguage(Utils.getStringFromJson("AddCategory", 
							"targetLanguage", "testData"), "targetLanguage");
		addCategory.clickAddCategory();
		
		test.info("Getting error message");
		Assert.assertEquals(addCategory.getErrorName(), Utils.getStringFromJson("AddCategory", 
							"errorNameMessageSize", "expectedData"));
		
		addCategory.clickGoBack();
	}
	
	@Test(priority = 4)
	public void createCategoryWhichAlreadyExist() {
		
		test.info("Adding first category");
		
		CategoriesPage categories = new CategoriesPage(driver);
		categories.clickAddCategory();
		
		AddCategoryPage addCategory = new AddCategoryPage(driver);
		
		addCategory.setCategoryName(Utils.getStringFromJson("AddCategory", 
				"categoryNameExist", "testData"));
		addCategory.chooseDefaultLanguage(Utils.getStringFromJson("AddCategory", 
				"srcLanguage", "testData"), "srcLanguage");
		addCategory.chooseDefaultLanguage(Utils.getStringFromJson("AddCategory", 
				"targetLanguage", "testData"), "targetLanguage");
		addCategory.clickAddCategory();
		
		test.info("Adding second category");
		
		categories.clickAddCategory();
		
		addCategory.setCategoryName(Utils.getStringFromJson("AddCategory", 
				"categoryNameExist", "testData"));
		addCategory.chooseDefaultLanguage(Utils.getStringFromJson("AddCategory", 
				"srcLanguage", "testData"), "srcLanguage");
		addCategory.chooseDefaultLanguage(Utils.getStringFromJson("AddCategory", 
				"targetLanguage", "testData"), "targetLanguage");
		addCategory.clickAddCategory();
		
		test.info("Getting error message");
		
		Assert.assertEquals(addCategory.getErrorName(), Utils.getStringFromJson("AddCategory", 
				"errorNameMessageExist", "expectedData"));
		
		addCategory.clickGoBack();
	}
	
	@Test(priority = 5)
	public void sameLanguages() {
		CategoriesPage categories = new CategoriesPage(driver);
		categories.clickAddCategory();
		
		AddCategoryPage addCategory = new AddCategoryPage(driver);
		addCategory.setCategoryName(Utils.getStringFromJson("AddCategory", "categoryName", "testData"));
		
		test.info("Adding same languages");
		
		addCategory.chooseDefaultLanguage(Utils.getStringFromJson("AddCategory", 
										"srcLanguage", "testData"), "srcLanguage");
		addCategory.chooseDefaultLanguage(Utils.getStringFromJson("AddCategory", 
										"srcLanguage", "testData"), "targetLanguage");
		addCategory.clickAddCategory();
		
		test.info("Getting error message");
		
		Assert.assertEquals(addCategory.getErrorDefTargetLan(), Utils.getStringFromJson("AddCategory", 
										"errorLanguagesDifferent", "expectedData"));
		
		addCategory.clickGoBack();
	}
	
	@Test(priority = 6)
	public void languagesNull() {
		CategoriesPage categories = new CategoriesPage(driver);
		categories.clickAddCategory();
		
		test.info("No languages added");
		
		AddCategoryPage addCategory = new AddCategoryPage(driver);
		addCategory.setCategoryName(Utils.getStringFromJson("AddCategory", "categoryName", "testData"));
		addCategory.clickAddCategory();
		
		Assert.assertEquals(addCategory.getErrorDefSrcLan(), Utils.getStringFromJson("AddCategory", 
							"errorLanguagesNull", "expectedData"));
		
		Assert.assertEquals(addCategory.getErrorDefTargetLan(), Utils.getStringFromJson("AddCategory", 
							"errorLanguagesNull", "expectedData"));
		
		addCategory.clickGoBack();
	}
	
	@Test(priority = 7)
	public void addProperCategory() {
		CategoriesPage categories = new CategoriesPage(driver);
		categories.clickAddCategory();
		
		test.info("Proper category added");
		
		AddCategoryPage addCategory = new AddCategoryPage(driver);
		addCategory.setCategoryName(Utils.getStringFromJson("AddCategory", "categoryName", "testData"));
		addCategory.chooseDefaultLanguage(Utils.getStringFromJson("AddCategory", 
										"srcLanguage", "testData"), "srcLanguage");
		addCategory.chooseDefaultLanguage(Utils.getStringFromJson("AddCategory", 
										"targetLanguage", "testData"), "targetLanguage");
		addCategory.chooseDefaultTargetSide(Utils.getStringFromJson("AddCategory", 
										"defaultTargetSide", "testData"));
		addCategory.chooseDefaultCountdownDuration(Utils.getStringFromJson("AddCategory", 
										"defaultCountdownDuration", "testData"));
		addCategory.clickAddCategory();
	}
	
}
