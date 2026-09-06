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
	
	public JSONBuilder put(String key, org.json.simple.JSONObject value) {
		return putObj(key, value);
	}
	
	public JSONBuilder putJSON(String key, org.json.simple.JSONObject value) {
		JSONBuilder builder = new JSONBuilder();
		for(Object i : value.keySet()) {
			if(i instanceof String k) {
				Object v = value.get(k);
				if(
						v == null ||
						v instanceof String ||
						v instanceof Byte ||
						v instanceof Short ||
						v instanceof Integer ||
						v instanceof Long ||
						v instanceof Float ||
						v instanceof Double ||
						v instanceof Boolean ||
						v instanceof JSONObject ||
						v instanceof JSONArray
				) {
					builder.putObj(k, v);
				}else if(v instanceof org.json.simple.JSONObject e) {
					builder.put(k, e);
				}else {
					throw new ClassCastException("Faild to convert to JSON type " + v.getClass());
				}
			}else {
				throw new ClassCastException("Faild to convert to JSON type " + i.getClass());
			}
		}
		return putObj(key, builder.buildJSON());
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
