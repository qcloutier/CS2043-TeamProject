package team9.transcriptanalyzer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/*
 * This file was used to test how to write excel files.
 */
public class ExcelOutput{

	public static void main(String[] args) throws IOException{
		
		Workbook w = new XSSFWorkbook();//creates a workbook
		Sheet s = w.createSheet("Test");//creates a sheet in the workbook
		
		CellStyle white = w.createCellStyle();//creates styles for black and white cells
		CellStyle black = w.createCellStyle();
		white.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		black.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		white.setFillForegroundColor(IndexedColors.WHITE.getIndex());//sets the colors for the two styles
		black.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		
		Font f = w.createFont();//creates a font
		f.setColor(IndexedColors.RED.getIndex());//sets font color to red
		white.setFont(f);//sets the black and white styles to use the font
		black.setFont(f);
		
		for(int i = 0; i < 45; i++) {
			Row r = s.createRow(i);//creates a row in the sheet
			for(int j = 0; j < 29; j++) {
				Cell c = r.createCell(j);//creates a cell in the row
				int x = (int)(Math.random()*2);
				
				if(x == 0) {
					c.setCellStyle(black);//sets the cell style to black
				}
				else {
					c.setCellStyle(white);//sets the cell style to white
				}
				
				c.setCellValue(x);// sets the cell value
			}
		}
		
		FileOutputStream out = new FileOutputStream("demo/TestOutput.xlsx");
		w.write(out);
		
		out.close();//FileOutputStream is closed before the workbook is closed.
		w.close();
	}
	
}