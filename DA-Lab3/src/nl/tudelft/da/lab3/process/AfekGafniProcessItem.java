package nl.tudelft.da.lab3.process;

import nl.tudelft.da.lab3.entity.ProcessItem;

public class AfekGafniProcessItem {
	public ProcessItem pi;
	public int id;
	public boolean Candidate;
	public boolean Captured;

	public AfekGafniProcessItem(ProcessItem pi) {
		this.pi = pi;
		this.id = 0;
		this.Candidate = "Candidate".equals(pi.algorithmFields) ? true : false;
		this.Captured = false;
	}

	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(pi.toString());
		sb.append(" ");
		sb.append("id=" + this.id);
		sb.append(" Candidate=" + this.Candidate);
		sb.append(" Captured=" + this.Captured);

		return sb.toString();
	}

}
