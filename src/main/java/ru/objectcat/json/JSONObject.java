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
	
	public void put(String key, String value) {
		MAP.put(key, value);
	}
	
	public void put(String key, byte value) {
		MAP.put(key, value);
	}
	
	public void put(String key, short value) {
		MAP.put(key, value);
	}
	
	public void put(String key, int value) {
		MAP.put(key, value);
	}
	
	public void put(String key, long value) {
		MAP.put(key, value);
	}
	
	public void put(String key, float value) {
		MAP.put(key, value);
	}
	
	public void put(String key, double value) {
		MAP.put(key, value);
	}
	
	public void put(String key, boolean value) {
		MAP.put(key, value);
	}
	
	public void  put(String key, JSONObject value) {
		MAP.put(key, value);
	}
	
	public void put(String key, JSONArray value) {
		MAP.put(key, value);
	}
	
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
		
		public Builder put(String key, String value) {
			return putObj(key, value);
		}
		
		public Builder put(String key, byte value) {
			return putObj(key, value);
		}
		
		public Builder put(String key, short value) {
			return putObj(key, value);
		}
		
		public Builder put(String key, int value) {
			return putObj(key, value);
		}
		
		public Builder put(String key, long value) {
			return putObj(key, value);
		}
		
		public Builder put(String key, float value) {
			return putObj(key, value);
		}
		
		public Builder put(String key, double value) {
			return putObj(key, value);
		}
		
		public Builder put(String key, boolean value) {
			return putObj(key, value);
		}
		
		public Builder put(String key, JSONObject value) {
			return putObj(key, value);
		}
		
		public Builder put(String key, JSONArray value) {
			return putObj(key, value);
		}
		
		public Builder putNull(String key) {
			return putObj(key, null);
		}
		
		public JSONObject build() {
			return new JSONObject(MAP);
		}
		
		private Builder putObj(String key, Object value) {
			MAP.put(key, value);
			return this;
		}
		
	}

}
