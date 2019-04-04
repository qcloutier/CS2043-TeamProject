package team9.transcriptanalyzer;

import java.io.IOException;

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
			"  All transcripts processed successfully. \n" + 
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
	 * Message to display when an IOException occurs. 
	 * @param e The IOException, which will have its message displayed.
	 */
	public static void formatError(IOException e) {
		System.out.print(
			"ERROR: \n" + 
			"  Unable to parse one or more input files. \n" + 
			"  Please verify that they are all in the correct format. \n" +
			"STACK TRACE: \n    "
		);
		e.printStackTrace();
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
