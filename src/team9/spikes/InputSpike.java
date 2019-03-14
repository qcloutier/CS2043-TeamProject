package team9.spikes;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
public class InputSpike{
/*
 * Testing that I can read input from the second sheet of an .xls workbook with titles, a number 
 * in its first row of data and different numbers of strings in the rows beneath, similar to our rank schema.
 */
    public static void main(String[] args) {
    	final String FILENAME="demo/testsheet.xls";
    	try {
    		FileInputStream excelFile = new FileInputStream(new File(FILENAME));
            Workbook workbook = new HSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(1);
            Iterator<Row> iterator = sheet.iterator();
            String[] titles=new String[3];
            String[][] courses=new String[3][4];
            double[] hours=new double[3];
            int counter, courseCounter;
            
            //getting titles
            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            counter=0;
            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();
                titles[counter]=currentCell.getStringCellValue();
                counter++;
            }
            
            //getting numbers
        	currentRow = iterator.next();
            cellIterator = currentRow.iterator();
            counter=0;
           
            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();
                hours[counter]=currentCell.getNumericCellValue();
                counter++;
            }
            
            courseCounter=0;
            while(iterator.hasNext()) {//getting courses
            	currentRow = iterator.next();
                cellIterator = currentRow.iterator();
                counter=0;
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    courses[currentCell.getColumnIndex()][courseCounter]=currentCell.getStringCellValue();
                    counter++;
                }
                courseCounter++;
            }
            System.out.print("Titles:\t");//Printing Results
            for(int i=0;i<titles.length;i++) {
            	System.out.print(titles[i]+"\t");
            }
            System.out.println();
            
            System.out.print("Hours:\t");
            for(int i=0;i<hours.length;i++) {
            	System.out.print(hours[i]+"\t");
            }
            System.out.println();
            
            System.out.println("Courses:\t");
            for (int j=0; j<courses[0].length;j++) {
	            System.out.print("\t");
            	for(int i=0;i<courses.length;i++) {
	            	System.out.print(courses[i][j]+"\t");
	            }
	            System.out.println();
            }  
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}