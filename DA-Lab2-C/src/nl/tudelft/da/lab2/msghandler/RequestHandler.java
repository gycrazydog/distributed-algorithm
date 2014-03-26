package nl.tudelft.da.lab2.msghandler;

import java.util.List;

import nl.tudelft.da.lab2.messages.AbstractMsg;
import nl.tudelft.da.lab2.messages.Grant;
import nl.tudelft.da.lab2.messages.Inquire;
import nl.tudelft.da.lab2.messages.Postponed;
import nl.tudelft.da.lab2.messages.ProcessItem;
import nl.tudelft.da.lab2.messages.Relinquish;
import nl.tudelft.da.lab2.messages.Request;
import nl.tudelft.da.lab2.process.Process;

public class RequestHandler implements IMsgHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Process pro;
	Request req;
	public RequestHandler(Process p,AbstractMsg abmsg) {
		this.pro = p;
		this.req = (Request)abmsg;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("RequestHandler is running, for "
				+ this.req.toString());
		
		if(this.req.sender.equals("GXY")){
			System.out.println("GXY");
		}
		
		if(this.req.sender.equals("GX")){
			System.out.println("GX");
		}
		
		ProcessItem oppo = null;
		ProcessItem cg = null;
		List prolist = this.pro.getProcessesItemList();
		for(int i = 0 ; i< prolist.size();i++)
		{
			ProcessItem pr = (ProcessItem)prolist.get(i);
			if(pr.name.equals(this.req.sender))
				oppo = pr;
			else if(this.pro.currentGrant!=null&&pr.name.equals(this.pro.currentGrant.sender))
				cg = pr;
		}
		
//		//----test--
//		if(this.pro.getName().equals("HKX")){
//			this.pro.SendMsg(oppo.IP, oppo.port, oppo.name, new Postponed("",this.pro.getName(),this.pro.getClock().currentClock()));
//			return;
//		}
//		//---test---
		
		
		if(this.pro.currentGrant==null)
		{
			this.pro.SendMsg(oppo.IP,oppo.port ,oppo.name, new Grant("",this.pro.getName(),this.pro.getClock().currentClock()));
			this.pro.currentGrant = this.req;
		}
		else{
			this.pro.reqQ.add(req);
			if(comp(req,this.pro.currentGrant)==1)
				this.pro.SendMsg(oppo.IP, oppo.port, oppo.name, new Postponed("",this.pro.getName(),this.pro.getClock().currentClock()));
			else
				this.pro.SendMsg(cg.IP, cg.port, cg.name, new Inquire("",this.pro.getName(),this.pro.getClock().currentClock()));
		}
	}
	private int comp(Request m1, Request m2) {
		// TODO Auto-generated method stub
		ProcessItem oppo1 = null;
		ProcessItem oppo2 = null;
		for(int i = 0 ; i< this.pro.getProcessesItemList().size();i++)
		{
			ProcessItem pr = (ProcessItem)this.pro.getProcessesItemList().get(i);
			if(pr.name.equals(m1.sender))
				oppo1 = pr;
			else if(pr.name.equals(m2.sender))
				oppo2 = pr;
		}
		int clock1 = m1.clock.currentClock();
		int clock2 = m2.clock.currentClock();
		if ( clock1 == clock2) {
			return oppo1.name.compareTo(oppo2.name);
		} else {
			return clock1 < clock2 ? -1 : 1;
		}
	}

}
