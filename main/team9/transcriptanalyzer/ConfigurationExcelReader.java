package team9.transcriptanalyzer;

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
 * Implementation of ConfigurationReader for Excel files.
 * @author qcloutier Created on 3/30/19, updated on 4/2/19.
 */
public class ConfigurationExcelReader implements ConfigurationReader {
	
	private File file;
	
	public ConfigurationExcelReader(File file) {
		this.file = file;
	}
	
	public Configuration read() throws IOException {
		
		try (Workbook inputExcel = WorkbookFactory.create(file)) {
			
			GradeSchema gs = parseGradeSchema(inputExcel.getSheet("Grade Schema"));
			RankSchema rs = parseRankSchema(inputExcel.getSheet("Rank Schema"));
			CourseAreas ca = parseAreas(inputExcel.getSheet("Course Areas"));
			CourseEquivalents cs = parseEquivalents(inputExcel.getSheet("Course Equivalents"));
		
			return new Configuration(gs, rs, ca, cs);
		}
	}
	
	private CourseAreas parseAreas(Sheet courseAreas) {
		
		CourseAreas results = new CourseAreas();
		for (int c=0; c<courseAreas.getRow(0).getLastCellNum(); c++) {
			
			String parent = courseAreas.getRow(0).getCell(c).getStringCellValue();
			for (int r=1; r<courseAreas.getLastRowNum(); r++) {
				
				Cell cell = courseAreas.getRow(r).getCell(c);
				if (cell != null) {
					
					String child = cell.getStringCellValue();
					results.addArea(child, parent);
				}
			}
		}
		
		return results;
	}
	
	private CourseEquivalents parseEquivalents(Sheet courseEqs) {
		
		CourseEquivalents results = new CourseEquivalents();
		for (int c=0; c<courseEqs.getRow(0).getLastCellNum(); c++) {
			
			String parent = courseEqs.getRow(0).getCell(c).getStringCellValue();
			for (int r=1; r<courseEqs.getLastRowNum(); r++) {
				
				Cell cell = courseEqs.getRow(r).getCell(c);
				if (cell != null) {
					
					String child = cell.getStringCellValue();
					results.addEquivalency(child, parent);
				}
			}
		}
		
		return results;
	}
	
	private GradeSchema parseGradeSchema(Sheet gradeSchema) {

		GradeSchema result = new GradeSchema();
		
		Row names = gradeSchema.getRow(0);
		Row lower = gradeSchema.getRow(1);
		Row upper = gradeSchema.getRow(2);
		
		for (int i=0; i<names.getLastCellNum(); i++) {
			result.addLevel(
				names.getCell(i).getStringCellValue(), 
				Grade.match(lower.getCell(i).getStringCellValue()), 
				Grade.match(upper.getCell(i).getStringCellValue())
			);
		}
		
		return result;
	}
	
	private RankSchema parseRankSchema(Sheet rankSchema) {
		
		RankSchema result = new RankSchema();
		for (int c=0; c<rankSchema.getRow(0).getLastCellNum(); c++) {
			
			String name = rankSchema.getRow(0).getCell(c).getStringCellValue();
			int hours = (int)rankSchema.getRow(1).getCell(c).getNumericCellValue();
			
			List<String> courses = new ArrayList<String>();
			for (int r=2; r<=rankSchema.getLastRowNum(); r++) {
				
				Cell cell = rankSchema.getRow(r).getCell(c);
				if (cell != null) {
					courses.add(cell.getStringCellValue());
				}
			}
			
			result.addLevel(name, hours, courses);
		}
		
		return result;
	}
	
}
