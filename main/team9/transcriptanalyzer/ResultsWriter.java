package team9.transcriptanalyzer;

import java.io.File;
import java.io.IOException;

public interface ResultsWriter {

	public void write(File file, Results data) throws IOException;
	
}
