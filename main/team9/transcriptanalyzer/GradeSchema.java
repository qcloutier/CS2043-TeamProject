package team9.transcriptanalyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the grading schema from the configuration file.
 * @author qcloutier Created on 3/16/19, last updated on 3/30/19.
 */
public class GradeSchema extends Schema {
	
	private List<GradeLevel> levels;

	/**
	 * Constructs this object by preparing the list for adding levels.
	 */
	public GradeSchema(boolean isDefault) {
		super();
		this.levels = new ArrayList<GradeLevel>();
		if(isDefault) {
			this.addLevel("Fail", Grade.match("F"), Grade.match("D"));
			this.addLevel("Marginal", Grade.match("C"), Grade.match("C+"));
			this.addLevel("Meets", Grade.match("B-"), Grade.match("B+"));
			this.addLevel("Exceeds", Grade.match("A-"), Grade.match("A+"));
		}
	}
	
	public void addLevel(String name, Grade lower, Grade upper) {
		if (!listNames().contains(name)) {
			addName(name);
			levels.add(new GradeLevel(lower, upper));
		}
	}
	
	/**
	 * Retrieves the lower bound for a grade level.
	 * @param name The name of the level.
	 * @return The lower bound for a grade level.
	 */
	public Grade getLower(String name) {
		return levels.get(listNames().indexOf(name)).lower;
	}
	
	/**
	 * Retrieves the upper bound for a grade level.
	 * @param name The name of the level.
	 * @return The upper bound for a grade level.
	 */
	public Grade getUpper(String name) {
		return levels.get(listNames().indexOf(name)).upper;
	}
	
	public String toString() {
		return "[" + listNames() + ", " + levels + "]";
	}
	
	/**
	 * Defines an individual level in the grade schema.
	 */
	private class GradeLevel {
		
		public Grade lower;
		
		public Grade upper;
		
		/**
		 * Creates a level in the grade schema.
		 * @param lower The lower grade bound.
		 * @param upper The upper grade bound.
		 */
		public GradeLevel(Grade lower, Grade upper) {
			this.lower = lower;
			this.upper = upper;
		}
		
		public String toString() {
			return "[" + lower + ", " + upper + "]";
		}
		
	}
	
}
