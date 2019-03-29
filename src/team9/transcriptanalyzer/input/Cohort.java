package team9.transcriptanalyzer.input;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Represents all the transcripts contained in the cohort folder, contained in a list.
 */
public class Cohort {
	
	ArrayList<Transcript> transcripts;
	
	File folder;
	
	public static void main(String[] args) throws IOException {
		System.out.println(new Cohort("demo/IO Spec Cohort"));
	}
	
	public Cohort(String filepath) throws IOException{
		folder=new File(filepath);
		transcripts=new ArrayList<Transcript>();
		fileGetTranscripts(folder);
	}
	
	private void fileGetTranscripts (File folder) throws IOException{
		FilenameFilter txtFilter=createFilter(".txt");
		File[]filesInFolder=folder.listFiles(txtFilter);
		for(File file:filesInFolder) {
			transcripts.add(new Transcript(file));
		}
	}
	
	private FilenameFilter createFilter(String ending) {
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				
				if (lowercaseName.endsWith(ending))
					return true;
				else 
					return false;
			}
		};
		return filter;
	}
	
	public String toString() {
		String result= "All Transcripts in Cohort Folder: "+folder.getAbsolutePath()+"\n\n";
		for(Transcript t:transcripts) {
			result+=t+"\n";
		}
		return result;
	}
	
}