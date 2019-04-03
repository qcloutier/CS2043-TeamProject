package team9.transcriptanalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Implementation of CohortReader for Text files.
 * @author rbannister Created on 4/03/19.
 */

public class CohortTextReader implements CohortReader {
	
	ArrayList<TranscriptCourse> courses;
	
	public Cohort fileGetCourse(File file) throws IOException{
	
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		String line="";
		String[] courseInfo=null;
		String separator="\\s\\s+";//regular expression for 2 or more spaces
		String section;
		String id;
		String grade;
		String term;
		int expectedElements=7;
	
		while ((line=br.readLine())!=null) {	
			courseInfo=line.split(separator);
			if(courseInfo.length==expectedElements) {	
				section=courseInfo[2];
				id=courseInfo[1];
				grade=courseInfo[4];
				term=courseInfo[6];
				double creditHours=Double.valueOf(courseInfo[5]);
				TranscriptCourse currentCourse=new TranscriptCourse(section, id, creditHours, Grade.match(grade),term);
				courses.add(currentCourse);
			}
		}
		br.close();
		fr.close();
		return null;
	}


	@Override
	public Cohort read(File file) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
