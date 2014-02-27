package nl.tudelft.da.lab1.message;

import nl.tudelft.da.lab1.commom.ProcessItem;
import nl.tudelft.da.lab1.process.SClock;

public class Ack extends AbstractMsg {
	private static final long serialVersionUID = 1L;
	public SClock msgClock;
	public ProcessItem msgSender;
	public Ack(ProcessItem curSender,SClock curClock,ProcessItem preSender,SClock preClock){
		this.clock = new SClock(curClock);
		this.sender = curSender;
		this.msgClock = new SClock(preClock);
		this.msgSender = preSender;
	}
}