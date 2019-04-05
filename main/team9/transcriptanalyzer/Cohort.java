package team9.transcriptanalyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cohort of transcripts.
 * @author jsudz Created on 3/?/19.
 * @author rbannister Updated on 4/3/19.
 * @author qcloutier Updated on 4/3/19.
 */
public class Cohort {
	
	private List<Transcript> transcripts;
	
	/**
	 * Creates the Cohort and initializes its list.
	 */
	public Cohort() {
		this.transcripts = new ArrayList<Transcript>();
	}
	
	/**
	 * Adds a given transcript to the list.
	 * @param transcript The transcript to add.
	 */
	public void addTranscript(Transcript transcript) {
		transcripts.add(transcript);
	}
	
	/**
	 * Retrieves a list of transcripts.
	 * @return The list of transcripts.
	 */
	public List<Transcript> getTranscripts() {
		return transcripts;
	}
	
	public String toString() {
		return "[" + transcripts + "]";
	}
	
}