package team9.spikes;

/*Great! In order to make sure that you fully understand what we're going with Excel files and Apache POI, 

I'd like you to make a method that takes in the Grade Schema, Course Equivalents, and Course Areas sheets 
from the Excel configuration file and creates a List of ConfigCourse objects. 

Have a look at the files in the "spikes" package in the eclipse project and use google to figure out 
how reading excel files works. 

Please switch to my branch (feature/configuration) to do this, as it has the ConfigCourse class. 

Write it as a static method in the ConfigCourse class and also write a main method in that same class to test it. 

Use the sample input excel file located in the "demo" folder in the project as test data.

I know it seems like a lot to take in, and I don't expect you to get it fully working. However, it is crucial 
that you have a basic understanding of what is going on with this part of the project. 

ConfigIdeally, I'll take the code you write and refactor it to finish up the Configuration class.

https://www.callicoder.com/java-read-excel-file-apache-poi */

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ExcelToObject {

	final static String FILENAME = "demo/TestInput.xlsx";
         
	public static void main(String[] args) throws IOException, InvalidFormatException {
		
		try {
            //Create Workbook instance holding reference to .xls or.xlsx file
			//Note that we’re not even using the concrete classes like HSSFWorkbook and XSSFWorkbook 
			//to create an instance of the Workbook. We’re creating the workbook using 
			//a WorkbookFactory instead. This makes our program format independent 
			//and it works for both types of files - .xls and .xlsx.
             Workbook workbook = WorkbookFactory.create(new File(FILENAME));

             
			//Iterate through sheets in file
			Iterator<Sheet> sheetIterator = workbook.sheetIterator();
				while (sheetIterator.hasNext()) {
					Sheet sheet = sheetIterator.next();
					System.out.println("=> " + sheet.getSheetName());
			}
			
			//Get first sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);
			
			// Create a DataFormatter to format and get each cell's value as String
			DataFormatter dataFormatter = new DataFormatter();
			 
			//Get a rowIterator and columnIterator and iterate over them
			
			//Iterating over rows, ignoring header row , sheet.rowIterator set to "0"
			Iterator<Row> rowIterator = sheet.rowIterator();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();

				//Iterating over the columns of the current row
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String cellValue = dataFormatter.formatCellValue(cell);
					System.out.print(cellValue + "\t");
				}
				
				/*Or you can use Java 8 forEach loop with lambda
				sheet.forEach(row -> {
					row.forEach(cell -> {
						String cellValue = dataFormatter.formatCellValue(cell);
						System.out.print(cellValue + "\t");
				});
            
				System.out.println();
				});*/
				System.out.println();
			}
			
              workbook.close();
          } 
          catch (Exception e) 
          {
              e.printStackTrace();
          }
      }
}