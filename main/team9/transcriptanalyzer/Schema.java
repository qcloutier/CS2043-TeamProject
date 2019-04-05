package team9.transcriptanalyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a abstract level schema.
 * @author qcloutier Created on 3/16/19.
 */
public abstract class Schema {

	private List<String> names;
	
	/**
	 * Creates a schema with an empty list of names. 
	 */
	public Schema() {
		names = new ArrayList<String>();
	}
	
	/**
	 * Adds the name of a level to the schema.
	 * @param name The name of the new level.
	 */
	public void addName(String name) {
		names.add(name);
	}
	
	/**
	 * Retrieves the names of all levels in a schema.
	 * @return The names of all levels in a schema.
	 */
	public List<String> listNames() {
		return names;
	}
	
}
