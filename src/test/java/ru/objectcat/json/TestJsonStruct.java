package ru.objectcat.json;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class TestJsonStruct {
	
	private JSONObject json = new JSONObject();
	
	@BeforeEach
	public void init() {
		json.put("data", "info");
		
		JSONObject user = new JSONObject();
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
		JSONObject data = new JSONBuilder()
				.put("data", "info")
				.put("user", new JSONBuilder()
						.put("name", "Tom")
						.put("age", 12)
						.build())
				.put("values", JSONStruct.arrayJSON("val1", "val2", "val3"))
				.build();
		Assertions.assertEquals(json, data);
	}
	
	@Test
	public void getName() {
		Object object = JSONStruct.jsonValue(json, "user", "name");
		Assertions.assertEquals("Tom", object);
	}

}
