package nl.tudelft.da.lab3.process;

/**
 * 
 */

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import nl.tudelft.da.lab3.common.Logger;
import nl.tudelft.da.lab3.entity.IAlgorithmProcess;
import nl.tudelft.da.lab3.entity.IProcessInterface;
import nl.tudelft.da.lab3.messages.AbstractMsg;
import nl.tudelft.da.lab3.messages.CaptureAttempMsg;

/**
 * @author vincentgong
 * 
 */
public class AfekGafniProcess implements IAlgorithmProcess {

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

	public void Receive(AbstractMsg abmsg) {

		// merge the process clock and abmsg clock

		System.out.println("Receive Msg. " + " Msg: " + abmsg.toString());

		// what is the type of message?
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

	private void ReceivingCaputredAttemptMsg(AbstractMsg abmsg) {
		// TODO Auto-generated method stub

	}

	public void SendMsg(String ip, int port, String name, AbstractMsg msg) {
		// this.clock.increase();
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(ip, port);
			IProcessInterface process = (IProcessInterface) registry
					.lookup(name);
			process.post(msg);
			System.out.println("Msg sent sucessfully. " + msg.toString());
			Logger.getInstance().log("Msg sent sucessfully. " + msg.toString());

		} catch (RemoteException | NotBoundException e) {
			System.out.println("Msg Sent Failed!!! " + msg.toString());
			e.printStackTrace();
		}
	}

	public boolean checkDeliver() {

		return false;
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
		return null;
	}

}
