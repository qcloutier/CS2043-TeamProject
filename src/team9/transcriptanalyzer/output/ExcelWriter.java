package team9.transcriptanalyzer.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import team9.transcriptanalyzer.input.Configuration;
import team9.transcriptanalyzer.input.CourseAreas;
import team9.transcriptanalyzer.input.CourseEquivalents;
import team9.transcriptanalyzer.input.GradeSchema;
import team9.transcriptanalyzer.input.RankSchema;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter{
	
	public static void writeCourseEquivalents(CourseEquivalents courseEquivalents, Workbook workbook) {
		Sheet courseEquivsSheet = workbook.createSheet("Course Equivalents");
		List<List<String>> equivalents = new ArrayList<List<String>>();
		Row firstRow = courseEquivsSheet.createRow(0);
		int numCourses = 0, maxColSize = 0;
		List<String> courses //
		for(String course : courses) {
			Cell cell = firstRow.createCell(numCourses);
			cell.setCellValue(course);
			List<String> eqs = //Get all equivalents for the course
			if(eqs.size() > maxColSize) {
				maxColSize = eqs.size();
			}
			equivalents.add(eqs);
			numCourses++;
		}
		
		int rowWidth = 0;
		for(int i = 0; i < maxColSize; i++) {
			Row nextRow = courseEquivsSheet.createRow(i+1);
			rowWidth = 0;
			for(List<String> equivalent : equivalents) {
				if(i < equivalent.size()) {
					Cell cell = nextRow.createCell(rowWidth);
					cell.setCellValue(equivalent.get(i));
				}
			}
		}
	}
	
	public static void writeCourseAreas(CourseAreas courseAreas, Workbook workbook) {
		Sheet courseAreasSheet = workbook.createSheet("Course Areas");
		List<List<String>> courses = new ArrayList<List<String>>();
		Row firstRow = courseAreasSheet.createRow(0);
		int numArea = 0, maxColSize = 0;
		List<String> areas; //Get all areas
		for(String area : areas) {
			Cell cell = firstRow.createCell(numArea);
			cell.setCellValue(area);
			List<String> areaCourses; //Get all courses in the area
			if(areaCourses.size() > maxColSize) {
				maxColSize = areaCourses.size();
			}
			courses.add(areaCourses);
			numArea++;
		}
		
		int rowWidth = 0;
		for(int i = 0; i < maxColSize; i++) {
			Row nextRow = courseAreasSheet.createRow(i+1);
			rowWidth = 0;
			for(List<String> areaCourses : courses) {
				if(i < areaCourses.size()) {
					Cell cell = nextRow.createCell(rowWidth);
					cell.setCellValue(areaCourses.get(i));
				}
			}
		}
	}
	
	public static void writeGradeSchema(GradeSchema gradeSchema, Workbook workbook) {
		Sheet gradeSchemaSheet = workbook.createSheet("Grade Schema");
		List<String> levelNames = gradeSchema.listNames();
		Row names = gradeSchemaSheet.createRow(0);
		Row lowerBound = gradeSchemaSheet.createRow(1);
		Row upperBound = gradeSchemaSheet.createRow(2);
		for(int i = 0; i < levelNames.size(); i++) {
			Cell name = names.createCell(i);
			name.setCellValue(levelNames.get(i));
			Cell lower = lowerBound.createCell(i);
			lower.setCellValue(gradeSchema.getLower(levelNames.get(i)).toString());
			Cell upper = upperBound.createCell(i);
			upper.setCellValue(gradeSchema.getUpper(levelNames.get(i)).toString());
			
		}
	}
	
	public static void writeRankSchema(RankSchema rankSchema, Workbook workbook) {
		Sheet rankSchemaSheet = workbook.createSheet("Rank Schema");
		List<String> levelNames = rankSchema.listNames();
		Row namesRow = rankSchemaSheet.createRow(0);
		Row creditHoursRow = rankSchemaSheet.createRow(1);
		int maxNumRequiredCourses = 0;
		for(int i = 0; i < levelNames.size(); i++) {
			Cell name = namesRow.createCell(i);
			name.setCellValue(levelNames.get(i));
			Cell creditHour = creditHoursRow.createCell(i);
			creditHour.setCellValue(rankSchema.getMinCreditHours(levelNames.get(i)));
			if(maxNumRequiredCourses < rankSchema.getRequiredCourses(levelNames.get(i)).size()){
				maxNumRequiredCourses = rankSchema.getRequiredCourses(levelNames.get(i)).size();
			}
		}
	}
	
	public static void writeToFile(Workbook workbook, File outputFile) throws FileNotFoundException, IOException {
		FileOutputStream out = new FileOutputStream(outputFile);
		workbook.write(out);
		
		out.close();//FileOutputStream is closed before the workbook is closed.
		workbook.close();
	}
}