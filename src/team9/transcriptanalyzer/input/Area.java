package team9.transcriptanalyzer.input;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines an area of courses from the configuration file.
 * @author qcloutier Created on 3/16/19.
 */
public class Area {

	private String name;
	private List<ConfigCourse> courses;
	
	public Area(String name) {
		this.name = name;
		
		courses = new ArrayList<ConfigCourse>();
	}
	
	public String getName() {
		return name;
	}
	
	public void addCourse(ConfigCourse course) {
		courses.add(course);
	}
	
	public List<ConfigCourse> listCourses() {
		return courses;
	}
	
	public String toString() {
		return "[" + name + ", " + courses + "]";
	}
	
}
