package team9.transcriptanalyzer.input;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Defines a course entry from the configuration file.
 * @author qcloutier Created on 3/16/19.
 */
public class ConfigCourse extends Course {

	private Area area;
	private List<ConfigCourse> equivalents;
	
	public ConfigCourse(String id, Area area, List<ConfigCourse> equivalents) {
		super(id);
		
		this.area = area;
		this.equivalents = equivalents;
	}
	
	public static List<ConfigCourse> parseCourses(Sheet courseAreas) {
		
		List<ConfigCourse> results = new ArrayList<ConfigCourse>();
		
		Row names = courseAreas.getRow(0);
		
		List<Row> courses = new ArrayList<Row>();
		for (int i=1; i<courseAreas.getLastRowNum(); i++) {
			courses.add(courseAreas.getRow(i));
		}
		
		return results;
	}
	
	public Area getArea() {
		return area;
	}
	
	public List<ConfigCourse> getEquivalents() {
		return equivalents;
	}
	
	public String toString() {
		return "[" + area + ", " + equivalents + "]";
	}
	
}
