package team9.transcriptanalyzer.input;

/*
 * Represents a single course in a transcript.
 */
public class TranscriptCourse {
		
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
	
	public String toString() {
		return "Section: "+section+" | Term: "+term+ " | CreditHours: "+creditHours+" | Grade: "+grade+"\t | ID: "+id;
	}

}