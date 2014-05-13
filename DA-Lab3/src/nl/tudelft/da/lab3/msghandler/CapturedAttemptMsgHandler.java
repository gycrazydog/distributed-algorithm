package nl.tudelft.da.lab3.msghandler;

import nl.tudelft.da.lab3.entity.IAlgorithmProcess;
import nl.tudelft.da.lab3.entity.IMsgHandler;
import nl.tudelft.da.lab3.messages.AbstractMsg;
import nl.tudelft.da.lab3.messages.CaptureAttempMsg;


public class CapturedAttemptMsgHandler implements IMsgHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	IAlgorithmProcess iap;
	CaptureAttempMsg ca;

	public CapturedAttemptMsgHandler(IAlgorithmProcess iap, AbstractMsg abmsg) {
		this.iap = iap;
		this.ca = (CaptureAttempMsg) abmsg;
	}
	
	public CapturedAttemptMsgHandler(){
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//this.iap.SendMsg(ip, port, name, msg);// call SendMsg() from iap
		//this.iap.getSender().sendCamptureRequest(); // call functions from sender of the iap

	}

}
