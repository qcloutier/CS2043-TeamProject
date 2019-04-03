package team9.transcriptanalyzer;

import java.io.IOException;

public interface ResultsWriter {

	public void write(Results data) throws IOException;
	
}
