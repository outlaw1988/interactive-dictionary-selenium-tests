package com.interactive.dictionary.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.interactive.dictionary.reports.ExtentManager;
import com.interactive.dictionary.utils.ReadConfig;

public class BaseTest {
	
	public static WebDriver driver;
	public static String username;
	public static String email;
	public static String password;
	
	public static ExtentReports extent;
	public static ExtentTest pNode;
	public static ExtentTest test;
	
	@SuppressWarnings("rawtypes")
	public static ThreadLocal parentTest = new ThreadLocal();
	
	@BeforeSuite
	public void setupBefore() {
		System.setProperty("webdriver.gecko.driver", ReadConfig.getProperty("firefoxDriver"));
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	
	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.createInstance(ReadConfig.getProperty("reportsOutputDir"));
		extent.setSystemInfo("OS", "Linux");
	}
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void beforeClassExtent() {
		pNode = extent.createTest(getClass().getSimpleName());
		parentTest.set(pNode);
	}
	
	@BeforeMethod
	public void beforeMethodExtent(ITestResult result) {
		test = pNode.createNode(result.getMethod().getMethodName());
	}

	@AfterMethod
	public void getResult(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + 
					"Test case FAILED due to", ExtentColor.RED));
			test.log(Status.FAIL, result.getThrowable());
			
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + 
					"Test case PASSED", ExtentColor.GREEN));
			
		} else {
			
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + 
					"Test case SKIPPED", ExtentColor.YELLOW));
			test.skip(result.getThrowable());
			
		}
		
	}
	
	@AfterSuite
	public void tearDown() {
		extent.flush();
	}
	
}
