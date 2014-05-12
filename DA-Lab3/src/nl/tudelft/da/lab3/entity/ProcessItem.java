/**
 * 
 */
package nl.tudelft.da.lab3.entity;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

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
	public List resourceSetNumber;

	public ProcessItem(String IP, int port, String name) {
		// TODO Auto-generated constructor stub
		this.IP = IP;
		this.port = port;
		this.name = name;
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		Iterator it = this.resourceSetNumber.iterator();
		while (it.hasNext()) {
			String rSet = (String) it.next();
			sb.append(rSet);
			sb.append(":");
		}

		String str = "Process1:" + this.name + " IP: " + this.IP + " Port: "
				+ this.port + " Resource Set: " + sb.toString();
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
	
	public List getResourceSet() {
		return resourceSetNumber;
	}

	public void setResourceSet(List resourceSet) {
		this.resourceSetNumber = resourceSet;
	}
}
