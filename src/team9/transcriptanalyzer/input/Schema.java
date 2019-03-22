package team9.transcriptanalyzer.input;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a abstract level schema.
 * @author qcloutier Created on 3/16/19.
 */
public abstract class Schema {

	private List<String> names;
	
	public Schema() {
		names = new ArrayList<String>();
	}
	
	public void addName(String name) {
		names.add(name);
	}
	
	public List<String> listNames() {
		return names;
	}
	
}
