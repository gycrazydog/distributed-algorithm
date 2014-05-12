/**
 * 
 */
package nl.tudelft.da.lab3.process;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Random;

import nl.tudelft.da.lab2.messages.AbstractMsg;
import nl.tudelft.da.lab2.messages.Postponed;
import nl.tudelft.da.lab2.messages.ProcessItem;
import nl.tudelft.da.lab2.messages.Request;

/**
 * @author vincentgong
 * 
 */
public class Sender implements Runnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Process process;

	public Sender(Process pro) {
		this.process = pro;
	}

	@Override
	public void run() {

		// Sender: GX
		if (this.process.getName().equals("GX")) {
			Request ppMsg = new Request("Requested By GX",
					this.process.getName(), this.process.getClock()
							.currentClock());
			// code for broadcast the msg
			System.out.println("process size:"
					+ this.process.getRequestSet().size());
			this.process.MulticastingRequest(ppMsg);
			// code for send one msg to someone:
			// this.process.SendMsg("127.0.0.1", 3233, "HKX", ppMsg);
		}
		
		// Sender GXY
//		if (this.process.getName().equals("GXY")) {
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Request ppMsg = new Request("Requested By GXY",
//					this.process.getName(), this.process.getClock()
//							.currentClock());
//			// code for broadcast the msg
//			System.out.println("process size:"
//					+ this.process.getRequestSet().size());
//			this.process.MulticastingRequest(ppMsg);
//
//			// code for send one msg to someone:
//			// this.process.SendMsg("127.0.0.1", 3233, "HKX", ppMsg);
//		}
		
		// Sender HKX
//		if (this.process.getName().equals("HKX")) {
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Request ppMsg = new Request("Requested By HKX",
//					this.process.getName(), this.process.getClock()
//							.currentClock());
//			// code for broadcast the msg
//			System.out.println("process size:"
//					+ this.process.getRequestSet().size());
//			this.process.MulticastingRequest(ppMsg);
//
//			// code for send one msg to someone:
//			// this.process.SendMsg("127.0.0.1", 3233, "HKX", ppMsg);
//			
//		}
		
		// Sender WF
		if (this.process.getName().equals("WF")) {
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Request ppMsg = new Request("Requested By WF",
					this.process.getName(), 0);
			// code for broadcast the msg
			System.out.println("process size:"
					+ this.process.getRequestSet().size());
			this.process.MulticastingRequest(ppMsg);

			// code for send one msg to someone:
			// this.process.SendMsg("127.0.0.1", 3233, "WF", ppMsg);
			
		}
	}

	public void handle(AbstractMsg absMsg) {
		System.out.println("Print the msg. Msg: " + absMsg.toString());
	}
}
