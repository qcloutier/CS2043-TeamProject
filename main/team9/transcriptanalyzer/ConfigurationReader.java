package team9.transcriptanalyzer;

import java.io.IOException;

/**
 * Defines a read method that returns a Configuration object.
 * @author qcloutier Created on 4/2/19.
 */
public interface ConfigurationReader {

	public Configuration read() throws IOException;
	
}
