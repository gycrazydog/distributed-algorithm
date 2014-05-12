package nl.tudelft.da.lab3.entity;

import nl.tudelft.da.lab3.process.Sender;

public interface IAlgorithmProcess {
	
	public String getName();
	public String getID();
	public Sender getSender();
}
