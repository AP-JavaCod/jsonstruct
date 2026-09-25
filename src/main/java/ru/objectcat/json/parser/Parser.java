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
			if(value.value == null) {
				json.putNull(key.value);
			}else if(value.value instanceof String s) {
				json.put(key.value, s);
			}else if(value.value instanceof JSONObject o){
				json.put(key.value, o);
			}else if(value.value instanceof JSONArray a){
				json.put(key.value, a);
			}else if(value.value instanceof Boolean b){
				json.put(key.value, b);
			}else if(value.value instanceof Long l) {
				json.put(key.value, l);
			}else if(value.value instanceof Double d) {
				json.put(key.value, d);
			}else {
				throw new ClassCastException("Faild to convert to JSON type " + value.value.getClass());
			}
			end += value.index;
			element = text.substring(end);
			next = element.indexOf(',');
			endObject = element.indexOf('}');
		}while(next != -1 && next < endObject);
		return new JSONValue<>(json, end + endObject + 1);
	}
	
	private static JSONValue<JSONArray> parsArray(String text){
		JSONArray json =  new JSONArray();
		String element;
		int end = 0;
		int next = 0;
		int endArray;
		do {
			end += next + 1;
			JSONValue<?> value = parsValue(text.substring(end));
			if(value.value == null) {
				json.addNull();
			}else if(value.value instanceof String s) {
				json.add(s);
			}else if(value.value instanceof JSONObject o) {
				json.add(o);
			}else if(value.value instanceof JSONArray a) {
				json.add(a);
			}else if(value.value instanceof Boolean b) {
				json.add(b);
			}else if(value.value instanceof Long l) {
				json.add(l);
			}else if(value.value instanceof Double d) {
				json.add(d);
			}else {
				throw new ClassCastException("Failed to convert to JSON type " + value.value.getClass());
			}
			end += value.index;
			element = text.substring(end);
			next = element.indexOf(',');
			endArray = element.indexOf(']');
		}while(next != -1 && next < endArray);
		return new JSONValue<>(json, end + endArray + 1);
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
			if(el == ' ' || el == '\t' || el == '\n' || el == ',' || el == '}' || el == ']') {
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
			}else if(el != ' ' && el != '\t' && el != '\n') {
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
