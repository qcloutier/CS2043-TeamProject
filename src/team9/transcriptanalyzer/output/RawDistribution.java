package team9.transcriptanalyzer.output;

import java.util.List;
import java.util.ArrayList;
import team9.transcriptanalyzer.input.*;

/**
 * Defines a RawDistribution for a specific course
 * @author mholt1 created on 3/16/2019
 */
public class RawDistribution extends Distribution{
	
	private List<RawEntry> entries;
	
	public RawDistribution(GradeSchema schema, CourseEquivalents course) {
		super(schema);
		entries = new ArrayList<RawEntry>();
	}
	
	private void addEntry(CourseEquivalents course, List<Integer> values) {
		entries.add(new RawEntry(course, values));
	}
	
	public void calculate(Configuration config, Cohort cohort) {
		//TODO
	}
	
	private class RawEntry{
		
		public CourseEquivalents course;
		public List<Integer> values;
		
		public RawEntry(CourseEquivalents course, List<Integer> values) {
			this.course = course;
			this.values = values;
			values = new ArrayList<Integer>();
		}	
	}
	
}