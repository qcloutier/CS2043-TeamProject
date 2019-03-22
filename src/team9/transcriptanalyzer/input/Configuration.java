package team9.transcriptanalyzer.input;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Overall representation of the Excel configuration file.
 * @author qcloutier Created on 3/20/19.
 */
public class Configuration {

	private GradeSchema gradeSchema;
	private RankSchema rankSchema;
	private List<Area> areas;
	private List<ConfigCourse> courses;
	
	public Configuration(File file) throws IOException {
		
		try (Workbook inputExcel = WorkbookFactory.create(file)) {
			
			this.gradeSchema = new GradeSchema(inputExcel.getSheet("Grade Schema"));
			this.rankSchema = new RankSchema(inputExcel.getSheet("Rank Schema"));
			
			parseCourses(inputExcel);
		}
	}
	
	/**
	 * Helper for parsing the courses, equivalents and areas.
	 */
	private void parseCourses(Workbook inputExcel) {
		
		Sheet rs = inputExcel.getSheet("Rank Schema");
		Sheet ca = inputExcel.getSheet("Course Areas");
		Sheet ce = inputExcel.getSheet("Course Equivalents");
		
		List<String> names = new ArrayList<String>();
		
		// TODO clean this up, perhaps create a method
		for (int r=2; r<rs.getLastRowNum(); r++) {
			Row row = rs.getRow(r);
			for (int c=0; c<row.getLastCellNum(); c++) {
				Cell cell = row.getCell(c);
				String contents = cell.getStringCellValue();
				if (!contents.equals("") && !names.contains(contents)) {
					names.add(contents);
				}
			}
		}
		for (int r=1; r<ca.getLastRowNum(); r++) {
			Row row = ca.getRow(r);
			for (int c=0; c<row.getLastCellNum(); c++) {
				Cell cell = row.getCell(c);
				String contents = cell.getStringCellValue();
				if (!contents.equals("") && !names.contains(contents)) {
					names.add(contents);
				}
			}
		}
		for (int r=0; r<ce.getLastRowNum(); r++) {
			Row row = ce.getRow(r);
			for (int c=0; c<row.getLastCellNum(); c++) {
				Cell cell = row.getCell(c);
				String contents = cell.getStringCellValue();
				if (!contents.equals("") && !names.contains(contents)) {
					names.add(contents);
				}
			}
		}
		
		// Create course objects
		this.courses = new ArrayList<ConfigCourse>();
		for (String name : names) {
			courses.add(new ConfigCourse(name, null, null));
		}
		
		// Create area objects
		
		// Add areas
		
		// Add equivalents
		
		
	}
	
	public GradeSchema getGradeSchema() {
		return this.gradeSchema;
	}
	
	public RankSchema getRankSchema() {
		return this.rankSchema;
	}
	
	public List<Area> getAreas() {
		return areas;
	}
	
	public List<ConfigCourse> getCourses() {
		return courses;
	}
	
	public String toString() {
		return "[" + gradeSchema + "," + rankSchema + "," + areas + "," + courses + "]";
	}
	
}
