package team9.transcriptanalyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the ranking schema from the configuration file.
 * @author qcloutier Created on 3/16/19, last updated on 3/30/19.
 */
public class RankSchema extends Schema {
	
	private List<RankLevel> levels;
	
	/**
	 * Constructs this object by preparing the list for adding levels.
	 */
	public RankSchema(boolean isDefault) {
		super();
		this.levels = new ArrayList<RankLevel>();
		
		if(isDefault) {
			this.addLevel("Freshman", 0, new ArrayList<String>());
			this.addLevel("Sophomore", 40, new ArrayList<String>());
			this.addLevel("Junior", 80, new ArrayList<String>());
			this.addLevel("Senior", 120, new ArrayList<String>());
		}
		
	}

	public void addLevel(String name, int minCreditHours, List<String> requiredCourses) {
		if (!listNames().contains(name)) {
			addName(name);
			levels.add(new RankLevel(minCreditHours, requiredCourses));
		}
	}
	
	/**
	 * Retrieves the minimum number of credit hours required for a rank.
	 * @param name The name of the level.
	 * @return The minimum number of credit hours required for a rank.
	 */
	public int getMinCreditHours(String name) {
		return levels.get(listNames().indexOf(name)).minCreditHours;
	}
	
	/**
	 * Retrieves the names of the required courses for a rank.
	 * @param name The name of the level.
	 * @return The names of the required courses for a rank.
	 */
	public List<String> getRequiredCourses(String name) {
		return levels.get(listNames().indexOf(name)).requiredCourses;
	}
	
	public String toString() {
		return "[" + listNames() + ", " + levels + "]";
	}
	
	/**
	 * Defines an individual level in the rank schema.
	 */
	private class RankLevel {
		
		public int minCreditHours;
		
		public List<String> requiredCourses;
		
		/**
		 * Creates a level in the rank schema.
		 * @param minCreditHours The minimum required credit hours.
		 * @param requiredCourses The required courses.
		 */
		public RankLevel(int minCreditHours, List<String> requiredCourses) {
			this.minCreditHours = minCreditHours;
			this.requiredCourses = requiredCourses;
		}
		
		public String toString() {
			return "[" + minCreditHours + ", " + requiredCourses + "]";
		}
		
	}
	
}
