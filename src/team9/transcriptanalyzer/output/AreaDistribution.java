package team9.transcriptanalyzer.output;

import java.util.List;
import java.util.Map;

import team9.transcriptanalyzer.input.Cohort;
import team9.transcriptanalyzer.input.Configuration;
import team9.transcriptanalyzer.input.CourseAreas;
import team9.transcriptanalyzer.input.CourseEquivalents;
import team9.transcriptanalyzer.input.GradeSchema;
import team9.transcriptanalyzer.input.Transcript;
import team9.transcriptanalyzer.input.TranscriptCourse;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Defines an AreaDistribution for a specific area.
 * @author mholt1 Created on 3/16/19.
 * @author qcloutier Updated on 3/30/19.
 * @author jsudz Updated on 4/1/19.
 */
public class AreaDistribution extends Distribution {
	
	private List<AreaEntry> entries;
	
	public AreaDistribution(GradeSchema schema) {
		super(schema);
		entries = new ArrayList<AreaEntry>();
	}
	
	public void addEntry(String area, List<Integer> values) {
		entries.add(new AreaEntry(area, values));
	}
	
	public void calculate(Configuration config, Cohort cohort) {
		
		CourseAreas areas = config.getCourseAreas();
		ArrayList<String> areaList=new ArrayList<String>();
		ArrayList<ArrayList<Integer>> valuesList =new ArrayList<ArrayList<Integer>>();
		initializeValuesList(valuesList);
		CourseEquivalents equivalencies = config.getCourseEquivalencies();
		
		Map<String, List<Double>> results = new HashMap<String, List<Double>>();
		
		for (Transcript transcript : cohort.getTranscripts()) { //this should be extracted
			
			ArrayList<double[]> totals=new ArrayList<double[]>();
			int gradePtIndex=0, creditHourIndex=1;
			
			for (TranscriptCourse course : transcript.getCourses()) {
				
				
				
				String currentCourse=equivalencies.getEquivalency(course.getID());
				List<String> areasContainingCourse=areas.getAreas(currentCourse);
				
				for(String area:areasContainingCourse) {
					if(!areaList.contains(area))
						areaList.add(area);
					
					int currentIndex=areaList.indexOf(area);
					totals.ensureCapacity(currentIndex);
					if(totals.get(currentIndex)==null) {
						initializeTotalsRow(totals,currentIndex);
					}
								
					double[] currentTotals=totals.get(currentIndex);
					currentTotals[gradePtIndex]+=course.getGrade().asPoint()*course.getCreditHours();
					currentTotals[creditHourIndex]+=course.getCreditHours();
				}
			}
			
			for(String area:areaList) {//so should this
				int index=areaList.indexOf(area);
				double[]currentTotals=totals.get(index);
				double avgGradePt=currentTotals[gradePtIndex]/currentTotals[creditHourIndex];
				int gradeIndex=getGradeAsIndex(config,avgGradePt);
				if(gradeIndex>=0) {
					ArrayList<Integer>currentAreaValues=valuesList.get(index);
					currentAreaValues.set(gradeIndex,currentAreaValues.get(gradeIndex)+1);
				}
				
			}
		}
		for(String area: areaList) {
			int index=areaList.indexOf(area);
			addEntry(area,valuesList.get(index));
		}
		
	}
	
	private void initializeValuesList(ArrayList<ArrayList<Integer>> valuesList) {
		for(ArrayList<Integer> values:valuesList) {
			values=new ArrayList<Integer>();
			for(int val:values){
				val=0;
			}
		}
		
	}
	
	private void initializeTotalsRow(ArrayList<double[]> totals,int index) {
		double[] newTotals=new double[2];
		for (int i=0;i<newTotals.length;i++)
			newTotals[i]=0;
		totals.set(index,newTotals);
	}
	
	private int getGradeAsIndex(Configuration config, double grade) {
		GradeSchema gradeSchema=config.getGradeSchema();
		int exceedsIndex=3,meetsIndex=2,marginalIndex=1,failsIndex=0,otherIndex=-1;
		if(grade>= gradeSchema.getLower("exceeds").asPoint()) {
			return exceedsIndex;
		}
		if(grade >= gradeSchema.getLower("meets").asPoint()) {
			return meetsIndex;
		}
		if(grade >= gradeSchema.getLower("marginal").asPoint()) {
			return marginalIndex;
		}
		if(grade >= gradeSchema.getLower("fails").asPoint()) {
			return failsIndex;
		}
		else {
			return otherIndex;
		}
	}
	
	public String[][] listDistribution(){
		return null;
	}
	
	private class AreaEntry{
		
		public String area;
		public List<Integer> values;
		
		public AreaEntry(String area, List<Integer> values) {
			this.area = area;
			this.values = values;
		}
	}
	
}