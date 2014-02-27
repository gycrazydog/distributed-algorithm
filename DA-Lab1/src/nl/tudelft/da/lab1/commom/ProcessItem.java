/**
 * 
 */
package nl.tudelft.da.lab1.commom;

import java.io.Serializable;

/**
 * @author vincentgong
 * 
 */
public class ProcessItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	public String IP;
	public int port = 3323;
	public String name;
	
	public ProcessItem(String IP, int port, String name) {
		// TODO Auto-generated constructor stub
		this.IP = IP;
		this.port = port;
		this.name = name;
	}

	@Override
	public String toString() {
		String str = "Process:" + this.name + " IP: " + this.IP + " Port: "
				+ this.port;
		return str;
	}

	public String toShortString() {
		String str = this.name + "@"+ this.IP + ":" + this.port;
		return str;
	}
	public boolean equals(Object newprocess)
	{
		ProcessItem npi = (ProcessItem)newprocess;
		if(this.IP.equals(npi.IP)&&this.port==npi.port&&this.name.equals(npi.name))
		return true;
		else
		return false;
	}
}
