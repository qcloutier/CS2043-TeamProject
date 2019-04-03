package team9.transcriptanalyzer;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents cohort of transcripts.
 * @author jsudz Created on 3/?/19.
 * @author rbannister Updated on 4/3/19.
 * @author qcloutier Updated on 4/3/19.
 */
public class Cohort {
	
	private List<Transcript> transcripts;
	
	public Cohort() {
		this.transcripts = new ArrayList<Transcript>();
	}
	
	public void addTranscript(Transcript transcript) {
		transcripts.add(transcript);
	}
	
	public List<Transcript> getTranscripts() {
		return transcripts;
	}
	
	public String toString() {
		return "[" + transcripts + "]";
	}
	
}