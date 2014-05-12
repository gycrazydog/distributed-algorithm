package nl.tudelft.da.lab3.msghandler;

import nl.tudelft.da.lab3.entity.IMsgHandler;
import nl.tudelft.da.lab3.messages.AbstractMsg;
import nl.tudelft.da.lab3.messages.CaptureAttempMsg;


public class CapturedAttemptMsgHandler implements IMsgHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Process pro;
	CaptureAttempMsg ca;

	public CapturedAttemptMsgHandler(Process p, AbstractMsg abmsg) {
		this.pro = p;
		this.ca = (CaptureAttempMsg) abmsg;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		this.pro.num_of_grants++;
//		System.out.println("GrantHandler is running, for " + this.ca.toString()
//				+ "  number of grants: " + this.pro.num_of_grants);
//		if (this.pro.num_of_grants == this.pro.getRequestSet().size()) {
//			System.out.println("critical section entered!!!!!");
//			// Critical Section
//			Random r = new Random(this.ca.clock.currentClock());
//
//				try {
//					Thread.sleep(Math.abs(r.nextInt()) % 5000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	
//			
//				this.pro.MulticastingRequest(new Release("Broadcast by pro",
//						this.pro.getName(), this.pro.getClock().currentClock()));
//			
//			this.pro.num_of_grants = 0;
//		}
	}

}
