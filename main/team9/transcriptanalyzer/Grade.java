package team9.transcriptanalyzer;

/**
 * Defines the possible grades for a course.
 * @author qcloutier Created on 3/16/19.
 */
public enum Grade {
	
	AP	(4.3, "A+"),
	A	(4, "A"),
	AM	(3.7, "A-"),
	BP	(3.3, "B+"),
	B	(3, "B"),
	BM	(2.7, "B-"),
	CP	(2.3, "C+"),
	C	(2, "C"),
	D	(1, "D"),
	F	(0, "F"),
	NA	(Double.NaN, "NA");
	
	private final double POINT;
	private final String LETTER;
	
	private Grade(double point, String letter) {
		this.POINT = point;
		this.LETTER = letter;
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
		
		for (Grade g : Grade.values()) {
			if (grade.equals(g.LETTER)) {
				return g;
			}
		}
		
		return Grade.NA;
	}
	
	public String toString() {
		return this.LETTER;
	}
	
}
