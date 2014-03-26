package nl.tudelft.da.lab2.msghandler;

import java.util.List;

import nl.tudelft.da.lab2.messages.AbstractMsg;
import nl.tudelft.da.lab2.messages.Grant;
import nl.tudelft.da.lab2.messages.Relinquish;
import nl.tudelft.da.lab2.messages.Request;
import nl.tudelft.da.lab2.process.Process;

public class RelinquishHandler implements IMsgHandler {
	Process pro;
	Relinquish rel;
	public RelinquishHandler(Process p,AbstractMsg abmsg) {
		this.pro = p;
		this.rel = (Relinquish)abmsg;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("RelinquishHandler is running, for "
				+ this.rel.toString());
		Request top = (Request)this.pro.reqQ.poll();
		Process oppo = null;
		List prolist = this.pro.getProcessesItemList();
		for(int i = 0 ; i< prolist.size();i++)
		{
			Process pr = (Process)prolist.get(i);
			if(pr.getName().equals(top.sender))
			{
				oppo = pr;
				break;
			}
		}
		this.pro.SendMsg(oppo.getIp(), oppo.getPort(), oppo.getName(), new Grant("",this.pro.getName(),this.pro.getClock().currentClock()));
		Request rq = new Request(this.rel.content,this.rel.sender,this.rel.clock.currentClock());
		this.pro.reqQ.add(rq);
		this.pro.currentGrant = rq;
	}

}
