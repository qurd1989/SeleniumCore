package com.qa.automationexercise.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static Properties prop;
	
	public static Properties getProperties() throws Exception {
		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream  fileInputStream= new FileInputStream("resources\\config.properties");
				prop.load(fileInputStream);
			} catch (IOException e) {
				throw new Exception( "Failed to load config.properties file");
			}
		}
		return prop;
	}
	public static String get(String key) throws Exception {
		return getProperties().getProperty(key);
	}
}
