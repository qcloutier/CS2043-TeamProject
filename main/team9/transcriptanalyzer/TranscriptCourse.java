package team9.transcriptanalyzer;

/*
 * Represents a single course in a transcript.
 * @author jsudz Created on 3/?/19.
 * @author rbannister Updated on 4/3/19.
 * @author qcloutier Updated on 4/3/19.
 */
public class TranscriptCourse implements Comparable<TranscriptCourse>{
		
	private String id;
	
	private String section;
	
	private String term;
	
	private double creditHours;
	
	private Grade grade;
	
	public TranscriptCourse(String section, String id, double creditHours, Grade grade, String term) {
		this.id = id;
		this.section=section;
		this.creditHours=creditHours;
		this.grade=grade;
		this.term=term;
	}
	
	public String getID() {
		return id;
	}
	
	public String getSection() {
		return section;
	}
	
	public String getTerm() {
		return term;
	}
	
	public Grade getGrade() {
		return grade;
	}
	
	public double getCreditHours() {
		return creditHours;
	}
	
	public boolean equals(TranscriptCourse other) {
		return(other.getID().equals(id));
	}
	
	public int compareTo(TranscriptCourse other) {
		return id.compareTo(other.getID());
	}
	
	public String toString() {
		return "[" + id + ", " + section + ", " + term + ", " + creditHours + ", " + grade + "]";
	}

}