package team9.transcriptanalyzer;

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
			// TODO
			
			// Read and parse transcript cohort
			// TODO
			
			// Calculate results and write to output file
			// TODO
			
		}
		catch (Exception e) {
			//Messenger.exception();
		}
		
	}
	
}