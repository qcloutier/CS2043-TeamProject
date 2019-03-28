package team9.transcriptanalyzer.input;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Defines the ranking schema from the configuration file.
 * @author qcloutier Created on 3/16/19, last updated on 3/27/19.
 */
public class RankSchema extends Schema {
	
	private List<RankLevel> levels;
	
	/**
	 * Parses an Excel sheet into a rank schema.
	 * @param rankSchema The sheet containing the rank schema.
	 */
	public RankSchema(Sheet rankSchema) {
		super();
		levels = new ArrayList<RankLevel>();
		
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
			
			addLevel(name, hours, courses);
		}
	}

	private void addLevel(String name, int minCreditHours, List<String> requiredCourses) {
		if (!listNames().contains(name)) {
			addName(name);
			levels.add(new RankLevel(minCreditHours, requiredCourses));
		}
	}
	
	/**
	 * Retrieves the minimum number of credit hours required for a rank.
	 * @param name The name of the level.
	 * @return The minimum number of credit hours required for a rank.
	 */
	public int getMinCreditHours(String name) {
		return levels.get(listNames().indexOf(name)).minCreditHours;
	}
	
	/**
	 * Retrieves the names of the required courses for a rank.
	 * @param name The name of the level.
	 * @return The names of the required courses for a rank.
	 */
	public List<String> getRequiredCourses(String name) {
		return levels.get(listNames().indexOf(name)).requiredCourses;
	}
	
	public String toString() {
		return "[" + listNames() + ", " + levels + "]";
	}
	
	/**
	 * Defines an individual level in the rank schema.
	 */
	private class RankLevel {
		
		public int minCreditHours;
		
		public List<String> requiredCourses;
		
		/**
		 * Creates a level in the rank schema.
		 * @param minCreditHours The minimum required credit hours.
		 * @param requiredCourses The required courses.
		 */
		public RankLevel(int minCreditHours, List<String> requiredCourses) {
			this.minCreditHours = minCreditHours;
			this.requiredCourses = requiredCourses;
		}
		
		public String toString() {
			return "[" + minCreditHours + ", " + requiredCourses + "]";
		}
		
	}
	
	// Temporary, will be removed once we formally start writing JUnit tests.
	public static void main(String[]args) throws Exception {
		Workbook wb = WorkbookFactory.create(new File("demo/IO Spec Input.xlsx"));
		RankSchema gs = new RankSchema(wb.getSheet("Rank Schema"));
		System.out.println(gs);
	}
	
}
