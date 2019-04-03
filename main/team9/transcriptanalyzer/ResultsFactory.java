package team9.transcriptanalyzer;

import java.io.File;
import java.io.IOException;

/**
 * Determines what type of ResultsWriter should be created.
 * @author qcloutier Created on 4/3/19.
 */
public class ResultsFactory {

	public static ResultsWriter determine(File file) throws IOException {
		
		ResultsWriter result = null;
		
		String n = file.getName();
		int l = n.length();
		
		if (l >= 5 && n.substring(l-5, l).equals(".xlsx")) {
			result = new ResultsExcelWriter(file);
		}
		else {
			throw new IOException("Unsupported file extension.");
		}
		
		return result;
	}
	
}
