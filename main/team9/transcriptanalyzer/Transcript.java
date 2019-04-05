package team9.transcriptanalyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single transcript in a cohort.
 * @author jsudz Created on 3/?/19.
 * @author rbannister Updated on 4/3/19.
 * @author qcloutier Updated on 4/3/19.
 */
public class Transcript{
	
	private List<TranscriptCourse> courses;
	
	/**
	 * Creates a Transcript and initializes its list.
	 */
	public Transcript() {
		courses = new ArrayList<TranscriptCourse>();
	}
	

	/**
	 * Adds a course to the transcript.
	 * @param course The course to add.
	 */
	public void addCourse(TranscriptCourse course) {
		courses.add(course);
	}
	
	/**
	 * Retrieves the list of courses.
	 * @return The list of courses.
	 */
	public List<TranscriptCourse> getCourses(){
		return courses;
	}
	
	public String toString() {
		return "[" + courses + "]";
	}
	
}