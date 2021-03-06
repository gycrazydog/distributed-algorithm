package nl.tudelft.da.lab2.messages;

import java.io.Serializable;
import java.rmi.Remote;

import nl.tudelft.da.lab2.process.*;
import nl.tudelft.da.lab2.process.Process;

public abstract class AbstractMsg implements Serializable, Remote{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SClock clock;
	public String sender;
	public String content;
	
	@Override
	public String toString() {
		String line = "Clock: " + this.clock.toString() + " msg from "
				+ this.sender;
		return line;
	}
}
