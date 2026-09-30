package ru.objectcat.json;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import ru.objectcat.json.parser.Parser;
import ru.objectcat.json.parser.JSONFormat;

public class JSONObject extends HashMap<String, Object>{
	
	public JSONObject() {}
	
	public JSONObject(Map<String, Object> map) {
		putAll(map);
	}
	
	public static JSONObject parse(String text) {
		return Parser.parserObject(text);
	}
	
	public Object valueJSON(String... path) {
		return valueJSON(path, 0);
	}
	
	@Override
	public Object put(String key, Object value) {
		if(!JSONFormat.isValide(value)){
			throw new ClassCastException("Failed to convert to JSON type " + value.getClass());
		}
		return super.put(key, value);
	}
	
	@Override
	public void putAll(Map<? extends String, ? extends Object> map) {
		for(String key : map.keySet()) {
			put(key, map.get(key));
		}
	}
	
	@Deprecated
	public void putNull(String key) {
		super.put(key, null);	
	}
	
	@Override
	public String toString() {
		String json = "{";
		Iterator<String> i = keySet().iterator();
		boolean isNext = i.hasNext();
		while(isNext) {
			String key = i.next();
			Object value = get(key);
			json += JSONFormat.formatString(key) + ":" + JSONFormat.formatValue(value);
			if(i.hasNext()) {
				json += ",";
			}else {
				isNext = false;
			}
		}
		return json + "}";
	}
	
	private Object valueJSON(String[] path, int index) {
		Object map = get(path[index]);
		if(index == path.length - 1) {
			return map;
		}else if(map instanceof JSONObject next) {
			return next.valueJSON(path, index + 1);	
		}
		return null;
	}
	
	public static class Builder{
		
		private final JSONObject MAP = new JSONObject();
		
		public Builder put(String key, Object value) {
			if(!JSONFormat.isValide(value)) {
				throw new ClassCastException("Failed to convert to JSON type " + value.getClass());
			}
			MAP.put(key, value);
			return this;
		}
		
		@Deprecated
		public Builder putNull(String key) {
			return put(key, null);
		}
		
		public JSONObject build() {
			return MAP;
		}
		
	}

}
