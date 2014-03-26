package nl.tudelft.da.lab2.msghandler;

import java.util.List;

import nl.tudelft.da.lab2.messages.AbstractMsg;
import nl.tudelft.da.lab2.messages.Grant;
import nl.tudelft.da.lab2.messages.ProcessItem;
import nl.tudelft.da.lab2.messages.Release;
import nl.tudelft.da.lab2.messages.Request;
import nl.tudelft.da.lab2.process.Process;

public class ReleaseHandler implements IMsgHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Process pro;
	Release rel;
	public ReleaseHandler(Process p,AbstractMsg abmsg) {
		this.pro = p;
		this.rel = (Release)abmsg;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("ReleaseHandler is running, for "
				+ this.rel.toString());
		this.pro.currentGrant = null;
		if(!this.pro.reqQ.isEmpty()){
			Request req = (Request)this.pro.reqQ.peek();
			if(this.rel.sender.equals(req.sender))
				this.pro.reqQ.poll();
			Request top = (Request)this.pro.reqQ.peek();
			ProcessItem oppo = null;
			List prolist = this.pro.getProcessesItemList();
			for(int i = 0 ; i< prolist.size();i++)
			{
				ProcessItem pr = (ProcessItem)prolist.get(i);
				if(pr.name.equals(top.sender))
				{
					oppo = pr;
					break;
				}
			}
			this.pro.SendMsg(oppo.IP, oppo.port,oppo.name, new Grant("",this.pro.getName(),this.pro.getClock().currentClock()));
		}
	}

}
