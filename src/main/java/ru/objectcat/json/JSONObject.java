package ru.objectcat.json;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import ru.objectcat.json.parser.Parser;
import ru.objectcat.json.parser.JSONFormat;

public class JSONObject extends HashMap<String, Object> implements Iterable<JSONObject.KeyValue>{
	
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
	public Iterator<KeyValue> iterator(){
		return new JSONObjectIterator(keySet().iterator());
	}
	
	@Override
	public String toString() {
		String json = "{";
		Iterator<KeyValue> i = iterator();
		boolean isNext = i.hasNext();
		while(isNext) {
			KeyValue value = i.next();
			json += JSONFormat.formatString(value.KEY) + ":" + JSONFormat.formatValue(value.VALUE);
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
			return get(path[index]);
		}
		Object map = get(path[index]);
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
	
	private class JSONObjectIterator implements Iterator<KeyValue>{
		
		private final Iterator<String> KEY;
		
		private JSONObjectIterator(Iterator<String> iterator) {
			KEY = iterator;
		}
		
		@Override
		public boolean hasNext() {
			return KEY.hasNext();
		}
		
		@Override
		public KeyValue next() {
			String key = KEY.next();
			Object value = JSONObject.this.get(key);
			return new KeyValue(key, value);
		}
		
	}
	
	public static record KeyValue(String KEY, Object VALUE) {}

}
