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
	
	public static void main(String[] args) {
		//System.out.println(new TranscriptCourse("FR01Z","Whoms'tsoevert've"));
	}
	
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
	
	public Grade getGrade() { //remember to uncomment this stoopid
		return grade;
	}
	
	public double getCreditHours() {
		return creditHours;
	}
	
	public String toString() {
		return "Section: "+section+" | Term: "+term+ " | Grade: You forgot to change this, didn't you?";
	}
	
	
}