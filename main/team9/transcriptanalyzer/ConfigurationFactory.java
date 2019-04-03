package team9.transcriptanalyzer;

import java.io.File;
import java.io.IOException;

/**
 * Determines what type of ConfigurationReader should be created.
 * @author qcloutier Created on 4/3/19.
 */
public class ConfigurationFactory {

	public static ConfigurationReader determine(File file) throws IOException {
		
		ConfigurationReader result = null;
		
		String n = file.getName();
		int l = n.length();
		
		if ((l >= 4 && n.substring(l-4, l).equals(".xls")) || 
			(l >= 5 && n.substring(l-5, l).equals(".xlsx"))) {
			
			result = new ConfigurationExcelReader(file);
		}
		else {
			throw new IOException("Unsupported file extension.");
		}
		
		return result;
	}
	
}
