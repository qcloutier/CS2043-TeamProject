package team9.transcriptanalyzer;

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
	
	public Transcript() {
		courses= new ArrayList<TranscriptCourse>();
		
	}
	
	public ArrayList<TranscriptCourse> getCourses(){
		return courses;
	}
	
	public String toString() {
		String result="Courses in transcript ";
		for(TranscriptCourse course: courses) {
			result+=course+"\n";
		}
		return result;
	}
	
}