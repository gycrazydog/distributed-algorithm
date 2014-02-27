/**
 * 
 */
package nl.tudelft.da.lab1.message;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.HashMap;

import nl.tudelft.da.lab1.commom.ProcessItem;
import nl.tudelft.da.lab1.process.SClock;

/**
 * @author vincentgong
 * 
 */
public class Msg extends AbstractMsg  {

	private static final long serialVersionUID = 1L;
	public String content;
	public HashMap<String,Boolean> AckQueue;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Msg(){
		this.clock = new SClock();
	}

	public Msg(String content, ProcessItem sender,SClock curClock){
		this.clock = new SClock(curClock);
		this.content = content;
		this.sender = sender;
	}

	public Msg(String content, String senderIP, int senderPort,
			String senderName,SClock curClock){
		this.clock = new SClock(curClock);
		this.content = content;
		this.sender = new ProcessItem(senderIP, senderPort, senderName);
	}

	@Override
	public String toString() {
		String line = "Clock: " + this.clock.toString() + " from "
				+ this.sender.toShortString() + " Msg: " + content;
		return line;
	}

}
