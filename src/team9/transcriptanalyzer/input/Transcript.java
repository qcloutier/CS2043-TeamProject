package team9.transcriptanalyzer.input;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * Represents a Transcript which is parsed from a single text file in the cohort folder.
 */
public class Transcript{
	
	ArrayList<TranscriptCourse> courses;
	
	String fileName;
	
	public Transcript(File file) throws IOException{
		courses= new ArrayList<TranscriptCourse>();
		fileName= file.getName();
		fileGetCourses(file);
	}
	
	private void fileGetCourses(File file) throws IOException {
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
				courses.add(new TranscriptCourse(section, id, creditHours, Grade.match(grade),term));
			}
		}
		br.close();
		fr.close();
	}
	
	public ArrayList<TranscriptCourse> getCourses(){
		return courses;
	}
	
	public String toString() {
		String result="Courses in transcript "+fileName+":\n";
		for(TranscriptCourse course: courses) {
			result+=course+"\n";
		}
		return result;
	}
	
}