package team9.transcriptanalyzer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Implementation of CohortReader for text files.
 * @author rbannister Created on 4/3/19.
 * @author qcloutier Updated on 4/5/19.
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
		
		// Warn the user that a transcript was skipped
		if (skipFlag) {
			Messenger.parseError();
		}

		return results;
	}
	
	private Transcript parseTranscript(File file) throws IOException {
		
		try (Scanner sc = new Scanner(file)) {
			
			Transcript results = new Transcript();
			while (sc.hasNextLine()) {
				
				String line = sc.nextLine();
				if (line != null && !line.isEmpty()) {
					results.addCourse(parseCourse(line));
				}
			}
			
			return results;
		}
	}
	
	private TranscriptCourse parseCourse(String line) {
		
		try (Scanner sc = new Scanner(line);) {
			
			String id = sc.next();
			String section = sc.next();
			
			// Skip course name by parsing on two or more spaces
			sc.useDelimiter("\\s\\s\\s*"); 
			sc.next(); 
			sc.useDelimiter("\\s\\s*"); 
			
			// Some entries might not have a grade
			Grade grade;
			if (!sc.hasNextDouble()) {
				grade = Grade.match(sc.next());
			}
			else {
				grade = Grade.NA;
			}

			Double creditHours = sc.nextDouble();
			
			// Skip # symbol if present
			String term = sc.next();
			if (term.equals("#")) {
				term = sc.next();
			}
				
			return new TranscriptCourse(id, section, grade, creditHours, term);
		}
	}
	
}
