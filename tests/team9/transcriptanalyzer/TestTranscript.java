package team9.transcriptanalyzer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests portions of the system related to transcripts.
 * @author rbannister Created on 4/4/19.
 */
class TestTranscript {

	@Test
	void testTranscriptCourse() {
		String section = "abc";
		String id = "123";
		double creditHours = 3.5;
		Grade grade = Grade.AP;
		String term = "F19";
		TranscriptCourse c = new TranscriptCourse(section, id, creditHours, grade, term);
		assertEquals(c.getID(), id);
	}
	
	@Test
	void testGetID() {
		String section = "abc";
		String id = "456";
		double creditHours = 3.5;
		Grade grade = Grade.AP;
		String term = "F19";
		TranscriptCourse d = new TranscriptCourse(section, id, creditHours, (Grade) grade, term);
		assertEquals(d.getCreditHours(), creditHours);
	}

}
