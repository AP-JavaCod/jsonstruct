package ru.objectcat.json;

import org.json.simple.JSONArray;

public class JSONBuilder {
	
	private final org.json.simple.JSONObject JSON = new org.json.simple.JSONObject();
	
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
	
	@Deprecated
	public JSONBuilder put(String key, org.json.simple.JSONObject value) {
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
	
	@Deprecated
	public org.json.simple.JSONObject build() {
		return JSON;
	}
	
	public JSONObject buildJSON() {
		return new JSONObject(JSON);
	}
	
	private JSONBuilder putObj(String key, Object value) {
		JSON.put(key, value);
		return this;
	}

}
