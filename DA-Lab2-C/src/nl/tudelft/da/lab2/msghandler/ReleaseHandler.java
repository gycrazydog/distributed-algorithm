package nl.tudelft.da.lab2.msghandler;

import java.util.List;

import nl.tudelft.da.lab2.messages.AbstractMsg;
import nl.tudelft.da.lab2.messages.Grant;
import nl.tudelft.da.lab2.messages.Release;
import nl.tudelft.da.lab2.messages.Relinquish;
import nl.tudelft.da.lab2.messages.Request;
import nl.tudelft.da.lab2.process.Process;

public class ReleaseHandler implements IMsgHandler {
	Process pro;
	Release rel;
	public ReleaseHandler(Process p,AbstractMsg abmsg) {
		this.pro = p;
		this.rel = (Release)abmsg;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Request req = (Request)this.pro.reqQ.peek();
		if(this.rel.sender.equals(req.sender))
			this.pro.reqQ.poll();
		this.pro.currentGrant = null;
		if(!this.pro.reqQ.isEmpty()){
			Request top = (Request)this.pro.reqQ.peek();
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
			this.pro.SendMsg(oppo.getIp(),oppo.getPort() ,oppo.getName(), new Grant("",this.pro.getName(),this.pro.getClock().currentClock()));
		}
	}

}
