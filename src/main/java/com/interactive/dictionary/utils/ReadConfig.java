package com.interactive.dictionary.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	public static String getProperty(String property) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("src/main/resources/config.properties"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return prop.getProperty(property);
	}
		
}
