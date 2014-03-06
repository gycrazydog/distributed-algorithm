package nl.tudelft.da.lab1.message;

import nl.tudelft.da.lab1.commom.ProcessItem;
import nl.tudelft.da.lab1.process.SClock;

public class Ack extends AbstractMsg {
	private static final long serialVersionUID = 1L;
	public SClock msgClock;
	public ProcessItem msgSender;

	public Ack(ProcessItem curSender, int curClock, ProcessItem preSender,
			int preClock) {
		this.clock = new SClock(curClock);
		this.sender = curSender;
		this.msgClock = new SClock(preClock);
		this.msgSender = preSender;
	}

	@Override
	public String toString() {
		String line = "Clock: " + this.clock.toString() + " ack from "
				+ this.sender.toShortString()+" Msg: "+this.msgClock.currentClock();
		return line;
	}
}
