package com.comcast.crm.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtility {
	
	public String getDataFromJsonFile(String key) throws Throwable {
		FileReader fileR= new FileReader("./configAppData/JSONcommondata.json");
		
		JSONParser parser= new JSONParser();
		Object obj=parser.parse(fileR);
		
		//step2: Convert java object into JSONObject using downcasting
		JSONObject map= (JSONObject)obj;
		
		//Step3: get the value from json file using key
		String data =(map.get(key)).toString();
		return data;
	}

}
