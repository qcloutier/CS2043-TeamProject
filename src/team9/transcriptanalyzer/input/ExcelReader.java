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
 * Temporary class with methods for parsing the Excel configuration file.
 * Will hopefully be refactored into something better.
 * @author qcloutier Created on 3/30/19.
 */
public class ExcelReader {
	
	public static Configuration parse(File file) throws IOException {
		
		try (Workbook inputExcel = WorkbookFactory.create(file)) {
			
			GradeSchema gs = parseGradeSchema(inputExcel.getSheet("Grade Schema"));
			RankSchema rs = parseRankSchema(inputExcel.getSheet("Rank Schema"));
			CourseAreas ca = parseAreas(inputExcel.getSheet("Course Areas"));
			CourseEquivalents cs = parseEquivalents(inputExcel.getSheet("Course Equivalents"));
		
			return new Configuration(gs, rs, ca, cs);
		}
	}
	
	public static CourseAreas parseAreas(Sheet courseAreas) {
		
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
	
	public static CourseEquivalents parseEquivalents(Sheet courseEqs) {
		
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
	
	public static GradeSchema parseGradeSchema(Sheet gradeSchema) {

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
	
	public static RankSchema parseRankSchema(Sheet rankSchema) {
		
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
	
	public static void main(String[]args) throws Exception {
		
		Workbook wb = WorkbookFactory.create(new File("demo/IO Spec Input.xlsx"));
		
		System.out.println(parseAreas(wb.getSheet("Course Areas")));
		System.out.println(parseEquivalents(wb.getSheet("Course Equivalents")));
		System.out.println(parseGradeSchema(wb.getSheet("Grade Schema")));
		System.out.println(parseRankSchema(wb.getSheet("Rank Schema")));
		
		System.out.println(parse(new File("demo/IO Spec Input.xlsx")));
	}
	
}
