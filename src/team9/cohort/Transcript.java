package team9.cohort;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import team9.transcriptanalyzer.input.Grade;
/*
 * Represents a Transcript which is parsed from a single text file in the cohort folder.
 */
public class Transcript{
	
	ArrayList<TranscriptCourse> courses;
	
	String fileName;
	
	public Transcript(File file) throws IOException{
		courses= new ArrayList<TranscriptCourse>();
		fileName= file.getName();
		fileGetCourses(file);
	}
	
	private void fileGetCourses(File file) throws IOException {
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		String line="";
		String[] courseInfo=null;
		String separator="\\s\\s+";//regular expression for 2 or more spaces
		String section;
		String name;
		String grade;
		int expectedElements=7;
	
		while ((line=br.readLine())!=null) {	
			courseInfo=line.split(separator);
			if(courseInfo.length==expectedElements) {	
				section=courseInfo[2];
				name=courseInfo[1];
				grade=courseInfo[4];
				double creditHours=Double.valueOf(courseInfo[5]);
				courses.add(new TranscriptCourse(section, name, creditHours, gradeStringToEnum(grade)));
			}
		}
		br.close();
		fr.close();
	}
	
	public ArrayList<TranscriptCourse> getCourses(){
		return courses;
	}
	
	private Grade gradeStringToEnum(String gradeStr) {
		Grade enumGrade=Grade.F;
		switch(gradeStr) {
			case("A+"):
				enumGrade=Grade.Ap;
				break;
			case("A"):
				enumGrade=Grade.A;
				break;
			case("A-"):
				enumGrade=Grade.Am;
				break;
			case("B+"):
				enumGrade=Grade.Bp;
				break;
			case("B"):
				enumGrade=Grade.B;
				break;
			case("B-"):
				enumGrade=Grade.Bm;
				break;
			case("C+"):
				enumGrade=Grade.Cp;
				break;
			case("C"):
				enumGrade=Grade.C;
				break;
			case("D"):
				enumGrade=Grade.D;
				break;
			case("F"):
				enumGrade=Grade.F;
				break;
		}
		return enumGrade;
	}
	
	public String toString() {
		String result="Courses in transcript "+fileName+":\n";
		for(TranscriptCourse course: courses) {
			result+=course+"\n";
		}
		return result;
	}
}