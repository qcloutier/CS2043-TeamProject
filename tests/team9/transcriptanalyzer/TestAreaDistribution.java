package team9.transcriptanalyzer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test of the AreaDistribution class.
 * @author mholt1 Created on 4/4/19.
 */
class TestAreaDistribution {

	GradeSchema gs;
	RankSchema rs;
	CourseAreas ca;
	CourseEquivalents ce;
	
	AreaDistribution areaDist;
	
	Cohort cohort;
	Configuration config;
	
	Transcript t1;
	Transcript t2;
	Transcript t3;
	
	@BeforeEach
	void setUp() {
		gs = new GradeSchema(true);
		rs = new RankSchema(true);
		
		ca = new CourseAreas();
		ca.addArea("MATH1003", "Math");
		ca.addArea("MATH1503", "Math");
		ca.addArea("BIOL1000", "Science");
		ca.addArea("ECE4093", "Core");
		
		ce = new CourseEquivalents();
		ce.addEquivalency("MATH1053", "MATH1003");
		
		config = new Configuration(gs, rs, ca, ce);
		
		t1 = new Transcript();
		t1.addCourse(new TranscriptCourse("MATH1003", "FR01A", Grade.BM, 3, "2017/FA"));
		t1.addCourse(new TranscriptCourse("BIOL1000", "FR01B", Grade.A, 4, "2018/WI"));
		
		t2 = new Transcript();
		t2.addCourse(new TranscriptCourse("MATH1053", "FR03B", Grade.C, 4, "2019/WI"));
		t2.addCourse(new TranscriptCourse("MATH1503", "FR02A", Grade.D, 4, "2018/FA"));
		
		t3 = new Transcript();
		t3.addCourse(new TranscriptCourse("ECE4093", "FR01A", Grade.BP, 5, "2016/FA"));
		
		cohort = new Cohort();
	}	
	
	@Test
	void testConstructor() {
		areaDist = new AreaDistribution(gs);
		
		assertEquals("[[]]", areaDist.toString());
	}
	
	@Test 
	void testGetSchema() {
		areaDist = new AreaDistribution(gs);
		
		assertEquals(gs.toString(), areaDist.getSchema().toString());
	}
	
	@Test
	void testNormalTranscript() {
		areaDist = new AreaDistribution(gs);
		
		cohort.addTranscript(t3);
		areaDist.calculate(config, cohort);
		
		String expected = "[[[Science, [0, 0, 0, 0]], [Core, [0, 0, 1, 0]], [Math, [0, 0, 0, 0]]]]";
		
		assertEquals(expected, areaDist.toString());
	}
	
	@Test
	void testEquivalentCourse() {
		areaDist = new AreaDistribution(gs);
		
		cohort.addTranscript(t2);
		areaDist.calculate(config, cohort);
		
		String expected = "[[[Science, [0, 0, 0, 0]], [Core, [0, 0, 0, 0]], [Math, [0, 1, 0, 0]]]]";
		
		assertEquals(expected, areaDist.toString());
	}
	
	@Test
	void testTwoAreas() {
		areaDist = new AreaDistribution(gs);
		
		cohort.addTranscript(t1);
		areaDist.calculate(config, cohort);
		
		String expected = "[[[Science, [0, 0, 0, 1]], [Core, [0, 0, 0, 0]], [Math, [0, 0, 1, 0]]]]";
		
		assertEquals(expected, areaDist.toString());
	}
	
	@Test
	void testMultipleTranscripts() {
		areaDist = new AreaDistribution(gs);
		
		cohort.addTranscript(t1);
		cohort.addTranscript(t2);
		cohort.addTranscript(t3);
		areaDist.calculate(config, cohort);
		
		String expected = "[[[Science, [0, 0, 0, 1]], [Core, [0, 0, 1, 0]], [Math, [0, 1, 1, 0]]]]";
		
		assertEquals(expected, areaDist.toString());
	}
	
	@Test 
	void testNoTranscripts() {
		areaDist = new AreaDistribution(gs);

		areaDist.calculate(config, cohort);
		
		String expected = "[[[Science, [0, 0, 0, 0]], [Core, [0, 0, 0, 0]], [Math, [0, 0, 0, 0]]]]";
		
		assertEquals(expected, areaDist.toString());
	}
	
	@Test 
	void testListDistributions() {
		areaDist = new AreaDistribution(gs);
		
		cohort.addTranscript(t1);
		areaDist.calculate(config, cohort);
		
		String[][] expectedArray = {{null, "Fail", "Marginal", "Meets", "Exceeds"},
							   {"Science", "0", "0", "0", "1"}, 
							   {"Core", "0", "0", "0", "0"},
							   {"Math", "0", "0", "1", "0"}};
		
		String[][] actualArray = areaDist.listDistribution();
		
		String expected = "", actual = "";
		for(int i = 0; i < expectedArray.length; i++) {
			for(int j = 0; j < expectedArray[i].length; j++) {
				expected += expectedArray[i][j];
				actual += actualArray[i][j];
			}
		}
		
		assertEquals(expected, actual);
	}
}
