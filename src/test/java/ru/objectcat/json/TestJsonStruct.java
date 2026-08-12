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
	public void createJSON() {
		JSONObject data = JSONStruct.objectJSON(
				new JSONStruct.KeyValue("data", "info"),
				new JSONStruct.KeyValue("user", JSONStruct.objectJSON(
						new JSONStruct.KeyValue("name", "Tom"),
						new JSONStruct.KeyValue("age", 12)
						)),
				new JSONStruct.KeyValue("values", JSONStruct.arrayJSON("val1", "val2", "val3"))
				);
		Assertions.assertEquals(json.toJSONString(), data.toJSONString());
	}
	
	@Test
	public void getName() {
		Object object = JSONStruct.jsonValue(json, "user", "name");
		Assertions.assertEquals("Tom", object);
	}

}
