package team9.transcriptanalyzer.input;

/**
 * Defines the possible grades for a course.
 * @author qcloutier Created on 3/16/19.
 */
public enum Grade {
	
	AP	(4.3),
	A	(4),
	AM	(3.7),
	BP	(3.3),
	B	(3),
	BM	(2.7),
	CP	(2.3),
	C	(2),
	D	(1),
	F	(0),
	O	(Double.NaN);
	
	private final double POINT;
	
	private Grade(double point) {
		this.POINT = point;
	}
	
	/**
	 * Retrieves the grade point value of a letter grade.
	 * @return The grade point value of a letter grade.
	 */
	public double asPoint() {
		return POINT;
	}
	
	/**
	 * Matches a string to a grade type.
	 * @param grade The string to match upon.
	 * @return The corresponding grade type.
	 */
	public static Grade match(String grade) {
		
		switch (grade) {
			case "A+"	: return Grade.AP;
			case "A"	: return Grade.A;
			case "A-"	: return Grade.AM;
			case "B+"	: return Grade.BP;
			case "B"	: return Grade.B;
			case "B-"	: return Grade.BM;
			case "C+"	: return Grade.CP;
			case "C"	: return Grade.C;
			case "D"	: return Grade.D;
			case "F"	: return Grade.F;
			default		: return Grade.O;
		}
	}
	
}
