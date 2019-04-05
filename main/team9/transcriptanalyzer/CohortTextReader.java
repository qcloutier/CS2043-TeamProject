package team9.transcriptanalyzer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Implementation of CohortReader for text files.
 * @author rbannister Created on 4/03/19.
 * @author qcloutier Created on 4/3/19.
 */
public class CohortTextReader implements CohortReader {
	
	private File files;
	
	public CohortTextReader(File files) {
		this.files = files;
	}
	
	public Cohort read() throws IOException {
	
		Cohort results = new Cohort();
		boolean skipFlag = false;
		
		for (File file : files.listFiles()) {
			
			// Skip transcript if any error occurs while parsing
			try { 
				Transcript t = parseTranscript(file);
				results.addTranscript(t);
			}
			catch (Exception e) { 
				skipFlag = true;
			};
		}
		
		if (skipFlag) {
			Messenger.parseError();
		}

		return results;
	}
	
	private Transcript parseTranscript(File file) throws IOException {
		
		try (Scanner sc = new Scanner(file)) {
			
			Transcript results = new Transcript();
			while (sc.hasNextLine()) {
				
				TranscriptCourse value = parseCourse(sc.nextLine());
				if (value != null) {
					results.addCourse(value);
				}
			}
			
			return results;
		}
	}
	
	private TranscriptCourse parseCourse(String line) {
		
		try (Scanner scan = new Scanner(line);) {
			
			if (scan.hasNext()) { // Skip blank lines
				scan.useDelimiter("\\s\\s\\s*"); // Parse on two or more spaces
				
				String id = scan.next();
				String section = scan.next();
				scan.next(); // Ignore course name
				Grade grade = Grade.match(scan.next());
				double creditHours = scan.nextDouble();
				String term = scan.next();
				return new TranscriptCourse(id, section, grade, creditHours, term);
			}
			
			return null;
		}
	}
	
}
