package ru.objectcat.json;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import ru.objectcat.json.parser.JSONFormat;

public class JSONArray {

	private final List<Object> LIST = new ArrayList<>();
	
	public void add(String string) {
		LIST.add(string);
	}
	
	public void add(byte value) {
		LIST.add(value);
	}
	
	public void add(short value) {
		LIST.add(value);
	}
	
	public void add(int value) {
		LIST.add(LIST);
	}
	
	public void add(long value) {
		LIST.add(value);
	}
	
	public void add(float value) {
		LIST.add(value);
	}
	
	public void add(double value) {
		LIST.add(value);
	}
	
	public void add(boolean value) {
		LIST.add(value);
	}
	
	public void add(JSONObject value) {
		LIST.add(value);
	}
	
	public void add(JSONArray value) {
		LIST.add(value);
	}
	
	public void addNull() {
		LIST.add(null);	
	}
	
	public int size() {
		return LIST.size();
	}
	
	public Object get(int index) {
		return LIST.get(index);
	}
	
	@Override
	public String toString() {
		String array = "[";
		Iterator<Object> i = LIST.iterator();
		boolean isNext = i.hasNext();
		while(isNext) {
			Object value = i.next();
			array += JSONFormat.formatValue(value);
			if(i.hasNext()) {
				array += ",";
			}else {
				isNext = false;
			}
		}
		return array + "]";
	}
	
}
