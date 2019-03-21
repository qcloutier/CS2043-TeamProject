package team9.transcriptanalyzer.output;

import java.util.List;
import java.util.ArrayList;
import team9.transcriptanalyzer.input.*;

/**
 * Defines a RawDistribution for a specific course
 * @author mholt1 created on 3/16/2019
 */

public class RawDistribution extends Distribution{
	
	private ConfigCourse course;
	private List<RawEntry> entries;
	
	public RawDistribution(GradeSchema schema, ConfigCourse course) {
		super(schema);
		this.course = course;
		entries = new ArrayList<RawEntry>();
	}
	
	private void addEntry(ConfigCourse course, List<Integer> values) {
		entries.add(new RawEntry(course, values));
	}
	
	public void calculate(Configuration config, Cohort cohort) {
		//TODO
	}
	
	
	private class RawEntry{
		
		public ConfigCourse course;
		public List<Integer> values;
		
		public RawEntry(ConfigCourse course, List<Integer> values) {
			this.course = course;
			this.values = values;
			values = new ArrayList<Integer>();
		}
		
		
	}
}