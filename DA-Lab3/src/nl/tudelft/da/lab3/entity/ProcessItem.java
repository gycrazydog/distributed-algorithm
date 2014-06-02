/**
 * 
 */
package nl.tudelft.da.lab3.entity;

import java.io.Serializable;

/**
 * @author vincentgong
 * 
 */
public class ProcessItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	public String IP;
	public int port = 3323;
	public String name;
	public String algorithmFields;

	public ProcessItem(String IP, int port, String name, String algorithmFields) {
		// TODO Auto-generated constructor stub
		this.IP = IP;
		this.port = port;
		this.name = name;
		this.algorithmFields = algorithmFields;
	}

	@Override
	public String toString() {
		String str = "Process1:" + this.name + " IP: " + this.IP + " Port: "
				+ this.port + " Algoritm Fields: " + algorithmFields;
		return str;
	}

	public String toShortString() {
		String str = this.name + "@" + this.IP + ":" + this.port;
		return str;
	}

	@Override
	public boolean equals(Object newprocess) {
		ProcessItem npi = (ProcessItem) newprocess;
		if (this.IP.equals(npi.IP) && this.port == npi.port
				&& this.name.equals(npi.name))
			return true;
		else
			return false;
	}
	
}
