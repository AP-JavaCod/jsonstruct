package ru.objectcat.json;

import java.util.ArrayList;
import java.util.Collection;

import ru.objectcat.json.parser.Parser;
import ru.objectcat.json.parser.JSONFormat;

public class JSONArray extends ArrayList<Object>{
	
	public static JSONArray parse(String text) {
		return Parser.parserArray(text);
	}
	
	public static JSONArray create(Object... values) {
		JSONArray array = new JSONArray();
		for(Object val : values) {
			array.add(val);
		}
		return array;
	}
	
	@Override
	public boolean add(Object value) {
		if(!JSONFormat.isValide(value)) {
			throw new ClassCastException("Failed to convert to JSON type " + value.getClass());	
		}
		return super.add(value);
	}
	
	
	@Override
	public void add(int index, Object value) {
		if(!JSONFormat.isValide(value)) {
			throw new ClassCastException("Failed to convert to JSON type " + value.getClass());
		}
		super.add(index, value);
	}
	
	@Override
	public boolean addAll(Collection<? extends Object> collection) {
		return super.addAll(collection.stream().filter(JSONFormat::isValide).toList());
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends Object> collection) {
		return super.addAll(index, collection.stream().filter(JSONFormat::isValide).toList());
	}
	
	@Override
	public Object set(int index, Object value) {
		if(!JSONFormat.isValide(value)) {
			throw new ClassCastException("Failed to convert to JSON type " + value.getClass());
		}
		return super.set(index, value);
	}
	
	@Deprecated
	public void addNull() {
		super.add(null);	
	}
	
	@Override
	public String toString() {
		return JSONFormat.formatArray(this);
	}
	
}
