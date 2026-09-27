package ru.objectcat.json;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import ru.objectcat.json.parser.Parser;
import ru.objectcat.json.parser.JSONFormat;

public class JSONArray {

	private final List<Object> LIST = new ArrayList<>();
	
	public static JSONArray parse(String text) {
		return Parser.parserArray(text);
	}
	
	public void add(Object value) {
		if(!JSONFormat.isValide(value)) {
			throw new ClassCastException("Failed to convert to JSON type " + value.getClass());	
		}
		LIST.add(value);
	}
	
	@Deprecated
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
