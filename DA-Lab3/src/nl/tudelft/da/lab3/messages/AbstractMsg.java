package nl.tudelft.da.lab3.messages;

import java.io.Serializable;
import java.rmi.Remote;

public abstract class AbstractMsg implements Serializable, Remote {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String sender;
	public String content;

	@Override
	public String toString() {
		String line = " msg from " + this.sender + " Content: " + content;
		return line;
	}
}
