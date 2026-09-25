package ru.objectcat.json.parser;

import org.json.simple.JSONArray;

import ru.objectcat.json.JSONObject;

public class JSONFormat {
	
	public static String formatValue(Object value) {
		if(value instanceof String s) {
			return formatString(s);
		}else if(value instanceof JSONObject json) {
			return formatObject(json);
		}else if(value instanceof JSONArray array) {
			return formatArray(array);
		}
		return formatOther(value);
	}
	
	public static String formatString(String value) {
		String str = "";
		for(char el : value.toCharArray()) {
			switch(el) {
			case '\\':
			case '"':
				str +=  '\\';
			default:
				str += el;	
			}
		}
		return '"' + str + '"';
	}
	
	public static String formatObject(JSONObject value) {
		return value.toString();
	}
	
	public static String formatArray(JSONArray value) {
		return value.toJSONString();
	}
	
	public static String formatOther(Object value) {
		if(
			value == null ||
			value instanceof Byte ||
			value instanceof Short ||
			value instanceof Integer ||
			value instanceof Long || 
			value instanceof Float ||
			value instanceof Double ||
			value instanceof Boolean
			) {
			return String.valueOf(value);
		}
		throw new ClassCastException("Failed to convert to JSON type " + value.toString());
	}

}
