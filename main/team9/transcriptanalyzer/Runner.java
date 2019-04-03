package team9.transcriptanalyzer;

import java.io.File;
import java.io.IOException;

/**
 * The starting point for execution of the system.
 * @author qcloutier Created on 3/16/19, updated on 4/2/19.
 */
public class Runner {

	public static void main(String[] args) {
		try {
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
			Cohort cohort = new Cohort(cohortFolder.getAbsolutePath());
			
			// Calculate results
			RawDistribution rawDist = new RawDistribution(configObj.getGradeSchema());
			rawDist.calculate(configObj, cohort);
			AreaDistribution areaDist = new AreaDistribution(configObj.getGradeSchema());
			areaDist.calculate(configObj, cohort);
			StudentRanks studentRanks = new StudentRanks(configObj.getRankSchema());
			studentRanks.calculate(cohort);
			
			// Write to output file
			Results resultsObj = new Results(configObj, rawDist, areaDist, studentRanks);
			ResultsFactory.determine(resultsFile).write(resultsObj);

			Messenger.success();
		}
		catch (IOException e) {
			Messenger.formatError(e);
		}
		catch (Exception e) {
			Messenger.exception(e);
		}
	}
	
}
