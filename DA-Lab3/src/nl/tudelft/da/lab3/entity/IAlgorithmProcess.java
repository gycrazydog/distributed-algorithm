package nl.tudelft.da.lab3.entity;

import nl.tudelft.da.lab3.messages.AbstractMsg;
import nl.tudelft.da.lab3.process.*;
import nl.tudelft.da.lab3.process.Process;

public interface IAlgorithmProcess {
	
	public Process getProcess();
	public Sender getSender();
	public void SendMsg(String ip, int port, String name, AbstractMsg msg);
	public void Receive(AbstractMsg abmsg);
}
