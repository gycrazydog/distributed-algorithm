/**
 * 
 */
package nl.tudelft.da.lab3.process;

import java.io.Serializable;

import nl.tudelft.da.lab3.entity.IAlgorithmProcess;

/**
 * @author vincentgong
 * 
 */
public class Sender implements Runnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IAlgorithmProcess process;

	public Sender(IAlgorithmProcess pro) {
		this.process = pro;
	}

	@Override
	public void run() {

		// Sender: GX
		// if (this.process.getName().equals("GX")) {
		// Request ppMsg = new Request("Requested By GX",
		// this.process.getName(), this.process.getClock()
		// .currentClock());
		// // code for broadcast the msg
		// System.out.println("process size:"
		// + this.process.getRequestSet().size());
		// this.process.MulticastingRequest(ppMsg);
		// // code for send one msg to someone:
		// // this.process.SendMsg("127.0.0.1", 3233, "HKX", ppMsg);
		// }
		this.sendCamptureRequest();
	}

	public void sendCamptureRequest() {
		// start the while code in Candidate process. Send the capture request
		// message to untraversed link.
		// TODO code
		
	}
}
