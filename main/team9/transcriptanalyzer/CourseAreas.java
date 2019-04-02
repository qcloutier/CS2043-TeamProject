package team9.transcriptanalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Defines the course areas from the configuration file.
 * @author qcloutier Created on 3/16/19, last updated on 3/30/19.
 */
public class CourseAreas {

	private Map<String, List<String>> areas;
	
	/**
	 * Constructs this object by preparing the map for adding areas.
	 */
	public CourseAreas() {
		this.areas = new HashMap<String, List<String>>();
	}
	
	/**
	 * Maps a course name to an area name.
	 * @param course The course name.
	 * @param area The area name.
	 */
	public void addArea(String course, String area) {
		if (!areas.containsKey(course)) {
			areas.put(course, new ArrayList<String>());
		}
		areas.get(course).add(area);
	}
	
	/**
	 * Retrieves the list of area names associated with a course name.
	 * @param course The course name.
	 * @return A list of area names.
	 */
	public List<String> getAreas(String course) {
		return this.areas.get(course);
	}
	
	/**
	 * Generates a double list of all course areas.
	 * @return A double list of all course areas.
	 */
	public List<List<String>> listAllAreas() {
		
		List<List<String>> results = new ArrayList<List<String>>();
		
		Iterator<String> keys = areas.keySet().iterator();
		while (keys.hasNext()) {
			
			String child = keys.next();
			List<String> parents = areas.get(child);
			for (String parent : parents) {
	
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
					List<String> temp = new ArrayList<String>();
					temp.add(parent);
					temp.add(child);
					results.add(temp);
				}
			}
		}
		
		return results;
	}

	public String toString() {
		
		String result = "";
		
		Iterator<String> keys = areas.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			result += "[" + key + ", " + areas.get(key) + "], ";
		}
		
		if (result.contains(", ")) {
			result = result.substring(0, result.length()-2);
		}
		
		return "[" + result + "]";
	}
	
}
