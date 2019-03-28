package team9.transcriptanalyzer.input;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Overall representation of the Excel configuration file.
 * @author qcloutier Created on 3/20/19, last updated on 3/27/19.
 */
public class Configuration {

	private GradeSchema gradeSchema;
	
	private RankSchema rankSchema;
	
	private List<CourseArea> areas;
	
	private List<CourseEquivalents> courses;
	
	/**
	 * Creates a representation of the Excel configuration file.
	 * @param file The input file.
	 * @throws IOException If the file cannot be read and parsed.
	 */
	public Configuration(File file) throws IOException {
		
		try (Workbook inputExcel = WorkbookFactory.create(file)) {
			
			this.gradeSchema = new GradeSchema(inputExcel.getSheet("Grade Schema"));
			this.rankSchema = new RankSchema(inputExcel.getSheet("Rank Schema"));
			
			this.areas = CourseArea.parseAreas(inputExcel.getSheet("Course Areas"));
			this.courses = CourseEquivalents.parseEquivalents(inputExcel.getSheet("Course Equivalents"));
		}
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
	 * Retrieves the list of course areas.
	 * @return The list of course areas.
	 */
	public List<CourseArea> getCourseAreas() {
		return areas;
	}
	
	/**
	 * Retrieves the list of course equivalencies.
	 * @return the list of course equivalencies.
	 */
	public List<CourseEquivalents> getCourseEquivalencies() {
		return courses;
	}
	
	public String toString() {
		return "[" + gradeSchema + "," + rankSchema + "," + areas + "," + courses + "]";
	}
	
	// Temporary, will be removed once we formally start writing JUnit tests.
	public static void main(String[]args) throws Exception {
		System.out.println(new Configuration(new File("demo/IO Spec Input.xlsx")));
	}
	
}
