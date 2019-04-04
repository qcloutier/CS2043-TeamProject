package team9.transcriptanalyzer;

import java.util.List;
import java.util.ArrayList;

/**
 * Defines an AreaDistribution for a specific area.
 * @author mholt1 Created on 3/16/19.
 * @author jsudz Updated on 4/1/19.
 * @author qcloutier Updated on 4/3/19.
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
		boolean missingAreas=false;
		CourseAreas areas = config.getCourseAreas();
		List<String>areaList=getFirstIndexOnly(areas.listAllAreas());
		ArrayList<List<Integer>> valuesList =new ArrayList<List<Integer>>();
		int listSize=areaList.size();
		initializeValuesList(valuesList,listSize);
		CourseEquivalents equivalencies = config.getCourseEquivalencies();
		
		for (Transcript transcript : cohort.getTranscripts()) {
			List<double[]> totals=new ArrayList<double[]>();
			initializeTotalsList(totals,listSize);
			int gradePtIndex=0, creditHourIndex=1;
			
			for (TranscriptCourse course : transcript.getCourses()) {
				String currentCourse=course.getID();
				String equivalent=equivalencies.getEquivalency(currentCourse);
				if(equivalent!=null)
					currentCourse=equivalencies.getEquivalency(course.getID());
				
				List<String> areasContainingCourse=areas.getAreas(currentCourse);
				if(areasContainingCourse!=null) {
					
					for(String area:areasContainingCourse) {
						int currentIndex=areaList.indexOf(area);		
						double[] currentTotals=totals.get(currentIndex);
						if(!Double.isNaN(course.getGrade().asPoint())){
							currentTotals[gradePtIndex]+=course.getGrade().asPoint()*course.getCreditHours();
							currentTotals[creditHourIndex]+=course.getCreditHours();
						}
					}
				}
				else
				{
					missingAreas=true;
				}
			}
			
			for(String area:areaList) {
				
				int index=areaList.indexOf(area);
				double[]currentTotals=totals.get(index);
				double avgGradePt=currentTotals[gradePtIndex]/currentTotals[creditHourIndex];
				int gradeIndex=getGradeAsIndex(avgGradePt);
				if(gradeIndex>=0) {
					ensureSize(valuesList,index+1);
					List<Integer>currentAreaValues=valuesList.get(index);						
					currentAreaValues.set(gradeIndex,currentAreaValues.get(gradeIndex)+1);
				}
			}
		}
		for(String area: areaList) {
			int index=areaList.indexOf(area);
			addEntry(area,valuesList.get(index));
		}
		if(missingAreas)
			Messenger.updateArea();
	}
	
	public String[][] listDistribution() {

		GradeSchema gradeSchema=super.getSchema();
		List<String> names=gradeSchema.listNames();
		int rowLength=names.size()+1;
		int colLength=entries.size()+1;
		String[][] output=new String[colLength][rowLength];
		
		for (int i=1; i<rowLength; i++) {
			output[0][i]=names.get(i-1);
		}
		
		for(AreaEntry entry:entries) {
			int index=entries.indexOf(entry)+1;
			output[index][0]=entry.area;
			int counter=1;
				for(int tally:entry.values) {
					output[index][counter++]=Integer.toString(tally);
				}
		}
		return output;
	}
	
	private void ensureSize(ArrayList<?> list, int size) {
	    list.ensureCapacity(size);
	    while (list.size() < size) {
	        list.add(null);
	    }
	}
	
	private List<String> getFirstIndexOnly(List<List<String>> list){
		List <String>firstIndexList=new ArrayList<String>();
		for(List<String> subList:list)
			firstIndexList.add(subList.get(0));
		return firstIndexList;	
	}
	
	private static List<Integer> emptyValuesRow() {
		List<Integer> row= new ArrayList<Integer>();
		int rowSize=4;
		for(int i=0;i<rowSize;i++)
			row.add(0);
		return row;
	}
	
	private double[] emptyTotalsRow() {
		return new double[] {0,0};
	}
	
	private void initializeTotalsList(List<double[]> totals,int size) {
		for(int i=0;i<size;i++) {
			totals.add(emptyTotalsRow());
		}
	}
	
	private void initializeValuesList(List<List<Integer>> values, int size) {
		for(int i=0;i<size;i++) {
			values.add(emptyValuesRow());
		}
	}
	
	private int getGradeAsIndex(double grade) {
		GradeSchema gradeSchema=super.getSchema();
		List<String> names=gradeSchema.listNames();
		for(String level:names)
			if(grade<= gradeSchema.getUpper(level).asPoint())
				return names.indexOf(level);
		return -1;
	}
	
	public String toString() {
		return "[" + entries + "]";
	}
	
	private class AreaEntry{
		
		public String area;
		
		public List<Integer> values;
		
		public AreaEntry(String area, List<Integer> values) {
			this.area = area;
			this.values = values;
		}
		
		public String toString() {
			return "[" + area + ", " + values + "]";
		}
		
	}
	
}