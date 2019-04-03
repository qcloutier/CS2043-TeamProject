package team9.transcriptanalyzer;

/**
 * A class to represent all the results.
 * @author mholt1 Created on 3/18/2019.
 * @author qcloutier Updated on 4/2/19.
 */
public class Results {
	
	private Configuration configuration;
	
	private RawDistribution rawDistribution;
	
	private AreaDistribution areaDistribution;
	
	private StudentRanks studentRanks;
	
	public Results(Configuration config, RawDistribution rawDist, AreaDistribution areaDist, StudentRanks studentRanks) {
		this.configuration = config;
		this.rawDistribution = rawDist;
		this.areaDistribution = areaDist;
		this.studentRanks = studentRanks;
	}
	
	public Configuration getConfiguration() {
		return this.configuration;
	}

	public RawDistribution getRawDistribution() {
		return this.rawDistribution;
	}

	public AreaDistribution getAreaDistribution() {
		return this.areaDistribution;
	}
	
	public StudentRanks getStudentRanks() {
		return this.studentRanks;
	}

	public String toString() {
		return "[" + configuration + ", " + rawDistribution + ", " + areaDistribution + ", " + studentRanks + "]";
	}
	
}