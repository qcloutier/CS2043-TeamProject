package team9.transcriptanalyzer.output;

import java.util.List;
import java.util.ArrayList;
import team9.transcriptanalyzer.input.*;

/**
 * Defines an AreaDistribution for a specific area.
 * @author mholt1 Created on 3/16/19.
 * @author qcloutier Updated on 3/30/19.
 */
public class AreaDistribution extends Distribution{
	
	private List<AreaEntry> entries;
	
	public AreaDistribution(GradeSchema schema, CourseAreas area) {
		super(schema);
		entries = new ArrayList<AreaEntry>();
	}
	
	private void addEntry(CourseAreas area, List<Integer> values) {
		entries.add(new AreaEntry(area, values));
	}
	
	public void calculate(Configuration Config, Cohort cohort) {
		//TODO
	}
	
	private class AreaEntry{
		
		public CourseAreas area;
		public List<Integer> values;
		
		public AreaEntry(CourseAreas area, List<Integer> values) {
			this.area = area;
			this.values = values;
		}
	}
	
}