package team9.transcriptanalyzer;

/**
 * Overall representation of the configuration file.
 * @author qcloutier Created on 3/20/19, last updated on 3/30/19.
 */
public class Configuration {

	private GradeSchema gradeSchema;
	
	private RankSchema rankSchema;
	
	private CourseAreas courseAreas;
	
	private CourseEquivalents courseEquivalents;
	
	/**
	 * Creates a representation of the configuration file.
	 * @param gs The grading schema.
	 * @param rs The ranking schema.
	 * @param ca The course areas.
	 * @param ce The course equivalents.
	 */
	public Configuration(GradeSchema gs, RankSchema rs, CourseAreas ca, CourseEquivalents ce) {
		this.gradeSchema = gs;
		this.rankSchema = rs;
		this.courseAreas = ca;
		this.courseEquivalents = ce;
	}
	
	/**
	 * Retrieves the grade schema.
	 * @return The grade schema.
	 */
	public GradeSchema getGradeSchema() {
		return this.gradeSchema;
	}
	
	/**
	 * Retrieves the rank schema.
	 * @return The rank schema.
	 */
	public RankSchema getRankSchema() {
		return this.rankSchema;
	}
	
	/**
	 * Retrieves the course areas.
	 * @return The course areas.
	 */
	public CourseAreas getCourseAreas() {
		return courseAreas;
	}
	
	/**
	 * Retrieves the course equivalencies.
	 * @return the course equivalencies.
	 */
	public CourseEquivalents getCourseEquivalencies() {
		return courseEquivalents;
	}
	
	public String toString() {
		return "[" + gradeSchema + "," + rankSchema + "," + courseAreas + "," + courseEquivalents + "]";
	}
	
}
