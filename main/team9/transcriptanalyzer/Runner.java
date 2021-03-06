package team9.transcriptanalyzer;

import java.io.File;

/**
 * The starting point for execution of the system.
 * @author qcloutier Created on 3/16/19, updated on 4/3/19.
 */
public class Runner {

	public static void main(String[] args) {
		try {
			System.setErr(System.out);
			
			// Check that three file paths have been passed
			if (args.length != 3) {
				Messenger.usage();
				return;
			}
			
			File configFile = new File(args[0]);
			File cohortFolder = new File(args[1]);
			File resultsFile = new File(args[2]);
			
			// Read and parse configuration file
			Configuration configObj = ConfigurationFactory.determine(configFile).read();
			
			// Read and parse transcript cohort
			Cohort cohortObj = CohortFactory.determine(cohortFolder).read();
			
			// Calculate results
			RawDistribution rawDist = new RawDistribution(configObj.getGradeSchema());
			rawDist.calculate(configObj, cohortObj);
			AreaDistribution areaDist = new AreaDistribution(configObj.getGradeSchema());
			areaDist.calculate(configObj, cohortObj);
			StudentRanks studentRanks = new StudentRanks(configObj.getRankSchema());
			studentRanks.calculate(cohortObj);
			
			// Write to output file
			Results resultsObj = new Results(configObj, rawDist, areaDist, studentRanks);
			ResultsFactory.determine(resultsFile).write(resultsObj);

			Messenger.success();
		}
		catch (Exception e) {
			Messenger.exception(e);
		}
	}
	
}
