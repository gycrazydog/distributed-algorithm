package nl.tudelft.da.lab1.message;

import java.io.Serializable;
import java.rmi.Remote;

import nl.tudelft.da.lab1.commom.ProcessItem;
import nl.tudelft.da.lab1.process.SClock;

public abstract class AbstractMsg implements Serializable, Remote{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SClock clock;
	public ProcessItem sender;
}
