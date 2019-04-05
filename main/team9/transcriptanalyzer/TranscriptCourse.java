package team9.transcriptanalyzer;

/** 
 * Represents a single course in a transcript.
 * @author jsudz Created on 3/?/19.
 * @author rbannister Updated on 4/3/19.
 * @author qcloutier Updated on 4/3/19.
 */
public class TranscriptCourse {
		
	private String id;
	
	private String section;
	
	private String term;
	
	private double creditHours;
	
	private Grade grade;
	
	/**
	 * Creates a transcript course entry.
	 * @param id The ID of the course.
	 * @param section The course section.
	 * @param grade The grade received.
	 * @param creditHours The credit hours worth.
	 * @param term The term it was taken.
	 */
	public TranscriptCourse(String id, String section, Grade grade, double creditHours, String term) {
		this.id = id;
		this.section=section;
		this.creditHours=creditHours;
		this.grade=grade;
		this.term=term;
	}
	
	/**
	 * Retrieves the ID of the course.
	 * @return The ID of the course.
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Retrieves the course section.
	 * @return The course section.
	 */
	public String getSection() {
		return section;
	}
	
	/**
	 * Retrieves the term it was taken.
	 * @return The term it was taken.
	 */
	public String getTerm() {
		return term;
	}
	
	/**
	 * Retrieves the grade received.
	 * @return The grade received.
	 */
	public Grade getGrade() {
		return grade;
	}
	
	/**
	 * Retrieves the credit hours worth.
	 * @return The credit hours worth.
	 */
	public double getCreditHours() {
		return creditHours;
	}
	
	public String toString() {
		return "[" + id + ", " + section + ", " + term + ", " + creditHours + ", " + grade + "]";
	}

}