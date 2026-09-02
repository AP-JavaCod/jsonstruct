package ru.objectcat.json;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class JSONStruct {

	@Deprecated
	public static JSONObject objectJSON(KeyValue... data) {
		JSONObject json = new JSONObject();
		for(KeyValue el : data) {
			json.put(el.key(), el.value());
		}
		return json;
	}
	
	public static JSONArray arrayJSON(Object... values) {
		JSONArray array = new JSONArray();
		for(Object el : values) {
			array.add(el);
		}
		return array;
	}
	
	@Deprecated
	public static Object jsonValue(JSONObject json, String... pateh) {
		Object value = json;
		for(String el : pateh) {
			if(value instanceof JSONObject e) {
				value = e.get(el);
			}else {
				return null;
			}
		}
		return value;
	}
	
	@Deprecated
	public record KeyValue(String key, Object value) {}
	
}
