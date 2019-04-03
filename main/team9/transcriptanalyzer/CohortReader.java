package team9.transcriptanalyzer;

import java.io.File;
import java.io.IOException;

public interface CohortReader {
	
	public Cohort read(File file) throws IOException;

}
