package team9.transcriptanalyzer.output;

import team9.transcriptanalyzer.input.*;
import java.io.File;


import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * A class that writes the results to an excel file
 * @author mholt1 created on 3/18/2019
 */

import java.io.IOException;
import java.io.FileNotFoundException;

public class Results{
	
	private RawDistribution rawDistribution;
	private AreaDistribution areaDistribution;
	private Configuration config;
	private File outputExcelFile;
	private Workbook workbook;
	
	public Results(File outputExcelFile) {
		this.rawDistribution  = null;
		this.areaDistribution = null;
		this.config = null;
		this.outputExcelFile = outputExcelFile;
	}
	
	public void setConfig(Configuration config) {
		this.config = config;
	}
	
	public void setRawDist(RawDistribution rawDist) {
		this.rawDistribution = rawDist;
	}
	
	public void setAreaDist(AreaDistribution areaDist) {
		this.areaDistribution = areaDist;
	}
	
	public void write() throws FileNotFoundException, IOException{
		Workbook workbook = new XSSFWorkbook();
		ExcelWriter.writeCourseAreas(this.config.getCourseAreas(), workbook);
		ExcelWriter.writeCourseEquivalents(this.config.getCourseEquivalencies(), workbook);
		ExcelWriter.writeGradeSchema(this.config.getGradeSchema(), workbook);
		ExcelWriter.writeRankSchema(this.config.getRankSchema(), workbook);
		
		try {
			ExcelWriter.writeToFile(workbook, this.outputExcelFile);
		}
		catch(FileNotFoundException e) {}
		catch(IOException e) {}
	}
	
	public String toString() {
		return null;
	}
	
}