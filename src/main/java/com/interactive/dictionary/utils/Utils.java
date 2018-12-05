package com.interactive.dictionary.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
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
	
}
