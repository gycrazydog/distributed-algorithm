package nl.tudelft.da.lab2.msghandler;

import java.util.List;

import nl.tudelft.da.lab2.messages.AbstractMsg;
import nl.tudelft.da.lab2.messages.Grant;
import nl.tudelft.da.lab2.messages.Inquire;
import nl.tudelft.da.lab2.messages.Postponed;
import nl.tudelft.da.lab2.messages.Relinquish;
import nl.tudelft.da.lab2.messages.Request;
import nl.tudelft.da.lab2.process.Process;

public class RequestHandler implements IMsgHandler {
	Process pro;
	Request req;
	public RequestHandler(Process p,AbstractMsg abmsg) {
		this.pro = p;
		this.req = (Request)abmsg;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Process oppo = null;
		Process cg = null;
		List prolist = this.pro.getProcessesItemList();
		for(int i = 0 ; i< prolist.size();i++)
		{
			Process pr = (Process)prolist.get(i);
			if(pr.getName().equals(this.req.sender))
				oppo = pr;
			else if(pr.getName().equals(this.pro.currentGrant.sender))
				cg = pr;
		}
		if(this.pro.currentGrant==null)
		{
			this.pro.SendMsg(oppo.getIp(),oppo.getPort() ,oppo.getName(), new Grant("",this.pro.getName(),this.pro.getClock().currentClock()));
			this.pro.currentGrant = this.req;
		}
		else{
			this.pro.reqQ.add(req);
			if(comp(req,this.pro.currentGrant)==1)
				this.pro.SendMsg(oppo.getIp(), oppo.getPort(), oppo.getName(), new Postponed("",this.pro.getName(),this.pro.getClock().currentClock()));
			else
				this.pro.SendMsg(cg.getIp(), cg.getPort(), cg.getName(), new Inquire("",this.pro.getName(),this.pro.getClock().currentClock()));
		}
	}
	private int comp(Request m1, Request m2) {
		// TODO Auto-generated method stub
		Process oppo1 = null;
		Process oppo2 = null;
		for(int i = 0 ; i< this.pro.getProcessesItemList().size();i++)
		{
			Process pr = (Process)this.pro.getProcessesItemList().get(i);
			if(pr.getName().equals(m1.sender))
				oppo1 = pr;
			else if(pr.getName().equals(m2.sender))
				oppo2 = pr;
		}
		int clock1 = m1.clock.currentClock();
		int clock2 = m2.clock.currentClock();
		if ( clock1 == clock2) {
			return oppo1.getId()<oppo2.getId()?-1 : 1;
		} else {
			return clock1 < clock2 ? -1 : 1;
		}
	}

}
