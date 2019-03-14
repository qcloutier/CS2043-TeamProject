package team9.spikes;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelOutput{

	public static void main(String[] args) throws IOException{
		Workbook w = new HSSFWorkbook();
		Sheet s = w.createSheet("Test");
		
		CellStyle white = w.createCellStyle();
		CellStyle black = w.createCellStyle();
		white.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		black.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		white.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		black.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		Font f = w.createFont();
		f.setColor(IndexedColors.RED.getIndex());
		white.setFont(f);
		black.setFont(f);
		
		for(int i = 0; i < 45; i++) {
			Row r = s.createRow(i);
			for(int j = 0; j < 29; j++) {
				Cell c = r.createCell(j);
				int x = (int)(Math.random()*2);
				
				if(x == 0) {
					c.setCellStyle(black);
				}
				else {
					c.setCellStyle(white);
				}
				
				c.setCellValue(x);
			}
		}
		
		FileOutputStream out = new FileOutputStream("testOutput.xls");
		w.write(out);
		
		out.close();
		w.close();
	}
}