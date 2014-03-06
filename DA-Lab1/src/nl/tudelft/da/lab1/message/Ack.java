package nl.tudelft.da.lab1.message;

import nl.tudelft.da.lab1.commom.ProcessItem;
import nl.tudelft.da.lab1.process.SClock;

public class Ack extends AbstractMsg {
	private static final long serialVersionUID = 1L;
	public int msgId;
	public ProcessItem msgSender;

	public Ack(ProcessItem curSender, int curClock, ProcessItem preSender,
			int MsgId) {
		this.clock = new SClock(curClock);
		this.sender = curSender;
		this.msgId = MsgId;
		this.msgSender = preSender;
	}

	@Override
	public String toString() {
		String line = "Clock: " + this.clock.toString() + " ack from "
				+ this.sender.toShortString()+" Msg: "+this.msgId+ " from "+this.msgSender.id;
		return line;
	}
}
