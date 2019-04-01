package team9.transcriptanalyzer.output;

import java.util.List;
import java.util.ArrayList;

import team9.transcriptanalyzer.input.GradeSchema;
import team9.transcriptanalyzer.input.Configuration;
import team9.transcriptanalyzer.input.Cohort;
import team9.transcriptanalyzer.input.CourseEquivalents;
import team9.transcriptanalyzer.input.Transcript;
import team9.transcriptanalyzer.input.TranscriptCourse;
import team9.transcriptanalyzer.input.Grade;

/**
 * Defines a RawDistribution for a specific course
 * @author mholt1 created on 3/16/2019
 */
public class RawDistribution extends Distribution{
	
	private List<RawEntry> entries;
	
	public RawDistribution(GradeSchema schema, CourseEquivalents course) {
		super(schema);
		entries = new ArrayList<RawEntry>();
	}
	
	private void addEntry(String course, List<Integer> values) {
		entries.add(new RawEntry(course, values));
	}
	
	public void calculate(Configuration config, Cohort cohort) {
		GradeSchema gradeSchema = config.getGradeSchema();
		CourseEquivalents courseEquivalents = config.getCourseEquivalencies();
		List<String> courseIds = new ArrayList<String>();
		List<String> levelNames = gradeSchema.listNames();

		for(Transcript transcript : cohort.getTranscripts()) {
			
			for(TranscriptCourse course : transcript.getCourses()) {
				
				Grade grade = course.getGrade();
				String courseName = courseEquivalents.getEquivalency(course.getID());
				
				if(courseIds.indexOf(courseName) == -1) {
					List<Integer> values = new ArrayList<Integer>();
					for(int i = 0; i < levelNames.size(); i++) {
						values.add(0);
					}
					this.addEntry(courseName, values);
					courseIds.add(courseName);
				}
				
				for(int i = 0; i < levelNames.size(); i++) {
					if(grade.asPoint() >= gradeSchema.getLower(levelNames.get(i)).asPoint() && grade.asPoint() <= gradeSchema.getUpper(levelNames.get(i)).asPoint()) {
						for(int j = 0; j < entries.size(); j++) {
							if(entries.get(j).course.equals(courseName)) {
								entries.get(j).values.set(i, entries.get(j).values.get(i) + 1);
							}
						}
						break;
					}
				}
			}
		}
	}
	
	public String[][] getRawDistribution(){
		String[][] distributions = new String[entries.size()][entries.get(0).values.size() + 1];
		for(int i = 0; i < entries.size(); i++) {
			RawEntry nextEntry = entries.get(i);
			for(int j = 0; j < nextEntry.values.size() + 1; j++) {
				if(j == 0) {
					distributions[i][j] = nextEntry.course;
				}
				else {
					distributions[i][j] = nextEntry.values.get(j).toString();
				}
			}
		}
		return distributions;
	}
	
	private class RawEntry{
		
		public String course;
		public List<Integer> values;
		
		public RawEntry(String course, List<Integer> values) {
			this.course = course;
			this.values = values;
			values = new ArrayList<Integer>();
		}	
	}
	
}