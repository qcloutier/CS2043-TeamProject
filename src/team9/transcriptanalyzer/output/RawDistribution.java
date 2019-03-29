package team9.transcriptanalyzer.output;

import java.util.List;
import java.util.ArrayList;
import team9.transcriptanalyzer.input.*;

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
	
	private void addEntry(CourseEquivalents course, List<Integer> values) {
		entries.add(new RawEntry(course, values));
	}
	
	/*public void calculate(Configuration config, Cohort cohort) {
		//TODO
	}*/
	
	public void calculate(Configuration config, Cohort cohort) {
		ArrayList<Integer> values = new ArrayList<Integer>();
		for(int i = 0; i < 5; i++) {
			values.add(0);
		}
		
		List<String> courses = cohort.getMasterList();
		List<Transcript> transcripts = cohort.getTranscripts();
		
		GradeSchema gradeSchema = config.getGradeSchema();
		
		for(String course : courses) {
			
			for(Transcript transcript : transcripts) {
				
				List<TranscriptCourse> transcriptCourses = transcript.getCourses();
				for(TranscriptCourse transcriptCourse : transcriptCourses) {
					
					if(transcriptCourse.getID().equals(course) || checkEquivalents(transcriptCourse, course)) {
						Grade grade = transcriptCourse.getGrade();
						if(grade.asPoint() >= gradeSchema.getLower("exceeds").asPoint()) {
							values.set(4, values.get(4) + 1);
						}
						if(grade.asPoint() >= gradeSchema.getLower("meets").asPoint()) {
							values.set(3, values.get(3) + 1);
						}
						if(grade.asPoint() >= gradeSchema.getLower("marginal").asPoint()) {
							values.set(2, values.get(2) + 1);
						}
						if(grade.asPoint() >= gradeSchema.getLower("fails").asPoint()) {
							values.set(1, values.get(1) + 1);
						}
						else {
							values.set(0, values.get(0) + 1);
						}
					}
				}
			}
			
			entries.add(new RawEntry(course, values));
			for(int i = 0; i < 5; i++) {
				values.set(i, 0);
			}
		}
	}
	
	public String[][] getRawDistribution(){
		
		String [][] rawDistributions = new String[entries.size()][6];
		
		for(int i = 0; i < entries.size(); i++) {
			
			RawEntry name = entries.get(i);
			
			for(int j = 1; j < 6; j++) {
				
				rawDistributions[1]
			}
		}
		
		return rawDistributions;
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