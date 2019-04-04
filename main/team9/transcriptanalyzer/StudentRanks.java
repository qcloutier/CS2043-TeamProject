package team9.transcriptanalyzer;

import java.util.List;
import java.util.ArrayList;

/**
 * Defines counts of students in all ranks in a given ranking schema.
 * @author mholt1 Created on 4/3/19.
 * @author jsudz Created on 4/3/19.
 * @author qcloutier Updated on 4/3/19.
 */
public class StudentRanks {
	
	private RankSchema rankSchema;
	
	private List<String> levelNames;
	
	private List<Integer> levelTally;
	
	public StudentRanks(RankSchema rankSchema) {
		this.rankSchema = rankSchema;
		this.levelNames = rankSchema.listNames();
		this.levelTally = new ArrayList<Integer>();
		for(int i = 0; i < levelNames.size(); i++) {
			levelTally.add(0);
		}
	}
	
	public void calculate(Cohort cohort) {
		for(Transcript transcript : cohort.getTranscripts()) {
			Double creditHours = 0.0;
			ArrayList<String> completedCourses = new ArrayList<String>();
			for(TranscriptCourse course : transcript.getCourses()) {
				creditHours += course.getCreditHours();
				completedCourses.add(course.getID());
			}
			for(int i = levelNames.size()-1; i >= 0; i--) {
				String levelName = levelNames.get(i);
				if(rankSchema.getMinCreditHours(levelName) < creditHours) {
					List<String> requiredCourses = rankSchema.getRequiredCourses(levelName);
					boolean hasCompletedCourses = true;
					for(String courseName : requiredCourses) {
						hasCompletedCourses &= completedCourses.contains(courseName);
					}
					if(hasCompletedCourses) {
						levelTally.set(i, levelTally.get(i) + 1);
						break;
					}
				}
			}
		}
	}
	
	public String[][] listRankTally() {
		String[][] rankTallies = new String[2][levelNames.size()];
		for(int i = 0; i < rankTallies[0].length; i++) {
			rankTallies[0][i] = levelNames.get(i);
			rankTallies[1][i] = levelTally.get(i).toString();
		}
		return rankTallies;
	}
	
	public String toString() {
		return "[" + rankSchema + ", " + levelNames + ", " + levelTally + "]";
	}
	
}