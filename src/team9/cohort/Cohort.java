package team9.cohort;

import java.io.File;
import java.util.ArrayList;

public class Cohort{
	/*
	 * Looks p janky at the moment but this represents all the transcripts contained in the cohort folder contained in a list.
	 */
	
	ArrayList<Transcript> transcripts;
	
	File folder;
	
	public Cohort(String filepath) {
		folder=new File(filepath);
		fileGetTranscripts(folder);
	}
	
	private void fileGetTranscripts(File folder) {
		/*look into filename filters because it's needed
		for(File file: folder.listFiles(filter)) {
			transcripts.add(new Transcript(file));
		}*/
	}
	
	
}