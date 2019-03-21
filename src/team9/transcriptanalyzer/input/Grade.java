package team9.transcriptanalyzer.input;

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
	
}
