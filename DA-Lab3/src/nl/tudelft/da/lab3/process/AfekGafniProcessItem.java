package nl.tudelft.da.lab3.process;

import nl.tudelft.da.lab3.entity.ProcessItem;

public class AfekGafniProcessItem {
	public ProcessItem pi;
	public int id;
	public boolean Candidate;
	public boolean Captured;
	
	public AfekGafniProcessItem(ProcessItem pi){
		this.pi = pi;
		this.id = 0;
		this.Candidate = true;
		this.Captured = false;
	}
	
}
