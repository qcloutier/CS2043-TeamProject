package team9.transcriptanalyzer.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Overall representation of the Excel configuration file.
 * @author qcloutier Created on 3/20/19.
 */
public class Configuration {

	private File file;
	private Workbook workbook;
	
	private GradeSchema gradeSchema;
	private RankSchema rankSchema;
	private List<Area> areas;
	private List<ConfigCourse> courses;
	
	public Configuration(File file) throws IOException {
		this.file = file;
		
		openWorkbook();
		parseSchemas();
		parseCourses();
	}
	
	/**
	 * Helper for determining if a workbook is in modern or legacy Excel format.
	 * @throws IOException If the file cannot be parsed.
	 */
	private void openWorkbook() throws IOException {
		
		boolean success = true;
		
		try {
			String name = file.getName();
			int length = name.length();
			
			if (length > 5 && name.substring(length-5, length).equals(".xlsx")) {
				this.workbook = new XSSFWorkbook(file);
			}
			else if (length > 4 && name.substring(length-5, length).equals(".xls")) {
				this.workbook = new HSSFWorkbook(new FileInputStream(file));
			}
			else {
				success = false;
			}
		}
		catch (InvalidFormatException | IOException e) {
			success = false;
		}
		
		if (!success) {
			throw new IOException("File could not be opened, please ensure it is in .xlsx or .xls format");
		}
	}

	/**
	 * Helper for parsing the Grade Schema and Rank Schema.
	 */
	private void parseSchemas() {
		this.gradeSchema = new GradeSchema(workbook.getSheet("Grade Schema"));
		this.rankSchema = new RankSchema(workbook.getSheet("Rank Schema"));
	}
	
	/**
	 * Helper for parsing the courses, equivalents and areas.
	 */
	private void parseCourses() {
		
		Sheet rs = workbook.getSheet("Rank Schema");
		Sheet ca = workbook.getSheet("Course Areas");
		Sheet ce = workbook.getSheet("Course Equivalents");
		
		List<String> names = new ArrayList<String>();
		
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
		
	}
	
	public File getFile() {
		return this.file;
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
		return "[" + file + "," + workbook + "," + gradeSchema 
			+ "," + rankSchema + "," + areas + "," + courses + "]";
	}
	
}
