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
		for (File file : files.listFiles()) {
			results.addTranscript(parseTranscript(file));
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

		String[] data = line.split("\\s\\s*"); // Regex for one or more spaces
		int length = data.length;
		
		if (length > 5) {
			
			String id = data[1];
			String section = data[2];
			Grade grade = Grade.match(data[length-3]);
			double creditHours = Double.valueOf(data[length-2]);
			String term = data[length-1];
			
			return new TranscriptCourse(section, id, creditHours, grade, term);
		}
		else {
			return null;
		}
	}
	
}
