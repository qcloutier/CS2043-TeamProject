package team9.transcriptanalyzer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests RawDistribution, mainly the calculations done by it.
 * @author qcloutier Created on 4/4/19.
 */
class TestRawDistribution {
	
	Transcript tNormal = null;
	Transcript tRepeated = null;
	Transcript tEquivalency = null;
	Transcript tNoGrade = null;
	
	Configuration configuration = null;
	Cohort cohort = null;
	Distribution distribution = null;
	
	@BeforeEach
	void setup() {
		
		GradeSchema gs = new GradeSchema(true);
		RankSchema rs = new RankSchema(true);
		
		CourseAreas ca = new CourseAreas();
		CourseEquivalents ce = new CourseEquivalents();
		ce.addEquivalency("CHEM1882", "CHEM1982");
		
		configuration = new Configuration(gs, rs, ca, ce);
		cohort = new Cohort();
		
		// A transcript with nothing special
		tNormal = new Transcript();
		tNormal.addCourse(new TranscriptCourse("FR01B", "CS2043", 4.00, Grade.A, "2018/WI"));
		tNormal.addCourse(new TranscriptCourse("FR01A", "CS3997", 3.00, Grade.AM, "2019/FA"));
		
		// A transcript with a repeated course
		tRepeated = new Transcript();
		tRepeated.addCourse(new TranscriptCourse("FR01A", "CS2043", 4.00, Grade.F, "2018/FA"));
		tRepeated.addCourse(new TranscriptCourse("FR01B", "CS2043", 4.00, Grade.C, "2019/WI"));
		
		// A transcript with a course equivalency
		tEquivalency = new Transcript();
		tEquivalency.addCourse(new TranscriptCourse("FR01A", "CHEM1882", 5.00, Grade.A, "2018/FA"));
		
		// A transcript with a course with no grade value
		tNoGrade = new Transcript();
		tNoGrade.addCourse(new TranscriptCourse("FR01A", "ECECOOP", 0.00, Grade.NA, "2018/FA"));
	}
	
	@Test
	void testConstructor() {
		
		distribution = new RawDistribution(new GradeSchema(true));
		
		String expected = "[[]]";
		String actual = distribution.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetSchema() {
		
		GradeSchema expected = new GradeSchema(true);
		
		distribution = new RawDistribution(expected);
		
		GradeSchema actual = distribution.getSchema();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculateNormal() {
		
		cohort.addTranscript(tNormal);
		
		distribution = new RawDistribution(new GradeSchema(true));
		distribution.calculate(configuration, cohort);
		
		String expected = "[["
			+ "[CS2043, [0, 0, 0, 0, 1]], "
			+ "[CS3997, [0, 0, 0, 0, 1]]"
		+ "]]";
		String actual = distribution.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculateRepeated() {
		
		cohort.addTranscript(tRepeated);
		
		distribution = new RawDistribution(new GradeSchema(true));
		distribution.calculate(configuration, cohort);
		
		String expected = "[[[CS2043, [0, 1, 1, 0, 0]]]]";
		String actual = distribution.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculateEquivalency() {
		
		cohort.addTranscript(tEquivalency);
		
		distribution = new RawDistribution(new GradeSchema(true));
		distribution.calculate(configuration, cohort);
		
		String expected = "[[[CHEM1982, [0, 0, 0, 0, 1]]]]";
		String actual = distribution.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculateNoGrade() {
		
		cohort.addTranscript(tNoGrade);
		
		distribution = new RawDistribution(new GradeSchema(true));
		distribution.calculate(configuration, cohort);
		
		String expected = "[[[ECECOOP, [1, 0, 0, 0, 0]]]]";
		String actual = distribution.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculateAll() {
		
		cohort.addTranscript(tNormal);
		cohort.addTranscript(tRepeated);
		cohort.addTranscript(tEquivalency);
		cohort.addTranscript(tNoGrade);
		
		distribution = new RawDistribution(new GradeSchema(true));
		distribution.calculate(configuration, cohort);
		
		String expected = "[["
			+ "[CS2043, [0, 1, 1, 0, 1]], "
			+ "[CS3997, [0, 0, 0, 0, 1]], "
			+ "[CHEM1982, [0, 0, 0, 0, 1]], "
			+ "[ECECOOP, [1, 0, 0, 0, 0]]"
		+ "]]";
		String actual = distribution.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testListDistribution() {
		
		cohort.addTranscript(tNormal);
		
		distribution = new RawDistribution(new GradeSchema(true));
		distribution.calculate(configuration, cohort);
		
		String[][] expected = {
			{null, "Other", "Fail", "Marginal", "Meets", "Exceeds"}, 
			{"CS2043", "0", "0", "0", "0", "1"}, 
			{"CS3997", "0", "0", "0", "0", "1"}
		};
		String[][] actual = distribution.listDistribution();
		
		boolean result = true;
		for (int r=1; r<expected.length; r++) {
			for (int c=0; c<expected[r].length; c++) {
				result &= expected[r][c].equals(actual[r][c]);
			}
		}
		
		assertTrue(result);
	}
	
}
