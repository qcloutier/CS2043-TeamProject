package team9.transcriptanalyzer.input;

import java.util.List;

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
