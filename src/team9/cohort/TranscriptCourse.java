package team9.cohort;
/*
 * Represents a single course in a transcript.
 */

import team9.transcriptanalyzer.input.Grade;

public class TranscriptCourse{
		
	private String section;
	
	private String term;
	
	private double creditHours;
	
	private Grade grade;
	
	public TranscriptCourse(String section, String term, double creditHours, Grade grade) {//remember to add Grade when it's merged idiot
		this.section=section;
		this.term=term;
		this.creditHours=creditHours;
		this.grade=grade;				
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
		return "Section: "+section+" | Term: "+term+ " | CreditHours: "+creditHours+" | Grade: "+grade;
	}
	
	
}