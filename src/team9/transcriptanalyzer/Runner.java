package team9.transcriptanalyzer;

import java.io.File;

import team9.transcriptanalyzer.input.Configuration;
import team9.transcriptanalyzer.input.ExcelReader;
import team9.transcriptanalyzer.input.Cohort;
import team9.transcriptanalyzer.output.RawDistribution;
import team9.transcriptanalyzer.output.AreaDistribution;
import team9.transcriptanalyzer.output.Results;

/**
 * The starting point for execution of the system.
 * @author qcloutier Created on 3/16/19.
 */
public class Runner {

	public static void main(String[] args) {
		
		try {
		
			// Check that three file paths have been passed
			if (args.length != 3) {
				//Messenger.usage();
				return;
			}
			
			// Read and parse configuration excel file
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
			
		}
		catch (Exception e) {
			e.printStackTrace();
			//Messenger.exception();
		}
		
	}
	
}
