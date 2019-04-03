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
			
			// Read and parse configuration file
			ConfigurationReader configReader = new ConfigurationExcelReader();
			Configuration config = configReader.read(new File(args[0]));
			
			// Read and parse transcript cohort
			Cohort cohort = new Cohort(args[1]);
			
			// Calculate results
			RawDistribution rawDist = new RawDistribution(config.getGradeSchema());
			rawDist.calculate(config, cohort);
			AreaDistribution areaDist = new AreaDistribution(config.getGradeSchema());
			areaDist.calculate(config, cohort);
			
			// Write to output file
			Results results = new Results(config, rawDist, areaDist);
			ResultsWriter resultsWriter = new ResultsExcelWriter();
			resultsWriter.write(new File(args[2]), results);

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
