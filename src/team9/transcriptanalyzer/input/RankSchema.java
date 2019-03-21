package team9.transcriptanalyzer.input;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Defines the ranking schema from the configuration file.
 * @author qcloutier Created on 3/16/19.
 */
public class RankSchema extends Schema {

	public static void main(String[]args) throws Exception {
		Workbook wb = new XSSFWorkbook(new File("demo/IO Spec Input.xlsx"));
		RankSchema gs = new RankSchema(wb.getSheet("Rank Schema"));
		System.out.println(gs);
	}
	
	private List<RankLevel> levels;
	
	public RankSchema(Sheet rankSchema) {
		super();
		levels = new ArrayList<RankLevel>();

		Row names = rankSchema.getRow(0);
		Row hours = rankSchema.getRow(1);
		
		List<Row> courses = new ArrayList<Row>();
		for (int i=2; i<rankSchema.getLastRowNum(); i++) {
			courses.add(rankSchema.getRow(i));
		}
		
		for (int i=0; i<names.getLastCellNum(); i++) {
			addLevel(
				names.getCell(i).getStringCellValue(), 
				(int)hours.getCell(i).getNumericCellValue(), 
				null
			);
		}
	}
	
	public void addLevel(String name, int minCreditHours, List<ConfigCourse> requiredCourses) {
		if (!listNames().contains(name)) {
			addName(name);
			levels.add(new RankLevel(minCreditHours, requiredCourses));
		}
	}
	
	public int getMinCreditHours(String name) {
		return levels.get(listNames().indexOf(name)).minCreditHours;
	}
	
	public List<ConfigCourse> getRequiredCourses(String name) {
		return levels.get(listNames().indexOf(name)).requiredCourses;
	}
	
	public String toString() {
		return "[" + listNames() + ", " + levels + "]";
	}
	
	private class RankLevel {
		
		public int minCreditHours;
		public List<ConfigCourse> requiredCourses;
		
		public RankLevel(int minCreditHours, List<ConfigCourse> requiredCourses) {
			this.minCreditHours = minCreditHours;
			this.requiredCourses = requiredCourses;
		}
		
		public String toString() {
			return "[" + minCreditHours + ", " + requiredCourses + "]";
		}
		
	}
	
}
