package team9.transcriptanalyzer.input;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the grading schema from the configuration file.
 * @author qcloutier Created on 3/16/19.
 */
public class GradeSchema extends Schema {
	
	private List<GradeLevel> levels;
	
	public GradeSchema(String file) {
		super();
		
		levels = new ArrayList<GradeLevel>();
	}
	
	public void addLevel(String name, Grade lower, Grade upper) {
		if (!listNames().contains(name)) {
			addName(name);
			levels.add(new GradeLevel(lower, upper));
		}
	}
	
	public Grade getLower(String name) {
		return levels.get(listNames().indexOf(name)).lower;
	}
	
	public Grade getUpper(String name) {
		return levels.get(listNames().indexOf(name)).upper;
	}
	
	public String toString() {
		return "[" + listNames() + ", " + levels + "]";
	}
	
	private class GradeLevel {
		
		public Grade lower;
		public Grade upper;
		
		public GradeLevel(Grade lower, Grade upper) {
			this.lower = lower;
			this.upper = upper;
		}
		
		public String toString() {
			return "[" + lower + ", " + upper + "]";
		}
		
	}
	
}
