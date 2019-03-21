package team9.transcriptanalyzer.input;

import java.util.InputMismatchException;

/**
 * Defines the possible grades for a course.
 * @author qcloutier Created on 3/16/19.
 */
public enum Grade {
	
	Ap	(4.3),
	A	(4),
	Am	(3.7),
	Bp	(3.3),
	B	(3),
	Bm	(2.7),
	Cp	(2.3),
	C	(2),
	D	(1),
	F	(0);
	
	private final double point;
	
	Grade(double point) {
		this.point = point;
	}
	
	public double asPoint() {
		return point;
	}
	
	public static Grade match(String grade) {
		
		switch (grade) {
			case "A+"	: return Grade.Ap;
			case "A"	: return Grade.A;
			case "A-"	: return Grade.Am;
			case "B+"	: return Grade.Bp;
			case "B"	: return Grade.B;
			case "B-"	: return Grade.Bm;
			case "C+"	: return Grade.Cp;
			case "C"	: return Grade.C;
			case "D"	: return Grade.D;
			case "F"	: return Grade.F;
		}
		
		throw new InputMismatchException("Grade could not be mapped");
	}
	
}
