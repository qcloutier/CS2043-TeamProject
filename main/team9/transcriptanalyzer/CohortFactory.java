package team9.transcriptanalyzer;

import java.io.File;
import java.io.IOException;

/**
 * Determines what type of TranscriptReader should be created.
 * @author qcloutier Created on 4/3/19.
 */
public class CohortFactory {
	
	public static CohortReader determine(File file) throws IOException {
		
		CohortReader result = null;
		
		String n = file.listFiles()[0].getName();
		int l = n.length();
		
		if (l >= 4 && n.substring(l-4, l).equals(".txt")) {
			result = new CohortTextReader(file);
		}
		else {
			throw new IOException("Unsupported file extension.");
		}
		
		return result;
	}
	
}
