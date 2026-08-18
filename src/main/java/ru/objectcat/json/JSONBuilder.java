package ru.objectcat.json;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class JSONBuilder {
	
	private final JSONObject JSON = new JSONObject();
	
	public JSONBuilder put(String key, String value) {
		return putObj(key, value);
	}
	
	public JSONBuilder put(String key, byte value) {
		return putObj(key, value);
	}
	
	public JSONBuilder put(String key, short value) {
		return putObj(key, value);
	}
	
	public JSONBuilder put(String key, int value) {
		return putObj(key, value);
	}
	
	public JSONBuilder put(String key, long value) {
		return putObj(key, value);
	}
	
	public JSONBuilder put(String key, float value) {
		return putObj(key, value);
	}
	
	public JSONBuilder put(String key, double value) {
		return putObj(key, value);
	}
	
	public JSONBuilder put(String key, boolean value) {
		return putObj(key, value);
	}
	
	public JSONBuilder put(String key, JSONObject value) {
		return putObj(key, value);
	}
	
	public JSONBuilder put(String key, JSONArray value) {
		return putObj(key, value);
	}
	
	public JSONBuilder putNull(String key) {
		return putObj(key, null);
	}
	
	public JSONObject build() {
		return JSON;
	}
	
	private JSONBuilder putObj(String key, Object value) {
		JSON.put(key, value);
		return this;
	}

}
