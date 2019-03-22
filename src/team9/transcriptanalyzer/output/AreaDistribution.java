package team9.transcriptanalyzer.output;

import java.util.List;
import java.util.ArrayList;
import team9.transcriptanalyzer.input.*;

/**
 * Defines an AreaDistribution for a specific area.
 * @author mholt1 created on 3/16/2019
 */
public class AreaDistribution extends Distribution{
	
	private List<AreaEntry> entries;
	
	public AreaDistribution(GradeSchema schema, Area area) {
		super(schema);
		entries = new ArrayList<AreaEntry>();
	}
	
	private void addEntry(Area area, List<Integer> values) {
		entries.add(new AreaEntry(area, values));
	}
	
	public void calculate(Configuration Config, Cohort cohort) {
		//TODO
	}
	
	private class AreaEntry{
		
		public Area area;
		public List<Integer> values;
		
		public AreaEntry(Area area, List<Integer> values) {
			this.area = area;
			this.values = values;
		}
	}
	
}