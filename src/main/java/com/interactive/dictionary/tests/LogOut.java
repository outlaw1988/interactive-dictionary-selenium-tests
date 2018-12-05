package com.interactive.dictionary.tests;

import org.testng.annotations.Test;

import com.interactive.dictionary.base.BaseTest;
import com.interactive.dictionary.components.Navigation;

public class LogOut extends BaseTest {

	@Test(priority = 1)
	public void logOut() {
		Navigation navigation = new Navigation(driver);
		navigation.clickLogOut();
	}
	
}
