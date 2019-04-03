package team9.transcriptanalyzer;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a single transcript in a cohort.
 * @author jsudz Created on 3/?/19.
 * @author rbannister Updated on 4/3/19.
 * @author qcloutier Updated on 4/3/19.
 */
public class Transcript{
	
	private List<TranscriptCourse> courses;
	
	public Transcript() {
		courses = new ArrayList<TranscriptCourse>();
	}
	
	public void addCourse(TranscriptCourse course) {
		courses.add(course);
	}
	
	public List<TranscriptCourse> getCourses(){
		return courses;
	}
	
	public String toString() {
		return "[" + courses + "]";
	}
	
}