package nl.tudelft.da.lab3.msghandler;

import java.util.Random;

import nl.tudelft.da.lab2.messages.AbstractMsg;
import nl.tudelft.da.lab2.messages.Grant;
import nl.tudelft.da.lab2.messages.Release;
import nl.tudelft.da.lab2.process.Process;

public class GrantHandler implements IMsgHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Process pro;
	Grant gr;

	public GrantHandler(Process p, AbstractMsg abmsg) {
		this.pro = p;
		this.gr = (Grant) abmsg;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.pro.num_of_grants++;
		System.out.println("GrantHandler is running, for " + this.gr.toString()
				+ "  number of grants: " + this.pro.num_of_grants);
		if (this.pro.num_of_grants == this.pro.getRequestSet().size()) {
			System.out.println("critical section entered!!!!!");
			// Critical Section
			Random r = new Random(this.gr.clock.currentClock());

				try {
					Thread.sleep(Math.abs(r.nextInt()) % 5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
//			//-----test
//			if(this.pro.getName().equals("WF")){
//				System.out.println("Grant comes from "+this.gr.sender);
//				System.out.println();
//			}
//			//----test
			
				this.pro.MulticastingRequest(new Release("Broadcast by pro",
						this.pro.getName(), this.pro.getClock().currentClock()));
			
			this.pro.num_of_grants = 0;
		}
	}

}
