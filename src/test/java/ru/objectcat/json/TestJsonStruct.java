package ru.objectcat.json;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import org.json.simple.JSONArray;

public class TestJsonStruct {
	
	private org.json.simple.JSONObject json = new org.json.simple.JSONObject();
	private JSONObject myJSON = new JSONBuilder()
			.put("data", "info")
			.put("user", new JSONBuilder()
					.put("name", "Tom")
					.put("age", 12)
					.buildJSON())
			.put("values", JSONStruct.arrayJSON("val1", "val2", "val3"))
			.buildJSON();
	
	@BeforeEach
	public void init() {
		json.put("data", "info");
		
		org.json.simple.JSONObject user = new org.json.simple.JSONObject();
		user.put("name", "Tom");
		user.put("age", 12);
		json.put("user", user);
		
		JSONArray array = new JSONArray();
		array.add("val1");
		array.add("val2");
		array.add("val3");
		json.put("values", array);
	}
	
	@Test
	public void builderJSON() {
		Assertions.assertEquals(json.toJSONString(), myJSON.toString());
	}
	
	@Test
	public void getName() {
		Object object = myJSON.valueJSON("user", "name");
		Assertions.assertEquals("Tom", object);
	}

}
