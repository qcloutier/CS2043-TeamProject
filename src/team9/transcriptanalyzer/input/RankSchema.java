package team9.transcriptanalyzer.input;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the ranking schema from the configuration file.
 * @author qcloutier Created on 3/16/19.
 */
public class RankSchema extends Schema {

	private List<RankLevel> levels;
	
	public RankSchema(String file) {
		super();
		
		levels = new ArrayList<RankLevel>();
	}
	
	public void addLevel(String name, int minCreditHours, List<ConfigCourse> requiredCourses) {
		if (!listNames().contains(name)) {
			addName(name);
			levels.add(new RankLevel(minCreditHours, requiredCourses));
		}
	}
	
	public int getMinCreditHours(String name) {
		return levels.get(listNames().indexOf(name)).minCreditHours;
	}
	
	public List<ConfigCourse> getRequiredCourses(String name) {
		return levels.get(listNames().indexOf(name)).requiredCourses;
	}
	
	public String toString() {
		return "[" + listNames() + ", " + levels + "]";
	}
	
	private class RankLevel {
		
		public int minCreditHours;
		public List<ConfigCourse> requiredCourses;
		
		public RankLevel(int minCreditHours, List<ConfigCourse> requiredCourses) {
			this.minCreditHours = minCreditHours;
			this.requiredCourses = requiredCourses;
		}
		
		public String toString() {
			return "[" + minCreditHours + ", " + requiredCourses + "]";
		}
		
	}
	
}
