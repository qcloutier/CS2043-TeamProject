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
	
	/*public void calculate(Configuration config, Cohort cohort) {
		//TODO
	}*/
	
	public void calculate(Configuration config, Cohort cohort) {
		ArrayList<RawDist> list = new ArrayList<RawDist>();
		int other = 0;
		int fails = 0;
		int marginal = 0;
		int meets = 0;
		int exceeds = 0;
		
		
		for(int i = 0; i < config.courses.length(); i++) {
			
			for(int j = 0; j < cohort.transcripts.length(); j++)
			
			if(config.courses[i] = cohort.transcripts[j]) {
				other++;
				fails++;
				marginal++;
				meets++;
				exceeds++;
				
			}
			list.add(new RawDist(config[i],other,fails,marginal,meets,exceeds));
			
		}
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