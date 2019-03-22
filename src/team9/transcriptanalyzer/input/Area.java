package team9.transcriptanalyzer.input;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Defines an area of courses from the configuration file.
 * @author qcloutier Created on 3/16/19.
 */
public class Area {

	private String name;
	private List<ConfigCourse> courses;
	
	public Area(String name, List<ConfigCourse> courses) {
		this.name = name;
		this.courses = courses;
	}
	
	public static List<Area> parseAreas(Sheet courseAreas) {
		
		List<Area> results = new ArrayList<Area>();
		
		Row names = courseAreas.getRow(0);
		
		List<Row> courses = new ArrayList<Row>();
		for (int i=1; i<courseAreas.getLastRowNum(); i++) {
			courses.add(courseAreas.getRow(i));
		}
		
		return results;
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
