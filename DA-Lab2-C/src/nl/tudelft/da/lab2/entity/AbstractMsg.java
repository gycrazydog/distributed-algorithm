package nl.tudelft.da.lab2.entity;

import java.io.Serializable;
import java.rmi.Remote;

import nl.tudelft.da.lab2.process.VClock;

public abstract class AbstractMsg implements Serializable, Remote{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public VClock clock;
	public ProcessItem sender;
	
	@Override
	public String toString() {
		String line = "Clock: " + this.clock.toString() + " msg from "
				+ this.sender.toShortString() + " Msg: " + this.toString();
		return line;
	}
	
}
