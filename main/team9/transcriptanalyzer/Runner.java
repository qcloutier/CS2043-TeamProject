package team9.transcriptanalyzer;

import java.io.File;
import java.io.IOException;

/**
 * The starting point for execution of the system.
 * @author qcloutier Created on 3/16/19, updated on 4/1/19.
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
			Configuration config = ExcelReader.parse(new File(args[0]));
			
			// Read and parse transcript cohort
			Cohort cohort = new Cohort(args[1]);
			
			// Calculate results and write to output file
			RawDistribution rawDist = new RawDistribution(config.getGradeSchema());
			rawDist.calculate(config, cohort);
			AreaDistribution areaDist = new AreaDistribution(config.getGradeSchema());
			//areaDist.calculate(config, cohort);
			Results results = new Results(new File(args[2]), config, rawDist, areaDist);
			results.write();
			
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
