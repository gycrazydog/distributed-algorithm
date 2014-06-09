package nl.tudelft.da.lab3.msghandler;

import java.util.Iterator;

import nl.tudelft.da.lab3.entity.IAlgorithmProcess;
import nl.tudelft.da.lab3.entity.IMsgHandler;
import nl.tudelft.da.lab3.messages.AbstractMsg;
import nl.tudelft.da.lab3.messages.CaptureAttempMsg;
import nl.tudelft.da.lab3.process.AfekGafniProcess;
import nl.tudelft.da.lab3.process.AfekGafniProcessItem;

public class CapturedAttemptMsgHandler implements IMsgHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	AfekGafniProcess iap;
	CaptureAttempMsg ca;

	public CapturedAttemptMsgHandler(IAlgorithmProcess iap, AbstractMsg abmsg) {
		this.iap = (AfekGafniProcess) iap;
		this.ca = (CaptureAttempMsg) abmsg;
	}

	public CapturedAttemptMsgHandler() {

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		CaptureAttempMsg cam = (CaptureAttempMsg) this.ca;
		int level = cam.level;
		int id_ = cam.id;
		if (this.iap.Candidate) {
			if (id_ == this.iap.getProcess().getId() && !this.iap.Captured) {
				System.out.println("Candidate "
						+ this.iap.getProcess().getName() + " recieved "
						+ cam.level + "  " + cam.id);
				this.iap.level++;
				this.iap.Link_killed = true;
				
				synchronized(this.iap.lock){
					Iterator it = this.iap.AlgorUntraversedLinkList.iterator();
					while(it.hasNext()){
						AfekGafniProcessItem link = (AfekGafniProcessItem) it.next();
						if (link.id == this.iap.current_link.id) {
							this.iap.AlgorUntraversedLinkList.remove(link);
							System.out.println("Candidate "
									+ this.iap.getProcess().getName()
									+ " captured current link to " + link.pi.name);
						}
					}
				}
				
				// synchronized(this) {
//				for (Object api : this.iap.AlgorUntraversedLinkList) {
//					AfekGafniProcessItem link = (AfekGafniProcessItem) api;
//					if (link.id == this.iap.current_link.id) {
//						this.iap.AlgorUntraversedLinkList.remove(link);
//						System.out.println("Candidate "
//								+ this.iap.getProcess().getName()
//								+ " captured current link to " + link.pi.name);
//					}
//				}
				// }
			} else {
				if (level < this.iap.level
						|| (level == this.iap.level && id_ < this.iap
								.getProcess().getId())) {
					System.out.println("Candidate "
							+ this.iap.getProcess().getName()
							+ " discard message! " + cam.id + " " + cam.level);
					return;
				} else {
					CaptureAttempMsg msg = new CaptureAttempMsg(level, id_,
							this.iap.getProcess().getName());
					for (Object api : this.iap.AlgorProcessItemList) {
						AfekGafniProcessItem process = (AfekGafniProcessItem) api;
						if (process.id == id_) {
							this.iap.SendMsg(process.pi.IP, process.pi.port,
									process.pi.name, msg);
							System.out.println("Candidate "
									+ this.iap.getProcess().getName()
									+ " got killed by " + process.pi.name);
							break;
						}
					}
					this.iap.Captured = true;
					return;
				}
			}
		} else {
			if (level < this.iap.level
					|| (level == this.iap.level && id_ < this.iap.getProcess()
							.getId())) {
				return;
			} else if (level > this.iap.level
					|| (level == this.iap.level && id_ > this.iap.getProcess()
							.getId())) {
				for (Object api : this.iap.AlgorProcessItemList) {
					AfekGafniProcessItem process = (AfekGafniProcessItem) api;
					if (process.id == id_) {
						this.iap.current_father = process;
						this.iap.getProcess().setId(id_);
						this.iap.level = level;
						if (this.iap.current_father == null)
							this.iap.current_father = this.iap.potential_father;
						CaptureAttempMsg msg = new CaptureAttempMsg(level, id_,
								this.iap.getProcess().getName());
						this.iap.SendMsg(this.iap.current_father.pi.IP,
								this.iap.current_father.pi.port,
								this.iap.current_father.pi.name, msg);
						System.out.println("Ordinary "
								+ this.iap.getProcess().getName()
								+ " send kill attempt to " + process.pi.name);
						return;
					}
				}
			} else {
				this.iap.current_father = this.iap.potential_father;
				CaptureAttempMsg msg = new CaptureAttempMsg(level, id_,
						this.iap.getProcess().getName());
				this.iap.SendMsg(this.iap.current_father.pi.IP,
						this.iap.current_father.pi.port,
						this.iap.current_father.pi.name, msg);
				System.out.println("Ordinary "
						+ this.iap.getProcess().getName() + " ack new father "
						+ this.iap.current_father.pi.name);
			}
		}

	}

}
