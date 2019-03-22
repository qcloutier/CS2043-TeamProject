package team9.transcriptanalyzer.output;

import team9.transcriptanalyzer.input.*;
import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that writes the results to an excel file
 * @author mholt1 created on 3/18/2019
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Results{
	
	private File file;
	
	private Workbook workbook;
	private RawDistribution rawDistribution;
	private AreaDistribution areaDistribution;
	private Sheet courseAreas;
	private Sheet courseEqs;
	private Sheet gradeSch;
	private Sheet rankSch;
	private Sheet rawDist;
	private Sheet areaDist;
	private Configuration config;
	
	public Results(String fileName) {
		this.file = new File(fileName);
		this.workbook = new XSSFWorkbook();
		this.rawDistribution  = null;
		this.areaDistribution = null;
		this.rawDist = this.workbook.createSheet("Raw Distribution");
		this.courseAreas = this.workbook.createSheet("Course Area's");
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
	
	public void setRawDist(RawDistribution rawDist) {
		this.rawDistribution = rawDist;
	}
	
	public void setAreaDist(AreaDistribution areaDist) {
		this.areaDistribution = areaDist;
	}
	
	public void write() throws FileNotFoundException, IOException{
		this.writeCourseEqs();
		this.writeGradeSch();
		this.writeCourseAreas();
		this.writeRankSch();
		
		this.writeAreaDistribution();
		this.writeRawDistribution();
		
		FileOutputStream out = new FileOutputStream(this.file);
		this.workbook.write(out);
		
		out.close();
		this.workbook.close();
	}
	
	private void writeAreaDistribution() {
		//TODO
	}
	
	private void writeRawDistribution() {
		//TODO
	}
	
	private void writeCourseEqs() {
		//TODO
	}
	
	private void writeGradeSch() {
		//TODO
	}
	
	private void writeCourseAreas() {
		/*List<Area> areas = this.config.getAreas();
		int areaCount = 0;
		Row areaName = this.courseAreas.createRow(0);
		for(Area area : areas) {
			Cell c = areaName.createCell(areaCount);    // Work In Progress
			c.setCellValue(area.getName());
		}
		int numRows = 1;
		for(Area area : areas) {
			List<ConfigCourse> courses = area.getCourses();
		}*/
	}
	
	private void writeRankSch() {
		//TODO
	}
	
	public String toString() {
		return null;
	}
	
}