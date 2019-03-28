package team9.transcriptanalyzer.input;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Defines an area of courses from the configuration file.
 * @author qcloutier Created on 3/16/19, last updated on 3/27/19.
 */
public class CourseArea {
	
	private String name;
	
	private List<String> courses;
	
	/**
	 * Creates a course area entry.
	 * @param name The name of the area.
	 * @param courses The names of the courses.
	 */
	public CourseArea(String name, List<String> courses) {
		this.name = name;
		this.courses = courses;
	}
	
	/**
	 * Retrieves the area name.
	 * @return The area name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Retrieves the names of the courses.
	 * @return The list of course names.
	 */
	public List<String> getCourses() {
		return courses;
	}
	
	public String toString() {
		return "[" + name + ", " + courses + "]";
	}
	
	/**
	 * Parses an Excel sheet into a list of areas.
	 * @param courseAreas The sheet containing the area information.
	 * @return A list of course areas.
	 */
	public static List<CourseArea> parseAreas(Sheet courseAreas) {
		
		List<CourseArea> results = new ArrayList<CourseArea>();
		
		for (int c=0; c<courseAreas.getRow(0).getLastCellNum(); c++) {
			
			String name = courseAreas.getRow(0).getCell(c).getStringCellValue();
			List<String> courses = new ArrayList<String>();
			
			for (int r=1; r<courseAreas.getLastRowNum(); r++) {
				
				Cell cell = courseAreas.getRow(r).getCell(c);
				if (cell != null) {
					courses.add(cell.getStringCellValue());
				}
			}
			
			results.add(new CourseArea(name, courses));
		}
		
		return results;
	}
	
	// Temporary, will be removed once we formally start writing JUnit tests.
	public static void main(String[]args) throws Exception {
		Workbook wb = WorkbookFactory.create(new File("demo/IO Spec Input.xlsx"));
		System.out.println(parseAreas(wb.getSheet("Course Areas")));
	}
	
}
