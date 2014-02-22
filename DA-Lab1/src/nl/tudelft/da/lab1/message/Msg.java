/**
 * 
 */
package nl.tudelft.da.lab1.message;

import java.io.Serializable;
import java.rmi.Remote;

import nl.tudelft.da.lab1.commom.ProcessItem;
import nl.tudelft.da.lab1.process.SClock;

/**
 * @author vincentgong
 * 
 */
public class Msg implements Serializable, Remote   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SClock clock;
	public String content;
	public ProcessItem sender;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Msg(){
		this.clock = new SClock();
	}

	public Msg(String content, ProcessItem sender){
		this.clock = new SClock();
		this.content = content;
		this.sender = sender;
	}

	public Msg(String content, String senderIP, int senderPort,
			String senderName){
		this.clock = new SClock();
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
