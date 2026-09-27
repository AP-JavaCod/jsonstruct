package ru.objectcat.json;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import ru.objectcat.json.parser.Parser;
import ru.objectcat.json.parser.JSONFormat;

public class JSONObject {
	
	private final Map<String, Object> MAP = new HashMap<>();
	
	public JSONObject() {}
	
	public JSONObject(JSONObject json) {
		MAP.putAll(json.MAP);
	}
	
	private JSONObject(Map<String, Object> map) {
		MAP.putAll(map);
	}
	
	public static JSONObject parse(String text) {
		return Parser.parserObject(text);
	}
	
	public Object valueJSON(String... path) {
		return valueJSON(path, 0);
	}
	
	public void put(String key, Object value) {
		if(!JSONFormat.isValide(value)){
			throw new ClassCastException("Failed to convert to JSON type " + value.getClass());
		}
		MAP.put(key, value);
	}
	
	@Deprecated
	public void putNull(String key) {
		MAP.put(key, null);	
	}
	
	public Set<String> getKey(){
		return MAP.keySet();
	}
	
	@Override
	public String toString() {
		String json = "{";
		Iterator<String> i = MAP.keySet().iterator();
		boolean isNext = i.hasNext();
		while(isNext) {
			String key = i.next();
			Object value = MAP.get(key);
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
	
	public static class Builder{
		
		private final Map<String, Object> MAP = new HashMap<>();
		
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
			return new JSONObject(MAP);
		}
		
	}

}
