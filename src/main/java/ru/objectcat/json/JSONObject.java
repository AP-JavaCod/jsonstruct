package ru.objectcat.json;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;

public class JSONObject {
	
	private final Map<String, Object> MAP = new HashMap<>();
	
	public JSONObject(Map<String, Object> map) {
		MAP.putAll(map);
	}
	
	public Object valueJSON(String... path) {
		return valueJSON(path, 0);
	}
	
	@Override
	public String toString() {
		String json = "{";
		Iterator<String> i = MAP.keySet().iterator();
		boolean isNext = i.hasNext();
		while(isNext) {
			String key = strJSON(i.next());
			json += "\"" + key + "\":";
			Object value = MAP.get(key);
			if(
					value == null ||
					value instanceof Byte ||
					value instanceof Short ||
					value instanceof Integer ||
					value instanceof Long ||
					value instanceof Float ||
					value instanceof Boolean
				) {
				json += value;
			}else if (value instanceof JSONObject v) {
				json += v.toString();
			}else if(value instanceof JSONArray a) {
				json += a.toJSONString();
			}else if(value instanceof String str) {
				json += "\"" + strJSON(str) + "\"";
			}else {
				throw new ClassCastException("Failed to convert to JSON type " + value.getClass());
			}
			if(i.hasNext()) {
				json += ",";
			}else {
				isNext = false;
			}
		}
		return json + "}";
	}
	
	private String strJSON(String value) {
		String str = "";
		for(char el : value.toCharArray()) {
			switch(el) {
			case '\\':
				str += "\\\\";
				break;
			case '\"':
				str += "\\\"";
				break;
			default:
				str += el;
			}
		}
		return str;
	}
	
	private Object valueJSON(String[] path, int index) {
		if(index == path.length - 1) {
			return MAP.get(path[index]);
		}
		Object map = MAP.get(path[index]);
		if(map instanceof JSONObject next) {
			return next.valueJSON(path, index + 1);	
		}else {
			return null;	
		}
	}

}
