package com.screener.app.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.apache.log4j.PropertyConfigurator;

public class LoadConfig {
	static Logger logger = LoggerFactory.getLogger(LoadConfig.class);
	
	public  String CONFIG_PATH = "C:\\Users\\EHRC\\Documents\\jidhan_config\\config\\";

	String username;
	static Properties prop = null;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static HashMap<String,String> objIpMap = new HashMap();
	
	public static String getConfigValue(String key)
	{
		return prop.getProperty(key);
		
	}
	
	public static void setProperties(Properties prop1)
	{
		prop = prop1;
	}
	
	
	public LoadConfig()
	{
		
		
		
	CONFIG_PATH = System.getProperty("org.patharitech.jidhan");
	//CONFIG_PATH= "C:\\ravi\\angular\\strad_config\\config/";
	
		//PropertyConfigurator.configure(CONFIG_PATH+"/"+"log4j"+strEnv+".properties");
	System.out.println("config path.... :- "+CONFIG_PATH);
		logger.info("config path.... :- "+CONFIG_PATH);
		Properties prop = new Properties();
		InputStream input = null;

		
		try {

			input = new FileInputStream(CONFIG_PATH+"/"+"credentials.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			LoadConfig.setProperties(prop);
			prepareIpMap(LoadConfig.getConfigValue("IP_WHITELIST"));
		} catch (IOException ex) {
			logger.error("",ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error("",e);
				}
			}
		}
	}
	
	void prepareIpMap(String ipwhitelist)
	{
		if(ipwhitelist != null)
		{
			String[] iplist = ipwhitelist.split(":");
			for(int i=0;i<iplist.length; i++)
			{
				if(iplist[i].trim().length() > 0)
				{
					objIpMap.put(iplist[i],"true");
				}
			}
		}
		
	}
	
	public static boolean checkIpAddress(String ip)
	{
		System.out.println("System IP = "+ip);
		System.out.println("Checking IP = "+objIpMap.get(ip));
		if(objIpMap.get(ip) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}


}
