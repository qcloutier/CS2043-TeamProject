package team9.cohort;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/*
 * Represents a Transcript which is parsed from a single text file in the cohort folder.
 */
public class Transcript{
	
	ArrayList<TranscriptCourse> courses;
	
	public static void main(String[]args) {
		//System.out.println();
	}
	
	public Transcript(File file) throws IOException{
		fileGetCourses(file);
	}
	
	private void fileGetCourses(File file) throws IOException {
		FileInputStream fis =new FileInputStream(file);
		//courses=TranscriptReader(fis); This ain't exist yet
		fis.close();
		courses.add(new TranscriptCourse("Whatever","Whoever"));
	}
	
	public ArrayList<TranscriptCourse> getCourses(){
		return courses;
	}
	
	public String toString() {
		String result="Courses in this transcript:\n";
		for(TranscriptCourse course: courses) {
			result+=course+"\n";
		}
		return result;
	}
}