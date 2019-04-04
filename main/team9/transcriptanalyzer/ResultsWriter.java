package team9.transcriptanalyzer;

import java.io.IOException;

/**
 * Defines a write method for writing the data in a Results object to a file.
 * @author qcloutier Created on 4/2/19.
 */
public interface ResultsWriter {

	public void write(Results data) throws IOException;
	
}
