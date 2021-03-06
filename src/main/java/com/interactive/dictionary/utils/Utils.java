package com.interactive.dictionary.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Utils {

	public static long getMillis() {
		DateTime time = new DateTime();
		return time.getMillis();
	}
	
	public static String getStringFromJson(String param1, String param2, String mode) {
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObj;
		String result = "";
		String fileName = "";
		
		if (mode.equals("expectedData")) {
			
			fileName = ReadConfig.getProperty("expectedData");
			
		} else if (mode.equals("testData")) {
			
			fileName = ReadConfig.getProperty("testData");
		}
        
        try {
            Object obj = parser.parse(new FileReader(fileName));
            jsonObj = (JSONObject) obj;
            
            JSONObject jsonObj2 = (JSONObject)jsonObj.get(param1);
            result = (String)jsonObj2.get(param2);
        }
        catch(FileNotFoundException e) { e.printStackTrace(); }
        catch(IOException e) { e.printStackTrace(); }
        catch(ParseException e) { e.printStackTrace(); }
        catch(Exception e) { e.printStackTrace(); }
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> getArrayFromJson(String param1, String param2, String mode) {
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObj;
		ArrayList<String> result = new ArrayList<String>();
		String fileName = "";
		
		if (mode.equals("expectedData")) {
			
			fileName = ReadConfig.getProperty("expectedData");
			
		} else if (mode.equals("testData")) {
			
			fileName = ReadConfig.getProperty("testData");
		}
        
        try {
            Object obj = parser.parse(new FileReader(fileName));
            jsonObj = (JSONObject) obj;
            
            JSONObject jsonObj2 = (JSONObject)jsonObj.get(param1);
            result = (ArrayList<String>)jsonObj2.get(param2);

        }
        catch(FileNotFoundException e) { e.printStackTrace(); }
        catch(IOException e) { e.printStackTrace(); }
        catch(ParseException e) { e.printStackTrace(); }
        catch(Exception e) { e.printStackTrace(); }
		
		return result;
	}
	
	public static void waitUntilVisibleById(WebDriver driver, String id) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}
	
	public static void waitUntilVisibleByXpath(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	
	public static String captureScreenshot(WebDriver driver, String ssName) {
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		String outputName = ReadConfig.getProperty("screenshotsOutputDir") + ssName + "_" +
				timeStamp + ".png";
		
		try {
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			
			File destFile = new File(outputName);
			FileUtils.copyFile(source, destFile);
			
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		return outputName;
	}
	
}
