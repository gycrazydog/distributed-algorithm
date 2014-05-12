package nl.tudelft.da.lab3.process;

/**
 * 
 */

import java.rmi.RemoteException;
import java.util.List;

import nl.tudelft.da.lab3.entity.IAlgorithmProcess;
import nl.tudelft.da.lab3.entity.IComponent;
import nl.tudelft.da.lab3.entity.IMsgHandler;
import nl.tudelft.da.lab3.messages.AbstractMsg;
import nl.tudelft.da.lab3.messages.CaptureAttempMsg;
import nl.tudelft.da.lab3.msghandler.CapturedAttemptMsgHandler;

/**
 * @author vincentgong
 * 
 */
public class AfekGafniProcess implements IAlgorithmProcess, IComponent {

	Process process;

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
	}

	@Override
	public void Receive(AbstractMsg abmsg) {

		// merge the process clock and abmsg clock

		System.out.println("Receive Msg. " + " Msg: " + abmsg.toString());

		// what's the type of message?
		if (abmsg instanceof CaptureAttempMsg) {
			ReceivingCaputredAttemptMsg(abmsg);
		}
		// else if(abmsg instanceof Inquire){
		// ReceivingInquire(abmsg);
		// }

		else {
			return;
		}

		System.out.println("Msg distributed. " + " Msg: " + abmsg.toString());
	}

	@Override
	public void ReceivingCaputredAttemptMsg(AbstractMsg abmsg) {
		// TODO Auto-generated method stub
		IMsgHandler imh = new CapturedAttemptMsgHandler(this, abmsg);
		Thread t = new Thread(imh);
		t.start();
	}

	@Override
	public void SendMsg(String ip, int port, String name, AbstractMsg msg) {
		this.process.SendMsg(ip, port, name, msg);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sender getSender() {
		// TODO Auto-generated method stub
		return this.sender;
	}

}
