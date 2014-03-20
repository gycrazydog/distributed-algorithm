package nl.tudelft.da.lab2.msghandler;

import java.util.List;

import nl.tudelft.da.lab2.messages.AbstractMsg;
import nl.tudelft.da.lab2.messages.Grant;
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
		List prolist = this.pro.getProcessesItemList();
		for(int i = 0 ; i< prolist.size();i++)
		{
			Process pr = (Process)prolist.get(i);
			if(pr.getName().equals(this.req.sender))
			{
				oppo = pr;
				break;
			}
		}
		if(this.pro.currentGrant==null)
		{
			this.pro.SendMsg(oppo.getIp(),oppo.getPort() ,oppo.getName(), new Grant("",this.pro.getName(),this.pro.getClock().currentClock()));
			this.pro.currentGrant = oppo;
		}
		else{
			this.pro.reqQ.add(req);
			if(true)
			{
				this.pro.SendMsg(oppo.getIp(), oppo.getPort(), oppo.getName(), new Postponed("",this.pro.getName(),this.pro.getClock().currentClock()));
			}
		}
	}

}
