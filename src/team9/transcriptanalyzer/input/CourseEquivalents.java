package team9.transcriptanalyzer.input;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Defines a course entry from the configuration file.
 * @author qcloutier Created on 3/16/19, last updated on 3/27/19.
 */
public class CourseEquivalents {
	
	private String id;
	
	private List<String> equivalents;
	
	/**
	 * Creates a course equivalency entry.
	 * @param id The course identifier.
	 * @param equivalents The identifiers of equivalent courses.
	 */
	public CourseEquivalents(String id, List<String> equivalents) {
		this.id = id;
		this.equivalents = equivalents;
	}
	
	/**
	 * Retrieves the course identifier.
	 * @return The course identifier.
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Retrieves the names of equivalent courses.
	 * @return The list of names of equivalent courses.
	 */
	public List<String> getEquivalents() {
		return equivalents;
	}
	
	public String toString() {
		return "[" + getID() + ", " + equivalents + "]";
	}
	
	/**
	 * Parses an Excel sheet into a list of course equivalencies.
	 * @param courseEqs The sheet containing the course equivalencies.
	 * @return A list of course equivalencies.
	 */
	public static List<CourseEquivalents> parseEquivalents(Sheet courseEqs) {
		
		List<CourseEquivalents> results = new ArrayList<CourseEquivalents>();
		
		for (int c=0; c<courseEqs.getRow(0).getLastCellNum(); c++) {
			
			String root = courseEqs.getRow(0).getCell(c).getStringCellValue();
			List<String> others = new ArrayList<String>();

			for (int r=1; r<courseEqs.getLastRowNum(); r++) {
				
				Cell cell = courseEqs.getRow(r).getCell(c);
				if (cell != null) {
					others.add(cell.getStringCellValue());
				}
			}
			
			results.add(new CourseEquivalents(root, others));
		}
		
		return results;
	}
	
	// Temporary, will be removed once we formally start writing JUnit tests.
	public static void main(String[]args) throws Exception {
		Workbook wb = WorkbookFactory.create(new File("demo/IO Spec Input.xlsx"));
		System.out.println(parseEquivalents(wb.getSheet("Course Equivalents")));
	}
	
}
