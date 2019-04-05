package team9.transcriptanalyzer;

import java.util.List;
import java.util.ArrayList;

/**
 * Defines an AreaDistribution for a specific area.
 * @author mholt1 Created on 3/16/19.
 * @author jsudz Updated on 4/5/19.
 * @author qcloutier Updated on 4/5/19.
 */
public class AreaDistribution extends Distribution {
	
	private List<AreaEntry> entries;
	
	/**
	 * Creates an AreaDistribution and initializes its lists.
	 * @param schema The grade schema to use.
	 */
	public AreaDistribution(GradeSchema schema) {
		super(schema);
		entries = new ArrayList<AreaEntry>();
	}
	
	private void addEntry(String area, List<Integer> values) {
		entries.add(new AreaEntry(area, values));
	}
	
	/**
	 * Calculates the area distribution given a configuration and cohort.
	 * @param config The configuration to use for areas and equivalencies.
	 * @param cohort The cohort with transcripts to process.
	 */
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
						if (currentIndex >= 0) {
							double[] currentTotals=totals.get(currentIndex);
							if(!Double.isNaN(course.getGrade().asPoint())){
								currentTotals[gradePtIndex]+=course.getGrade().asPoint()*course.getCreditHours();
								currentTotals[creditHourIndex]+=course.getCreditHours();
							}
						}
					}
				}
				else {
					missingAreas=true;
					areas.addArea(currentCourse, "UNSORTED");
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
	
	/**
	 * Creates a 2D array of the distributions 
	 * for easy writing to a file.
	 * @return The distributions in a 2D array.
	 */
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
	
	private List<Integer> emptyValuesRow() {
		List<Integer> row= new ArrayList<Integer>();
		int rowSize=getSchema().listNames().size();
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
		for(int i=names.size()-1; i>=0; i--)
			if(grade >= gradeSchema.getLower(names.get(i)).asPoint())
				return names.indexOf(names.get(i));
		return -1;
	}
	
	public String toString() {
		return "[" + entries + "]";
	}
	
	/**
	 * Defines an individual entry in the area distribution list.
	 */
	private class AreaEntry{
		
		public String area;
		
		public List<Integer> values;
		
		/**
		 * Creates an entry in the area distributions.
		 * @param area The name of the area.
		 * @param values The counts for the area.
		 */
		public AreaEntry(String area, List<Integer> values) {
			this.area = area;
			this.values = values;
		}
		
		public String toString() {
			return "[" + area + ", " + values + "]";
		}
		
	}
	
}