package team9.transcriptanalyzer.input;

/**
 * Defines a abstract course entry.
 * @author qcloutier Created on 3/16/19.
 */
public abstract class Course {

	private String id;
	
	public Course(String id) {
		this.id = id;
	}
	
	public String getID() {
		return id;
	}
	
	public boolean equals(Course other) {
		return this.id == other.id;
	}
	
}
