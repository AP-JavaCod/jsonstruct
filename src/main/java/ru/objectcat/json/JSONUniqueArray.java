package ru.objectcat.json;

import java.util.HashSet;

import ru.objectcat.json.parser.JSONFormat;

public class JSONUniqueArray extends HashSet<Object>{
	
	@Override
	public boolean add(Object value) {
		if(!JSONFormat.isValide(value)) {
			throw new ClassCastException("Failed to convert to JSON type " + value.getClass());
		}
		return super.add(value);
	}
	
	@Override
	public String toString() {
		return JSONFormat.formatArray(this);
	}

}
