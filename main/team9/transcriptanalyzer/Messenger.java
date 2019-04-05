package team9.transcriptanalyzer;

/**
 * Provides static methods for providing messages to the user.
 * @author rbannister Created on 3/?/19.
 * @author qcloutier Updated on 4/3/19.
 */
public class Messenger {

	/**
	 * Message explaining what files need to be 
	 * passed in order for the program to run.
	 */
	public static void usage() {
		System.out.print(
			"USAGE: \n" +
			"  To run the Transcript Analyzer, please provide the following: \n" + 
			"    1. Configuration input file (.xlsx or .xls) \n" + 
			"    2. Cohort folder containing transcripts (.txt) \n" +
			"    3. Results output file (.xlsx) \n" +
			"  These values must be provided in that order when running the program, i.e.: \n" +
			"    java -jar TranscriptAnalyzer.jar <Configuration.xlsx> <Cohort> <Results.xlsx> \n"
		);
	}
	
	/**
	 * Message to display when everything is processed successfully.
	 */
	public static void success() {
		System.out.print(
			"SUCCESS: \n" + 
			"  Transcripts have been processed. \n" + 
			"  Results file has been written. \n"
		);
	}
	
	/**
	 * Message to display when there are uncategorized course areas.
	 */
	public static void updateArea() {
		System.out.print(
			"IMPORTANT: \n" + 
			"  Courses that are not in an area were found. \n" + 
			"  Please sort them and rerun the program. \n" +
			"  They are listed under the 'Unsorted' category in the output file. \n"
		);
	}
	
	/**
	 * Message to display when a transcript could not be parsed.
	 */
	public static void parseError() {
		System.out.print(
			"WARNING: \n" + 
			"  One or more transcripts could not be parsed. \n" + 
			"  Please check that they are all well-formed. \n"
		);
	}
	
	/**
	 * Message to display when an unexpected exception occurs.
	 * @param e The Exception, which will have its message displayed.
	 */
	public static void exception(Exception e) {
		System.out.print(
			"ERROR: \n" + 
			"  An unhandled exception occured. \n" + 
			"STACK TRACE: \n    "
		);
		e.printStackTrace();
	}
	
}
