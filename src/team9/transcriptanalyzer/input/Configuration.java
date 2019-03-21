package team9.transcriptanalyzer.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
	
	/**
	 * 
	 * @param file
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public Configuration(File file) throws InvalidFormatException, IOException {
		
		this.file = file;
		this.workbook = determineType(file);
		this.gradeSchema = new GradeSchema(workbook.getSheet("Grade Schema"));
		this.rankSchema = new RankSchema(workbook.getSheet("Rank Schema"));
		this.areas = new ArrayList<Area>();
		this.courses = new ArrayList<ConfigCourse>();
	}
	
	/**
	 * Helper method for determining if a workbook is in modern or legacy Excel format.
	 * @param file The Excel file to be opened and parsed.
	 * @return A Workbook instantiated as either a XSSFWorkbook or HSSFWorkbook.
	 * @throws InvalidFormatException When the file has a bad extension.
	 * @throws IOException When any other IO error occurs.
	 */
	private Workbook determineType(File file) throws InvalidFormatException, IOException {
	
		Workbook result = null;
		
		String name = file.getName();
		int length = name.length();
		
		if (length > 5 && name.substring(length-5, length).equals(".xlsx")) {
			result = new XSSFWorkbook(file);
		}
		else if (length > 4 && name.substring(length-5, length).equals(".xls")) {
			result = new HSSFWorkbook(new FileInputStream(file));
		}
		else {
			throw new InvalidFormatException("File is not .xlsx or .xls");
		}
		
		return result;
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
