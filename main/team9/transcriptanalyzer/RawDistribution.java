package team9.transcriptanalyzer;

import java.util.List;
import java.util.ArrayList;

/**
 * Defines a RawDistribution for a specific course
 * @author mholt1 Created on 3/16/2019.
 * @author qcloutier Updated on 4/4/19.
 */
public class RawDistribution extends Distribution{
	
	private List<RawEntry> entries;
	
	/**
	 * Creates a RawDistribution and initializes its lists.
	 * @param schema The grade schema to use.
	 */
	public RawDistribution(GradeSchema schema) {
		super(schema);
		entries = new ArrayList<RawEntry>();
	}
	
	private void addEntry(String course, List<Integer> values) {
		entries.add(new RawEntry(course, values));
	}
	
	/**
	 * Calculates the raw distribution given a configuration and cohort.
	 * @param config The configuration to use for equivalencies.
	 * @param cohort The cohort with transcripts to process.
	 */
	public void calculate(Configuration config, Cohort cohort) {
		GradeSchema gradeSchema = config.getGradeSchema();
		CourseEquivalents courseEquivalents = config.getCourseEquivalencies();
		List<String> courseIds = new ArrayList<String>();
		List<String> levelNames = new ArrayList<String>();
		List<String> gradeLevelNames = gradeSchema.listNames();
		levelNames.add(0, "Other");
		for(int i = 0; i < gradeLevelNames.size(); i++) {
			levelNames.add(gradeLevelNames.get(i));
		}

		for(Transcript transcript : cohort.getTranscripts()) {
			for(TranscriptCourse course : transcript.getCourses()) {
				
				Grade grade = course.getGrade();
				String courseName = courseEquivalents.getEquivalency(course.getID());
				if(courseName == null) {
					courseName = course.getID();
				}
				
				if(courseIds.indexOf(courseName) == -1) {
					List<Integer> values = new ArrayList<Integer>();
					for(int i = 0; i < levelNames.size(); i++) {
						values.add(0);
					}
					this.addEntry(courseName, values);
					courseIds.add(courseName);
				}
				for(int i = 1; i < levelNames.size(); i++) {
					if((grade.asPoint() >= gradeSchema.getLower(levelNames.get(i)).asPoint() && grade.asPoint() <= gradeSchema.getUpper(levelNames.get(i)).asPoint()) || grade.toString() == "NA") {
						for(int j = 0; j < entries.size(); j++) {
							if(entries.get(j).course.equals(courseName)) {
								if(grade.toString() == "NA") {
									entries.get(j).values.set(0, entries.get(j).values.get(0) + 1);
									break;
								}
								entries.get(j).values.set(i, entries.get(j).values.get(i) + 1);
							}
						}
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Creates a 2D array of the distributions 
	 * for easy writing to a file.
	 * @return The distributions in a 2D array.
	 */
	public String[][] listDistribution(){
		String[][] distributions = new String[entries.size() + 1][entries.get(0).values.size() + 1];
		List<String> levels = this.getSchema().listNames();
		distributions[0][1] = "Other";
		for(int j = 2; j < levels.size() + 2; j++) {
			distributions[0][j] = levels.get(j-2);
		}
		for(int i = 0; i < entries.size(); i++) {
			RawEntry nextEntry = entries.get(i);
			for(int j = 0; j < nextEntry.values.size() + 1; j++) {
				if(j == 0) {
					distributions[i+1][j] = nextEntry.course;
				}
				else {
					distributions[i+1][j] = nextEntry.values.get(j-1).toString();
				}
			}
		}
		return distributions;
	}
	
	public String toString() {
		return "[" + entries + "]";
	}
	
	/**
	 * Defines an individual entry in the raw distribution list.
	 */
	private class RawEntry{
		
		public String course;
		
		public List<Integer> values;
		
		/**
		 * Creates an entry in the raw distributions.
		 * @param course The ID of the course.
		 * @param values The counts for the course.
		 */
		public RawEntry(String course, List<Integer> values) {
			this.course = course;
			this.values = values;
		}	
	
		public String toString() {
			return "[" + course + ", " + values + "]";
		}
		
	}
	
}