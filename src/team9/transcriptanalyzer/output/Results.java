package team9.transcriptanalyzer.output;

import team9.transcriptanalyzer.input.*;
import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * A class that writes the results to an excel file
 * @author mholt1 created on 3/18/2019
 */

import java.io.FileOutputStream;
import java.io.IOException;

public class Results{
	
	private File file;
	private Workbook workbook;
	private Sheet courseEqs;
	private Sheet gradeSch;
	private Sheet rankSch;
	private Sheet rawDist;
	private Sheet areaDist;
	private Configuration config;
	
	public Results(String fileName) {
		this.file = new File(fileName);
		this.workbook = new XSSFWorkbook();
	}
	
	public File getFile() {
		return this.file;
	}
	
	public void setConfig(Configuration config) {
		this.config = config;
	}
	
	public void setRawDist(RawDistribution rawDist) {
		//TODO
	}
	
	public void setAreaDist(AreaDistribution areaDist) {
		//TODO
	}
	
	public void write() {
		//TODO
	}
	
	public String toString() {
		return null;
	}
	
}