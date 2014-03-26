package nl.tudelft.da.lab2.msghandler;

import java.util.List;

import nl.tudelft.da.lab2.messages.AbstractMsg;
import nl.tudelft.da.lab2.messages.Inquire;
import nl.tudelft.da.lab2.messages.ProcessItem;
import nl.tudelft.da.lab2.messages.Relinquish;
import nl.tudelft.da.lab2.process.Process;

public class InquireHandler implements IMsgHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Process pro;
	Inquire iq;
	public InquireHandler(Process p,AbstractMsg abmsg) {
		this.pro = p;
		this.iq = (Inquire)abmsg;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("InquireHandler is running, for "
				+ this.iq.toString());
		while(!this.pro.postponed&&this.pro.num_of_grants!=this.pro.resourceSetProcessNumber){}
		if(this.pro.postponed||this.pro.relinquished)
		{	
			this.pro.relinquished = true;
			List prolist = this.pro.getProcessesItemList();
			for(int i = 0 ; i< prolist.size();i++)
			{
				ProcessItem pr = (ProcessItem)prolist.get(i);
				if(pr.name.equals(this.iq.sender))
				{
					this.pro.SendMsg(pr.IP,pr.port ,pr.name, new Relinquish("",this.pro.getName(),this.pro.getClock().currentClock()));
					this.pro.num_of_grants = 0;
					this.pro.postponed = false;
					break;
				}
			}
		}
	}

}
