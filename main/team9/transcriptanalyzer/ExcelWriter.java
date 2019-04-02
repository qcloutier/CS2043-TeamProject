package team9.transcriptanalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelWriter{
	
	public static void writeCourseEquivalents(CourseEquivalents courseEquivalents, Workbook workbook) {
		Sheet courseEquivsSheet = workbook.createSheet("Course Equivalents");
		List<List<String>> courseEquivalentsList = courseEquivalents.listAllEquivalencies();
		int maxNumRows = getMaxNumRows(courseEquivalentsList);
		for(int i = 0; i < maxNumRows; i++) {
			Row nextRow = courseEquivsSheet.createRow(i);
			int j = 0;
			for(List<String> equivs : courseEquivalentsList) {
				if(equivs.size() > i) {
					Cell c = nextRow.createCell(j);
					c.setCellValue(equivs.get(i));
				}
				j++;
			}
		}
	}
	
	public static void writeCourseAreas(CourseAreas courseAreas, Workbook workbook) {
		Sheet courseAreasSheet = workbook.createSheet("Course Areas");
		List<List<String>> courseAreasList = courseAreas.listAllAreas();
		int maxNumRows = getMaxNumRows(courseAreasList);
		for(int i = 0; i < maxNumRows; i++) {
			Row nextRow = courseAreasSheet.createRow(i);
			int j = 0;
			for(List<String> area : courseAreasList) {
				if(area.size() > i) {
					Cell c = nextRow.createCell(j);
					c.setCellValue(area.get(i));
				}
				j++;
			}
		}
	}
	
	private static int getMaxNumRows(List<List<String>> columns) {
		int max = 0;
		for(int i = 0; i < columns.size(); i++) {
			if (columns.get(i).size() > max) {
				max = columns.get(i).size();
			}
		}
		return max;
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
	
	public static void writeDistribution(Distribution distribution, Workbook workbook) {
		Sheet rawDistributionSheet = workbook.createSheet("Raw Distribution");
		String[][] distributionStrings = distribution.listDistribution();
		for(int i = 0; i < distributionStrings.length; i++) {
			Row nextRow = rawDistributionSheet.createRow(i);
			for(int j = 0; j < distributionStrings[i].length; j++) {
				Cell c = nextRow.createCell(j);
				c.setCellValue(distributionStrings[i][j]);
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