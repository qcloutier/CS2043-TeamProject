package team9.cohort;
/*
 * Represents a single course in a transcript.
 */

public class TranscriptCourse{
		
	private String section;
	
	private String term;
	
	//private Grade grade; commented until Grade is merged
	
	public static void main(String[] args) {
		System.out.println(new TranscriptCourse("FR01Z","Whoms'tsoevert've"));
	}
	
	public TranscriptCourse(String section, String term) {//remember to add Grade when it's merged idiot
		this.section=section;
		this.term=term;
	}
	
	public String getSection() {
		return section;
	}
	
	public String getTerm() {
		return term;
	}
	
	/*public Grade getGrade() { //remember to uncomment this stoopid
		return grade;
	}*/
	
	public String toString() {
		return "Section: "+section+" | Term: "+term+ " | Grade: You forgot to change this, didn't you?";
	}
	
	
}