/**
 * 
 */
package nl.tudelft.da.lab3.process;

import java.io.Serializable;

import nl.tudelft.da.lab3.entity.IAlgorithmProcess;
import nl.tudelft.da.lab3.messages.CaptureAttempMsg;
import nl.tudelft.da.lab3.messages.TestMessage;

/**
 * @author vincentgong
 * 
 */
public class Sender implements Runnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IAlgorithmProcess iap;

	public Sender(IAlgorithmProcess pro) {
		this.iap = pro;
	}

	@Override
	public void run() {
		AfekGafniProcess ap = (AfekGafniProcess)this.iap;
		if(ap.Candidate){
			while(ap.AlgorUntraversedLinkList.size()>1){
				ap.current_link = (AfekGafniProcessItem)ap.AlgorUntraversedLinkList.get(0);
				ap.Link_killed = false;
				CaptureAttempMsg cm = new CaptureAttempMsg(ap.level,ap.getProcess().getId(),ap.getProcess().getName());
				this.iap.getProcess().SendMsg(ap.current_link.pi.IP, ap.current_link.pi.port, ap.current_link.pi.name, cm);
				System.out.println("Candidate "+this.iap.getProcess().getName()+" trying to capture "+ap.current_link.pi.name);
				while(!ap.Link_killed){}
			}
			ap.elected = true;
			System.out.println("Process  "+ap.getProcess().getName()+"  Elected!");
		}
	}
	
	public void sendTestMessage(){
		
		if(this.iap.getProcess().getName().equals("HKX")){
			Process p = this.iap.getProcess();
			String msg = "Process: "+ p.getName() + " id=" + p.getId() + " ip: " + p.getIp();
			TestMessage tm = new TestMessage(p.getName(), msg);
//			this.iap.getProcess().broadcast(tm);
			this.iap.getProcess().SendMsg("127.0.0.1", 3233, "GX", tm);;
//			System.out.println("Test Message sent by "+ p.getName());
		}
	}

	public void sendCamptureRequest() {
		// start the while code in Candidate process. Send the capture request
		// message to untraversed link.
		Process p = this.iap.getProcess();
		AfekGafniProcess ap = (AfekGafniProcess)this.iap;
		String msg = "Process: "+ p.getName() + " id=" + p.getId() + " ip: " + p.getIp();
		CaptureAttempMsg cm = new CaptureAttempMsg(ap.level,p.getId(),p.getName());
//		this.iap.getProcess().broadcast(tm);
		this.iap.getProcess().SendMsg("127.0.0.1", 3233, "GX", cm);
//		System.out.println("Test Message sent by "+ p.getName());
		
	}
}
