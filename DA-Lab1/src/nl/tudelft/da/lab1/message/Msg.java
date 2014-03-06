/**
 * 
 */
package nl.tudelft.da.lab1.message;

import java.util.HashMap;

import nl.tudelft.da.lab1.commom.ProcessItem;
import nl.tudelft.da.lab1.process.SClock;

/**
 * @author vincentgong
 * 
 */
public class Msg extends AbstractMsg {

	private static final long serialVersionUID = 1L;
	public String content;
	public int MsgId;
	public HashMap<String, Boolean> AckQueue;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Msg() {
		this.clock = new SClock();
	}

	public Msg(String content, ProcessItem sender, int curClock,int MsgId) {
		this.clock = new SClock(curClock);
		this.content = content;
		this.sender = sender;
		this.MsgId = MsgId;
	}

	public Msg(String content, String senderIP, int senderPort,
			String senderName, int curClock, int MsgId) {
		this.clock = new SClock(curClock);
		this.content = content;
		this.sender = new ProcessItem(senderIP, senderPort, senderName);
		this.MsgId = MsgId;
	}

	@Override
	public String toString() {
		String line = "Clock: " + this.clock.toString() + " msg from "
				+ this.sender.toShortString() + " Msg: " + content;
		return line;
	}

}
