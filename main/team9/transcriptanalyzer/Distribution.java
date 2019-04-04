package team9.transcriptanalyzer;

/**
 * Defines an abstract distribution.
 * @author mholt1 Created on 3/16/2019.
 */
public abstract class Distribution{
	
	private GradeSchema schema;
	
	public Distribution(GradeSchema schema) {
		this.schema = schema;
	}
	
	public GradeSchema getSchema() {
		return this.schema;
	}
	
	public abstract void calculate(Configuration config, Cohort cohort);
	
	public abstract String[][] listDistribution();
	
}