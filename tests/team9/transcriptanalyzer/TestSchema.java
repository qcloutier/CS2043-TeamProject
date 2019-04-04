package team9.transcriptanalyzer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team9.transcriptanalyzer.GradeSchema;

import team9.transcriptanalyzer.RankSchema;

/**
 * Tests the Schema, GradeSchema, and RankSchema objects.
 * @author jsudz Created on 4/4/19.
 */
class TestSchema {

	GradeSchema defaultGrade;
	
	RankSchema defaultRank;
	
	
	@BeforeEach
	void setUp() throws Exception{
		defaultGrade=new GradeSchema(true);
		defaultRank= new RankSchema(true);		
	}
	@Test
	void testDefaultGradeSchemaNames() {
		List<String> names=defaultGrade.listNames();
		assertTrue(names.contains("Meets")&&names.contains("Marginal")&&names.contains("Exceeds")&&names.contains("Fail"));
	}
	
	@Test
	void testDefaultGradeSchemaGrades() {
		for(String name: defaultGrade.listNames()) {
			if(defaultGrade.getLower(name)==null||defaultGrade.getUpper(name)==null)
				fail("A default grade boundary is null");
		}
		return;
	}
	
	@Test
	void testDefaultRankSchemaNames() {
		List<String> names=defaultRank.listNames();
		assertTrue(names.contains("Freshman")&&names.contains("Sophomore")&&names.contains("Junior")&&names.contains("Senior"));
	}
	
	@Test
	void testDefaultRankSchemaCreditHours() {
		for(String name: defaultRank.listNames()) {
			if(defaultRank.getMinCreditHours(name)<0)
				fail("A default Credit Hour minimum is negative");
		}
		return;
	}
	
	@Test
	void testRankSchemaCourses(){
		RankSchema rank=new RankSchema(false);
		ArrayList<String> courses1=new ArrayList<String>();
		courses1.add("COURSE1");
		ArrayList<String> courses2=new ArrayList<String>();
		courses2.add("COURSE1");
		courses2.add("COURSE2");
		ArrayList<String> courses3=new ArrayList<String>();
		courses3.add("COURSE1");
		courses3.add("COURSE2");
		courses3.add("COURSE3");
		ArrayList<String> courses4=new ArrayList<String>();
		courses4.add("COURSE1");
		courses4.add("COURSE2");
		courses4.add("COURSE3");
		courses4.add("COURSE4");
		
		rank.addLevel("Rank1", 1, courses1);
		rank.addLevel("Rank2", 2, courses2);
		rank.addLevel("Rank3", 3, courses3);
		rank.addLevel("Rank4", 4, courses4);
		int i=1;
		for(String name: rank.listNames()) {
			if(rank.getMinCreditHours(name)!=i)
				fail("Credit Hours were not set correctly");
			if(rank.getRequiredCourses(name).size()!=i)
				fail("Not all required courses were added");
			i++;
		}
		return;
		
	}

}
