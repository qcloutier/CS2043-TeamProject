package team9.transcriptanalyzer;

import java.io.File;
import java.io.IOException;

public interface ConfigurationReader {

	public Configuration read(File file) throws IOException;
	
}
