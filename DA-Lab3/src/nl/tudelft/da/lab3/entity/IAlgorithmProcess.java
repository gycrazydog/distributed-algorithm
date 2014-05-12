package nl.tudelft.da.lab3.entity;

import nl.tudelft.da.lab3.messages.AbstractMsg;
import nl.tudelft.da.lab3.process.Sender;

public interface IAlgorithmProcess {
	
	public String getName();
	public String getID();
	public Sender getSender();
	public void SendMsg(String ip, int port, String name, AbstractMsg msg);
	public void Receive(AbstractMsg abmsg);
}
