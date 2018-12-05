package com.interactive.dictionary.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.interactive.dictionary.base.BaseTest;
import com.interactive.dictionary.pages.LoginPage;
import com.interactive.dictionary.pages.RegistrationPage;
import com.interactive.dictionary.utils.ReadConfig;
import com.interactive.dictionary.utils.Utils;

public class Registration extends BaseTest {

	@Test(priority = 1)
	public void enterLoginPageAndClickSignUp() {
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterPage(ReadConfig.getProperty("url"));
		loginPage.clickSignUp();
		
		RegistrationPage registration = new RegistrationPage(driver);
		Assert.assertTrue(registration.checkConfirmEl());
	}
	
	@Test(priority = 2)
	public void fillRegistrationForm() {
		
		String millis = Long.toString(Utils.getMillis());
		username = ReadConfig.getProperty("username") + millis;
		email = username + ReadConfig.getProperty("emailSuffix");
		password = ReadConfig.getProperty("password");
		
		RegistrationPage registration = new RegistrationPage(driver);
		registration.fillForm(username, email, password);
		registration.clickApply();
		
		Assert.assertEquals(registration.registrationConfirmation(), 
				Utils.getStringFromJson("registration", "successMessage", "expectedData"));
		
		registration.clickGoToLoginPage();
	}
	
}
