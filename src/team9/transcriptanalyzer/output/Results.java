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
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

public class Results{
	
	private File file;
	
	private Workbook workbook;
	private List<RawDistribution> rawDistributions;
	private List<AreaDistribution> areaDistributions;
	private Sheet courseEqs;
	private Sheet gradeSch;
	private Sheet rankSch;
	private Sheet rawDist;
	private Sheet areaDist;
	private Configuration config;
	
	public Results(String fileName) {
		this.file = new File(fileName);
		this.workbook = new XSSFWorkbook();
		this.rawDistributions = new ArrayList<RawDistribution>();
		this.areaDistributions = new ArrayList<AreaDistribution>();
		this.rawDist = this.workbook.createSheet("Raw Distribution");
		this.areaDist = this.workbook.createSheet("Area Distribution");
		this.gradeSch = this.workbook.createSheet("Grade Schema");
		this.courseEqs = this.workbook.createSheet("Course Equivalents");
		this.rankSch = this.workbook.createSheet("Rank Schema");
	}
	
	public File getFile() {
		return this.file;
	}
	
	public void setConfig(Configuration config) {
		this.config = config;
	}
	
	public void addRawDist(RawDistribution rawDist) {
		this.rawDistributions.add(rawDist);
	}
	
	public void addAreaDist(AreaDistribution areaDist) {
		this.areaDistributions.add(areaDist);
	}
	
	public void write() throws FileNotFoundException, IOException{
		//TODO
		
		FileOutputStream out = new FileOutputStream(this.file);
		this.workbook.write(out);
		out.close();
		this.workbook.close();
	}
	
	public String toString() {
		return null;
	}
	
}