package nl.tudelft.da.lab3.msghandler;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.da.lab3.common.Utils;
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
		if (this.iap.Candidate) { // Candidate
			if (id_ == this.iap.getProcess().getId() && !this.iap.Captured) {
				this.iap.level++;
				List delList = new ArrayList();
				for (Object api : this.iap.AlgorUntraversedLinkList) {
					AfekGafniProcessItem link = (AfekGafniProcessItem) api;
					if (link.id == this.iap.current_link.id) {
						delList.add(link);
						// Candidate ACK

						Utils.record("Candidate "
								+ this.iap.getProcess().getId() + " "
								+ this.iap.getProcess().getName() + " (level: "
								+ this.iap.level
								+ ") received an ACK-Captured message from "
								+ link.pi.ID + " " + link.pi.name + " (level:"
								+ cam.level + ")");
						// System.out.println("Candidate "+this.iap.getProcess().getName()+" captured current link to "+link.pi.name+"  current level: "+this.iap.level+" current set number: "+(this.iap.AlgorUntraversedLinkList.size()-1));
					}
				}
				this.iap.AlgorUntraversedLinkList.removeAll(delList);
				this.iap.Link_killed = true;
			} else {
				if (level < this.iap.level
						|| (level == this.iap.level && id_ < this.iap
								.getProcess().getId())) {

					Utils.record("Candidate "
							+ this.iap.getProcess().getId()
							+ " "
							+ this.iap.getProcess().getName()
							+ " (level: "
							+ this.iap.level
							+ ") received an INVALID Capture-Attempt message from "
							+ cam.id + " " + cam.sender + " (level: "
							+ cam.level + ") ");
					return;
				} else {
					CaptureAttempMsg msg = new CaptureAttempMsg(level, id_,
							this.iap.getProcess().getName());
					Utils.record("ID changed event: original "
							+ this.iap.getProcess().getId() + " "
							+ this.iap.getProcess().getName() + " to " + id_
							+ ".");
					this.iap.getProcess().setId(id_);
					this.iap.level = level;
					for (Object api : this.iap.AlgorProcessItemList) {
						AfekGafniProcessItem process = (AfekGafniProcessItem) api;
						if (process.id == id_) {
							this.iap.SendMsg(process.pi.IP, process.pi.port,
									process.pi.name, msg);

							
							Utils.record("\n--------------------------------------------------------------\n"
									+ "Candidate "
									+ this.iap.getProcess().getId()
									+ " "
									+ this.iap.getProcess().getName()
									+ " (level: "
									+ this.iap.level
									+ ") received a VALID Capture-Attempt message from "
									+ cam.id
									+ " "
									+ cam.sender
									+ " (level: "
									+ cam.level
									+ ") "
									+ "\n--------------------------------------------------------------\n");
							break;
						}
					}
					this.iap.Captured = true;
					return;
				}
			}
		} else { // Ordinary
			if (level < this.iap.level
					|| (level == this.iap.level && id_ < this.iap.getProcess()
							.getId())) {
				Utils.record("Ordinary " + this.iap.getProcess().getId() + " "
						+ this.iap.getProcess().getName() + " (level: "
						+ this.iap.level
						+ ") received an INVALID Capture-Attempt message from "
						+ cam.id + " " + cam.sender + " (level: " + cam.level
						+ ") ");
				return;
			} else if (level > this.iap.level
					|| (level == this.iap.level && id_ > this.iap.getProcess()
							.getId())) {

				Utils.record("\n--------------------------------------------------------------\n"
						+ "Ordinary "
						+ this.iap.getProcess().getId()
						+ " "
						+ this.iap.getProcess().getName()
						+ " (level: "
						+ this.iap.level
						+ ") received a VALID Capture-Attempt message from "
						+ cam.id
						+ "(level: "
						+ cam.level
						+ ") "
						+ "\n--------------------------------------------------------------\n");

				for (Object api : this.iap.AlgorProcessItemList) {
					AfekGafniProcessItem process = (AfekGafniProcessItem) api;
					if (process.id == id_) {
						this.iap.potential_father = process;
						Utils.record("ID change event: original "
								+ this.iap.getProcess().getId() + " "
								+ this.iap.getProcess().getName() + " to "
								+ id_ + ".");
						this.iap.getProcess().setId(id_);

						this.iap.level = level;
						if (this.iap.current_father == null) {
							this.iap.current_father = this.iap.potential_father;

							Utils.record("Ordinary "
									+ this.iap.getProcess().getId() + " "
									+ this.iap.getProcess().getName()
									+ " (level: " + this.iap.level
									+ ") send an ACK-Captured message to "
									+ process.id + " " + process.pi.name
									+ " (level: " + level + ") ");

						} else {
							Utils.record("Ordinary "
									+ this.iap.getProcess().getId() + " "
									+ this.iap.getProcess().getName()
									+ " (level: " + this.iap.level
									+ ") send a Kill-Attempt message to "
									+ this.iap.current_father.id + " "
									+ this.iap.current_father.pi.name);
						}

						CaptureAttempMsg msg = new CaptureAttempMsg(level, id_,
								this.iap.getProcess().getName());
						this.iap.SendMsg(this.iap.current_father.pi.IP,
								this.iap.current_father.pi.port,
								this.iap.current_father.pi.name, msg);

						return;
					}
				}
			} else {

				Utils.record("Ordinary " + this.iap.getProcess().getId() + " "
						+ this.iap.getProcess().getName() + " (level: "
						+ this.iap.level
						+ ") receives an ACK-Killed message from "
						+ this.iap.current_father.id + " "
						+ this.iap.current_father.pi.name);

				this.iap.current_father = this.iap.potential_father;
				CaptureAttempMsg msg = new CaptureAttempMsg(level, id_,
						this.iap.getProcess().getName());
				this.iap.SendMsg(this.iap.current_father.pi.IP,
						this.iap.current_father.pi.port,
						this.iap.current_father.pi.name, msg);

				Utils.record("Ordinary " + this.iap.getProcess().getId() + " "
						+ this.iap.getProcess().getName() + " (level: "
						+ this.iap.level
						+ ") sends an ACK-Captured message to "
						+ this.iap.current_father.id + " "
						+ this.iap.current_father.pi.name);

			}
		}

	}

}
