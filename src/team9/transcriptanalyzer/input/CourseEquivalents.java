package team9.transcriptanalyzer.input;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Defines the course equivalencies from the configuration file.
 * @author qcloutier Created on 3/16/19, last updated on 3/30/19.
 */
public class CourseEquivalents {
	
	private Map<String, String> equivalents;
	
	/**
	 * Constructs this object by preparing the map for adding equivalencies.
	 */
	public CourseEquivalents() {
		this.equivalents = new HashMap<String, String>();
	}
	
	/**
	 * Maps a course name to its top level equivalent.
	 * @param course The course name.
	 * @param equivalent The equivalent course.
	 */
	public void addEquivalency(String course, String equivalent) {
		if (!equivalents.containsKey(course)) {
			this.equivalents.put(course, equivalent);
		}
	}
	
	/**
	 * Retrieves the top level equivalent of a given course name.
	 * @param course The course name.
	 * @return The equivalent course.
	 */
	public String getEquivalency(String course) {
		return this.equivalents.get(course);
	}
	
	public String toString() {
		
		String result = "";
		
		Iterator<String> keys = equivalents.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			result += "[" + key + ", " + equivalents.get(key) + "], ";
		}
		
		if (result.length() > 2) {
			result = result.substring(0, result.length()-2);
		}
		
		return "[" + result + "]";
	}
	
}
