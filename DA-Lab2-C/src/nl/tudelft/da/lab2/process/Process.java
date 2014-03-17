/**
 * 
 */
package nl.tudelft.da.lab2.process;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import nl.tudelft.da.lab2.commom.Logger;
import nl.tudelft.da.lab2.commom.Utils;
import nl.tudelft.da.lab2.messages.AbstractMsg;
import nl.tudelft.da.lab2.messages.Grant;
import nl.tudelft.da.lab2.messages.Inquire;
import nl.tudelft.da.lab2.messages.Postponed;
import nl.tudelft.da.lab2.messages.Release;
import nl.tudelft.da.lab2.messages.Relinquish;
import nl.tudelft.da.lab2.messages.Request;
import nl.tudelft.da.lab2.msghandler.IMsgHandler;
import nl.tudelft.da.lab2.msghandler.PostponedHandler;

/**
 * @author vincentgong
 * 
 */
public class Process extends UnicastRemoteObject implements IProcessInterface,
		IComponent {

	private SClock clock;
	private PriorityQueue reqQ;
	
	private String name;
	private int id;
	private String ip;
	private int port;
	
	//variables for the algorithm
	private List reqSetList;

	//status variables
	private boolean postponed;
	private boolean granted;
	//TODO adding all variables

	public static void main(String[] args) throws RemoteException, Exception {
		// String id = args[0];
		// String ip = args[1];
		// int port = Integer.parseInt(args[2]);

		String name = "A";
		int id = 1;
		String ip = "127.0.0.1";
		int port = 9527;

		Process pr = new Process(name, id, ip, port);
		// now the Process clock = {0,0,0} by default

		Utils.regProcessWithNewRegistry(name, port, pr);
		// pr.sender1.run();//start the multiple-threads
		String line = "Process Start: " + name + " id:" + id + " IP: " + ip
				+ " Port : " + port;
		System.out.println(line);
		Logger.getInstance().log(line);

		Thread.sleep(2000);


		// a to b
//		Msg msg = new Msg("msg from A to B", ip, port, name, new VClock(
//				pr.clock.clock), 1);
//		pr.SendMsg(ip, port, name, msg);
//		
//		Thread.sleep(1000);
//		Msg msg2 = new Msg("msg from A to B", ip, port, name, new VClock(
//				pr.clock.clock), 2);
//		pr.SendMsg(ip, port, name, msg2);
	}

	public Process(String ProcessName, int processID, String ip, int port)
			throws RemoteException {
		this.id = processID;
		this.name = ProcessName;
		this.ip = ip;
		this.port = port;
		this.clock = new SClock();

		Comparator OrderIsdn = new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		this.reqQ = new PriorityQueue(11, OrderIsdn);

		this.reqSetList = new ArrayList();
		
		//initial the variables for the algorithm
		this.postponed = false;
		this.granted = false;
	}

	public void Receive(AbstractMsg abmsg) {
		
		//merge the process clock and abmsg clock
		this.clock.mergeClock(abmsg.clock.currentClock());
		
		System.out.println("Receive Msg. Process Clock" + this.clock.toString()
				+ " Msg: " + abmsg.toString());
		
//		IMsgHandler mh = new AbstractMsgHandler(this, abmsg);
		// what is the type of message?
		if(abmsg instanceof Grant){
			
		}else if(abmsg instanceof Inquire){
			
		}else if(abmsg instanceof Postponed){
//			mh = new PostponedHandler(this, abmsg);
			ReceivingPostponed(abmsg);
		}else if(abmsg instanceof Release){
			
		}else if(abmsg instanceof Relinquish){
			
		}else if(abmsg instanceof Request){
			
		}else{
			return;
		}
		
//		Thread t = new Thread(mh);
//		t.start();
		
		System.out.println("Msg distributed. Process Clock" + this.clock.toString()
				+ " Msg: " + abmsg.toString());
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

	public void broadcast(AbstractMsg msg) {

	}

	@Override
	public void post(AbstractMsg msg) {
		// TODO Auto-generated method stub
		this.Receive(msg);
	}
	
/*
//	public void regProcess(Registry registry, String name) {
//		try {
//			System.setSecurityManager(new RMISecurityManager());
//			registry.rebind(name, this);
//
//			System.out.println("Server is ready: " + name);
//		} catch (Exception e) {
//			System.out.println(name + " server failed: " + e);
//		}
//	}
//
//	public void regProcessWithNewRegistry(String name, int port) {
//		try {
//			Registry registry = LocateRegistry.createRegistry(port);
//			this.regProcess(registry, name);
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void regProcessWithRegistry(String name, int port) {
//		Registry registry;
//		try {
//			registry = LocateRegistry.getRegistry(port);
//			this.regProcess(registry, name);
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
*/
	
	@Override
	public PriorityQueue getReqQ() {
		// TODO Auto-generated method stub
		return this.reqQ;
	}


	@Override
	public List getRequestSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void MulticastingRequest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ReceivingRequest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ReceivingGrant() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ReceivingInquire() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ReceivingRelinquish() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ReceivingRelease() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ReceivingPostponed(AbstractMsg abmsg) {
		// TODO Auto-generated method stub
		IMsgHandler imh = new PostponedHandler(this, abmsg);
		Thread t = new Thread(imh);
		t.start();
	}

	@Override
	public SClock getSClock() {
		// TODO Auto-generated method stub
		return this.clock;
	}
	
	public SClock getClock() {
		return clock;
	}

	public void setClock(SClock clock) {
		this.clock = clock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public List getReqSetList() {
		return reqSetList;
	}

	public void setReqSetList(List reqSetList) {
		this.reqSetList = reqSetList;
	}
}
