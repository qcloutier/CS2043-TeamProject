package team9.transcriptanalyzer.input;

/*
 * Represents a single course in a transcript.
 */
public class TranscriptCourse extends Course{
		
	private String section;
	
	private String term;
	
	private double creditHours;
	
	private Grade grade;
	
	public TranscriptCourse(String section, String id, double creditHours, Grade grade, String term) {//remember to add Grade when it's merged idiot
		super(id);
		this.section=section;
		this.creditHours=creditHours;
		this.grade=grade;
		this.term=term;
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
		return "Section: "+section+" | Term: "+term+ " | CreditHours: "+creditHours+" | Grade: "+grade+"\t | ID: "+super.getID();
	}

}