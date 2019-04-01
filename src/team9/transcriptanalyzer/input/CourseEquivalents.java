package team9.transcriptanalyzer.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
	
	/**
	 * Retrieves a double list of all course equivalencies.
	 * @return A double list of all course equivalencies.
	 */
	public List<ArrayList<String>> listAllEquivalencies() {
		
		List<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		
		Iterator<String> keys = equivalents.keySet().iterator();
		while (keys.hasNext()) {
			
			String child = keys.next();
			String parent = equivalents.get(child);
			
			boolean exists = false;
			int c=0;
			while (c<results.size()) {
				if (results.get(c).get(0).equals(parent)) {
					exists = true;
					break;
				}
				c++;
			}
			
			if (exists) {
				results.get(c).add(child);
			}
			else {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(parent);
				temp.add(child);
				results.add(temp);
			}
		}
		
		return results;
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
