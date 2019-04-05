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
	
	/**
	 * Creates a results object.
	 * @param config The configuration.
	 * @param rawDist The raw distribution.
	 * @param areaDist The area distribution.
	 * @param studentRanks The student ranks.
	 */
	public Results(Configuration config, RawDistribution rawDist, AreaDistribution areaDist, StudentRanks studentRanks) {
		this.configuration = config;
		this.rawDistribution = rawDist;
		this.areaDistribution = areaDist;
		this.studentRanks = studentRanks;
	}
	
	/**
	 * Retrieves the configuration.
	 * @return The configuration.
	 */
	public Configuration getConfiguration() {
		return this.configuration;
	}

	/**
	 * Retrieves the raw distribution.
	 * @return The raw distribution.
	 */
	public RawDistribution getRawDistribution() {
		return this.rawDistribution;
	}

	/**
	 * Retrieves the area distribution.
	 * @return The area distribution.
	 */
	public AreaDistribution getAreaDistribution() {
		return this.areaDistribution;
	}
	
	/**
	 * Retrieves the student ranks.
	 * @return The student ranks.
	 */
	public StudentRanks getStudentRanks() {
		return this.studentRanks;
	}

	public String toString() {
		return "[" + configuration + ", " + rawDistribution + ", " + areaDistribution + ", " + studentRanks + "]";
	}
	
}