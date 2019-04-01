package team9.transcriptanalyzer.output;

import team9.transcriptanalyzer.input.Configuration;
import java.io.File;

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
	
	public Results(File outputExcelFile, Configuration config, RawDistribution rawDist, AreaDistribution areaDist) {
		this.rawDistribution  = rawDist;
		this.areaDistribution = areaDist;
		this.config = config;
		this.outputExcelFile = outputExcelFile;
	}
	
	public void write() throws FileNotFoundException, IOException{
		Workbook workbook = new XSSFWorkbook();
		ExcelWriter.writeCourseAreas(this.config.getCourseAreas(), workbook);
		ExcelWriter.writeCourseEquivalents(this.config.getCourseEquivalencies(), workbook);
		ExcelWriter.writeGradeSchema(this.config.getGradeSchema(), workbook);
		ExcelWriter.writeRankSchema(this.config.getRankSchema(), workbook);
		
		ExcelWriter.writeDistribution(this.rawDistribution, workbook);
		//ExcelWriter.writeDistribution(this.areaDistribution, workbook);
		
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