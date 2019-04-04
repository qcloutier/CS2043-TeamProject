package team9.transcriptanalyzer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the calculations done by RawDistribution.
 * @author qcloutier Created on 4/4/19.
 */
class TestRawDistribution {
	
	Transcript tNormal = null;
	Transcript tRepeated = null;
	Transcript tEquivalency = null;
	Transcript tNoArea = null;
	Transcript tNoGrade = null;
	
	Configuration configuration = null;
	Cohort cohort = null;
	Distribution distribution = null;
	
	@BeforeEach
	void setup() {
		
		GradeSchema gs = new GradeSchema(true);
		RankSchema rs = new RankSchema(true);
		
		CourseAreas ca = new CourseAreas();
		ca.addArea("CS2043", "CS");
		ca.addArea("CS3997", "CS");
		ca.addArea("CHEM1982", "CHEM");
		
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
		
		// A transcript with a course with no area
		tNoArea = new Transcript();
		tNoArea.addCourse(new TranscriptCourse("FR01A", "HIST2104", 3.00, Grade.B, "2018/FA"));
		
		// A transcript with a course with no grade value
		tNoGrade = new Transcript();
		tNoGrade.addCourse(new TranscriptCourse("FR01A", "ECECOOP", 0.00, Grade.NA, "2018/FA"));
	}
	
	@Test
	void testCalculateNormal() {
		
		cohort.addTranscript(tNormal);
		
		distribution = new RawDistribution(new GradeSchema(true));
		distribution.calculate(configuration, cohort);
		
		String[][] expected = {{"CS2043", "0", "0", "0", "1"}, {"CS3997", "0", "0", "0", "1"}};
		String[][] actual = distribution.listDistribution();
		
		assertEquals(expected[0], actual[0]);
	}
	
	@Test
	void testCalculateRepeated() {
		distribution = new RawDistribution(new GradeSchema(true));
	}
	
	@Test
	void testCalculateEquivalency() {
		distribution = new RawDistribution(new GradeSchema(true));
	}
	
	@Test
	void testCalculateNoArea() {
		distribution = new RawDistribution(new GradeSchema(true));
	}
	
	@Test
	void testCalculateNoGrade() {
		distribution = new RawDistribution(new GradeSchema(true));
	}
	
	@Test
	void testCalculateAll() {
		distribution = new RawDistribution(new GradeSchema(true));
	}
	
}
