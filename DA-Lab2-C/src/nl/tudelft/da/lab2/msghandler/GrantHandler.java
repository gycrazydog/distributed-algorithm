package nl.tudelft.da.lab2.msghandler;

import java.util.Random;

import nl.tudelft.da.lab2.messages.AbstractMsg;
import nl.tudelft.da.lab2.messages.Grant;
import nl.tudelft.da.lab2.messages.Inquire;
import nl.tudelft.da.lab2.process.Process;

public class GrantHandler implements IMsgHandler {
	Process pro;
	Grant gr;
	public  GrantHandler(Process p,AbstractMsg abmsg) {
		this.pro = p;
		this.gr = (Grant)abmsg;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.pro.num_of_grants++;
		System.out.println("GrantHandler is running, for "
				+ this.gr.toString()+"  number of grants: "+this.pro.num_of_grants);
		if(this.pro.num_of_grants==this.pro.resourceSetProcessNumber)
		{
			System.out.println("critical section entered!!!!!");
			//Critical Section
			Random r = new Random(this.gr.clock.currentClock());
			try {
				Thread.sleep(Math.abs(r.nextInt()) % 5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.pro.num_of_grants = 0;
		}
	}

}
