package team9.transcriptanalyzer;

import java.io.IOException;

/**
 * Defines a read method that returns a Cohort object.
 * @author rbannister Created on 4/3/19.
 * @author qcloutier Updated on 4/3/19.
 */
public interface CohortReader {
	
	public Cohort read() throws IOException;

}
