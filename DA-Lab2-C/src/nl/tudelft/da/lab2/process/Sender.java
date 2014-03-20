/**
 * 
 */
package nl.tudelft.da.lab2.process;

import java.io.Serializable;

import nl.tudelft.da.lab2.messages.AbstractMsg;
import nl.tudelft.da.lab2.messages.Postponed;

/**
 * @author vincentgong
 * 
 */
public class Sender implements Runnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double i;
	private Process process;

	public Sender(Process pro) {
		this.i = Math.random() * 1000;
		this.process = pro;
	}

	@Override
	public void run() {
		if (this.process.getName().equals("GX")) {
			Postponed ppMsg = new Postponed("Broadcast by pro",
					this.process.getName(), this.process.getClock().currentClock());
			
			// code for broadcast the msg
			this.process.broadcast(ppMsg);
			
			// code for send one msg to someone:
			// this.process.SendMsg("127.0.0.1", 3233, "HKX", ppMsg);
		}

		System.out.println("Sender.run() " + this.i);
	}

	public void handle(AbstractMsg absMsg) {
		System.out.println("Print the msg. Msg: " + absMsg.toString());
	}
}
