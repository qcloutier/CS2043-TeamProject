package team9.transcriptanalyzer.input;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Defines the grading schema from the configuration file.
 * @author qcloutier Created on 3/16/19, last updated on 3/27/19.
 */
public class GradeSchema extends Schema {
	
	private List<GradeLevel> levels;
	
	/**
	 * Parses an Excel sheet into a grade schema.
	 * @param gradeSchema The sheet containing the grade schema.
	 */
	public GradeSchema(Sheet gradeSchema) {
		super();
		levels = new ArrayList<GradeLevel>();
		
		Row names = gradeSchema.getRow(0);
		Row lower = gradeSchema.getRow(1);
		Row upper = gradeSchema.getRow(2);
		
		for (int i=0; i<names.getLastCellNum(); i++) {
			addLevel(
				names.getCell(i).getStringCellValue(), 
				Grade.match(lower.getCell(i).getStringCellValue()), 
				Grade.match(upper.getCell(i).getStringCellValue())
			);
		}
	}
	
	private void addLevel(String name, Grade lower, Grade upper) {
		if (!listNames().contains(name)) {
			addName(name);
			levels.add(new GradeLevel(lower, upper));
		}
	}
	
	/**
	 * Retrieves the lower bound for a grade level.
	 * @param name The name of the level.
	 * @return The lower bound for a grade level.
	 */
	public Grade getLower(String name) {
		return levels.get(listNames().indexOf(name)).lower;
	}
	
	/**
	 * Retrieves the upper bound for a grade level.
	 * @param name The name of the level.
	 * @return The upper bound for a grade level.
	 */
	public Grade getUpper(String name) {
		return levels.get(listNames().indexOf(name)).upper;
	}
	
	public String toString() {
		return "[" + listNames() + ", " + levels + "]";
	}
	
	/**
	 * Defines an individual level in the grade schema.
	 */
	private class GradeLevel {
		
		public Grade lower;
		
		public Grade upper;
		
		/**
		 * Creates a level in the grade schema.
		 * @param lower The lower grade bound.
		 * @param upper The upper grade bound.
		 */
		public GradeLevel(Grade lower, Grade upper) {
			this.lower = lower;
			this.upper = upper;
		}
		
		public String toString() {
			return "[" + lower + ", " + upper + "]";
		}
		
	}
	
	// Temporary, will be removed once we formally start writing JUnit tests.
	public static void main(String[]args) throws Exception {
		Workbook wb = WorkbookFactory.create(new File("demo/IO Spec Input.xlsx"));
		GradeSchema rs = new GradeSchema(wb.getSheet("Grade Schema"));
		System.out.println(rs);
	}
	
}
