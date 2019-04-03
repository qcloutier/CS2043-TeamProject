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
	
	public Results(Configuration config, RawDistribution rawDist, AreaDistribution areaDist) {
		this.configuration = config;
		this.rawDistribution = rawDist;
		this.areaDistribution = areaDist;
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}

	public RawDistribution getRawDistribution() {
		return rawDistribution;
	}

	public AreaDistribution getAreaDistribution() {
		return areaDistribution;
	}

	public String toString() {
		return null;
	}
	
}