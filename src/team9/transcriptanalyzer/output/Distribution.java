package team9.transcriptanalyzer.output;

import team9.transcriptanalyzer.input.Cohort;
import team9.transcriptanalyzer.input.Configuration;
import team9.transcriptanalyzer.input.GradeSchema;

/**
 * Defines an abstract Distribution
 * @author mholt1 created on 3/16/2019
 */
public abstract class Distribution{
	
	private GradeSchema schema;
	
	public Distribution(GradeSchema schema) {
		this.schema = schema;
	}
	
	public GradeSchema getSchema() {
		return this.schema;
	}
	
	abstract void calculate(Configuration config, Cohort cohort);
	
	abstract String[][] listDistribution();
	
}