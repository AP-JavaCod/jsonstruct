package ru.objectcat.json.parser;

import ru.objectcat.json.JSONObject;
import ru.objectcat.json.JSONArray;

public class Parser {
	
	public static JSONObject parserObject(String value){
		return parsObject(value).value;
	}
	
	public static JSONArray parserArray(String value){ 
		return parsArray(value).value;
	}
	
	private static JSONValue<JSONObject> parsObject(String text) {
		JSONObject json = new JSONObject();
		int startKey = text.indexOf('"');
		int stopObject = text.indexOf('}');
		if(startKey == -1 || startKey > stopObject) {
			return new JSONValue<>(json, stopObject + 1);
		}
		String element;
		int end = 0;
		int next = 0;
		int endObject;
		do {
			end += next + 1;
			JSONValue<String> key = parsString(text.substring(end));
			end += key.index;
			end += text.substring(end).indexOf(':') + 1;
			JSONValue<?> value = parsValue(text.substring(end));
			json.put(key.value, value.value);
			end += value.index;
			element = text.substring(end);
			next = element.indexOf(',');
			endObject = element.indexOf('}');
		}while(next != -1 && next < endObject);
		return new JSONValue<>(json, end + endObject + 1);
	}
	
	private static JSONValue<JSONArray> parsArray(String text){
		JSONArray json =  new JSONArray();
		int end = 1;
		JSONValue<?> value = parsValue(text.substring(end));
		while(value.index != -1) {
			json.add(value.value);
			end += value.index;
			String nextElement = text.substring(end);
			int next = nextElement.indexOf(',');
			if(next == -1 || next > nextElement.indexOf(']')) {
				break;
			}
			end += next + 1;
			value = parsValue(text.substring(end));
		}
		return new JSONValue<>(json, end + text.substring(end).indexOf(']') + 1);
	}
	
	private static JSONValue<String> parsString(String text){
		String value = "";
		int i = text.indexOf('"') + 1;
		for(;i < text.length(); i++) {
			char el = text.charAt(i);
			if(el == '"') {
				break;
			}else if(el == '\\') {
				i++;
				el = text.charAt(i);
			}
			value += el;
		}
		return new JSONValue<>(value, i + 1);
	}
	
	private static JSONValue<Object> parsOther(String text){
		String value = "";
		int index = 0;
		for(char el : text.toCharArray()) {
			if(el == ' ' || el == '\t' || el == '\n' || el == ',' || el == '}' || el == ']' || el == '\r') {
				break;
			}
			value += el;
			index ++;
		}
		if(value.equals("true")) {
			return new JSONValue<>(true, index);
		}else if(value.equals("false")) {
			return new JSONValue<>(false, index);
		}else if(value.equals("null")) {
			return new JSONValue<>(null, index);
		}
		int point = value.indexOf('.');
		if(point == -1) {
			return new JSONValue<>(Long.parseLong(value), index);
		}
		return new JSONValue<>(Double.parseDouble(value), index);
	}
	
	private static JSONValue<? extends Object> parsValue(String text) {
		int end = 0;
		for(char el : text.toCharArray()) {
			if(el == '"') {
				return parsString(text.substring(end)).step(end);
			}else if(el == '{') {
				return parsObject(text.substring(end)).step(end);
			}else if(el == '[') {
				return parsArray(text.substring(end)).step(end);
			}else if(el == ']' || el == '}') {
				return new JSONValue<>(null, -1);
			}else if(el != ' ' && el != '\t' && el != '\n' && el != '\r') {
				return parsOther(text.substring(end)).step(end);
			}
			end++;
		}
		return null;
	}
	
	private record JSONValue<T>(T value, int index) {
		
		public JSONValue<T> step(int index){
			return new JSONValue<>(value, this.index + index);
		}
		
	}

}
