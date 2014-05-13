package nl.tudelft.da.lab3.process;

/**
 * 
 */

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import nl.tudelft.da.lab3.entity.IAlgorithmProcess;
import nl.tudelft.da.lab3.entity.IComponent;
import nl.tudelft.da.lab3.entity.IMsgHandler;
import nl.tudelft.da.lab3.entity.ProcessItem;
import nl.tudelft.da.lab3.messages.AbstractMsg;
import nl.tudelft.da.lab3.messages.CaptureAttempMsg;
import nl.tudelft.da.lab3.messages.TestMessage;
import nl.tudelft.da.lab3.msghandler.CapturedAttemptMsgHandler;
import nl.tudelft.da.lab3.msghandler.TestMessageHandler;

/**
 * @author vincentgong
 * 
 */
public class AfekGafniProcess implements IAlgorithmProcess, IComponent {

	private Process process;
	public List AlgorProcessItemList;
	public List AlgorUntraversedLinkList;

	// variables for the algorithm
	private List reqSetList;// List of ProcessItems which are in the same
							// request set with the current process. e.g. All
							// the processes in the request set 3 and 6.
	public Sender sender;

	// status variables
	public int num_of_grants;// the number of grants got from the resource set

	// public boolean relinquished;
	// private boolean inquiring;// whether this process has given the inquire
	// to other nodes
	// public int resourceSetProcessNumber;// |R|, the number of processes in
	// the Resource Set of the current process.
	// public boolean postponed;// whether this process has been postponed, or
	// has received the postponed message

	// TODO adding all variables

	public static void main(String[] args) throws RemoteException, Exception {
		// String id = args[0];
		// String ip = args[1];
		// int port = Integer.parseInt(args[2]);

		// String name = "A";
		// int id = 1;
		// String ip = "127.0.0.1";
		// int port = 9527;
		//
		// AfekGafniProcess pr = new AfekGafniProcess(name, id, ip, port);
		// // now the Process clock = {0,0,0} by default
		//
		// Utils.regProcessWithNewRegistry(name, port, pr);
		// // pr.sender1.run();//start the multiple-threads
		// String line = "Process Start: " + name + " id:" + id + " IP: " + ip
		// + " Port : " + port;
		// System.out.println(line);
		// Logger.getInstance().log(line);
		//
		// Thread.sleep(2000);

	}

	public AfekGafniProcess(Process process) throws RemoteException {

		this.process = process;

		// initial the variables for the algorithm
		// this.postponed = false;
		// this.currentGrant = null;
		// this.relinquished = false;

		this.sender = new Sender(this);
		this.AlgorProcessItemList = new LinkedList();
		this.AlgorUntraversedLinkList = new LinkedList();
		this.initialAlgorProcessItem();
		this.initialAlgorUntraversedLinkList();
	}

	public void initialAlgorUntraversedLinkList() {
		// TODO Auto-generated method stub
		// build untraversedLinkList based on AlgorProcessItemList
		Iterator it = this.AlgorProcessItemList.iterator();
		while (it.hasNext()) {
			AfekGafniProcessItem api = (AfekGafniProcessItem) it.next();
			if (api.pi.name.equals(this.process.getName())) {
				continue;
			}
			AlgorUntraversedLinkList.add(api);
		}
	}

	public void initialAlgorProcessItem() {
		// TODO Auto-generated method stub
		// build AlgorProcessItems and put them into the AlgorProcessItemList
		Iterator it = this.process.getProcessesItemList().iterator();
		while (it.hasNext()) {
			ProcessItem pi = (ProcessItem) it.next();
			AfekGafniProcessItem api = new AfekGafniProcessItem(pi);
			AlgorProcessItemList.add(api);
		}
	}

	@Override
	public void Receive(AbstractMsg abmsg) {

		// System.out.println("Receive Msg. " + " Msg: " + abmsg.toString());

		// what's the type of message?
		if (abmsg instanceof TestMessage) {
			HandleTheMessage(abmsg, new TestMessageHandler(this, abmsg));
		} else if (abmsg instanceof CaptureAttempMsg) {
			HandleTheMessage(abmsg, new CapturedAttemptMsgHandler(this, abmsg));
		}
		// else if(abmsg instanceof Inquire){
		// HandleTheMessage(abmsg, new CapturedAttemptMsgHandler(this, abmsg));
		// }

		else {
			return;
		}

		// System.out.println("Msg distributed. " + " Msg: " +
		// abmsg.toString());
	}


	@Override
	public void HandleTheMessage(AbstractMsg abmsg, IMsgHandler imh) {
		Thread t = new Thread(imh);
		t.start();
	}

	@Override
	public void SendMsg(String ip, int port, String name, AbstractMsg msg) {
		this.process.SendMsg(ip, port, name, msg);
	}

	@Override
	public Sender getSender() {
		// TODO Auto-generated method stub
		return this.sender;
	}

	@Override
	public Process getProcess() {
		// TODO Auto-generated method stub
		return this.process;
	}

	public List getAlgorProcessItemList() {
		return AlgorProcessItemList;
	}

	public void setAlgorProcessItemList(List algorProcessItemList) {
		AlgorProcessItemList = algorProcessItemList;
	}

	public List getAlgorUntraversedLinkList() {
		return AlgorUntraversedLinkList;
	}

	public void setAlgorUntraversedLinkList(List algorUntraversedLinkList) {
		AlgorUntraversedLinkList = algorUntraversedLinkList;
	}

}
