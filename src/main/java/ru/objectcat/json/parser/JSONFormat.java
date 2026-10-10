package ru.objectcat.json.parser;

import java.util.Iterator;

import ru.objectcat.json.JSONObject;
import ru.objectcat.json.JSONArray;
import ru.objectcat.json.JSONUniqueArray;

public class JSONFormat {
	
	public static String formatValue(Object value) {
		if(value instanceof String s) {
			return formatString(s);
		}else if(value instanceof JSONObject json) {
			return formatObject(json);
		}else if(value instanceof JSONArray array) {
			return formatArray(array);
		}else if(value instanceof JSONUniqueArray un) {
			return formatArray(un);
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
		return formatArray(value.iterator());
	}
	
	public static String formatArray(JSONUniqueArray value) {
		return formatArray(value.iterator());
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
		throw new ClassCastException("Failed to convert to JSON type " + value.getClass());
	}
	
	public static boolean isValide(Object value) {
		return value == null ||
				value instanceof String ||
				value instanceof JSONObject ||
				value instanceof JSONArray ||
				value instanceof JSONUniqueArray ||
				value instanceof Byte ||
				value instanceof Short ||
				value instanceof Integer ||
				value instanceof Long ||
				value instanceof Float ||
				value instanceof Double ||
				value instanceof Boolean;
	}
	
	private static String formatArray(Iterator<Object> iterator) {
		String array = "[";
		boolean isNext = iterator.hasNext();
		while(isNext) {
			Object value = iterator.next();
			array += formatValue(value);
			if(iterator.hasNext()) {
				array += ",";
			}else {
				isNext = false;
			}
		}
		return array + "]";
	}

}
