package nl.tudelft.da.lab2.msghandler;

import nl.tudelft.da.lab2.messages.AbstractMsg;
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
	}

}
