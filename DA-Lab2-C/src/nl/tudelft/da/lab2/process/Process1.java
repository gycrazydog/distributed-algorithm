///**
// * 
// */
//package nl.tudelft.da.lab2.process;
//
//import java.rmi.NotBoundException;
//import java.rmi.RMISecurityManager;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.rmi.server.UnicastRemoteObject;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.PriorityQueue;
//
//import nl.tudelft.da.lab2.commom.Logger;
//import nl.tudelft.da.lab2.messages.AbstractMsg;
//import nl.tudelft.da.lab2.messages.Msg;
//
///**
// * @author vincentgong
// * 
// */
//public class Process1 extends UnicastRemoteObject implements IProcessInterface,
//		IComponent {
//
//	private VClock clock;
//	private PriorityQueue reqQ;
//	private String name;
//	private int id;
//	private String ip;
//	private int port;
//	private List reqSetList;
//
//
//	public static void main(String[] args) throws RemoteException, Exception {
//		// String id = args[0];
//		// String ip = args[1];
//		// int port = Integer.parseInt(args[2]);
//
//		String name = "A";
//		int id = 1;
//		String ip = "127.0.0.1";
//		int port = 9527;
//
//		Process1 pr = new Process1(name, id, ip, port);
//		// now the Process clock = {0,0,0} by default
//
//		pr.regProcessWithNewRegistry(name, port);
//		// pr.sender1.run();//start the multiple-threads
//		String line = "Process Start: " + name + " id:" + id + " IP: " + ip
//				+ " Port : " + port;
//		System.out.println(line);
//		Logger.getInstance().log(line);
//
//		Thread.sleep(2000);
//
//		// Starting to broadcast msg to B and C, respectively.
//		// The Process clock is set to {1,0,0}
//		pr.clock.clock[0]++; // should be {1,0,0}
//		// The msg clock = Process clock = {1,0,0}
//
//		// a to b
//		Msg msg = new Msg("msg from A to B", ip, port, name, 0, 1);
//		pr.SendMsg(ip, port, name, msg);
//		
//		Thread.sleep(1000);
//		Msg msg2 = new Msg("msg from A to B", ip, port, name, 1, 2);
//		pr.SendMsg(ip, port, name, msg2);
//	}
//
//	public Process1(String ProcessName, int processID, String ip, int port)
//			throws RemoteException {
//		this.id = processID;
//		this.name = ProcessName;
//		this.ip = ip;
//		this.port = port;
//		this.clock = new VClock();
//
//		Comparator OrderIsdn = new Comparator() {
//
//			@Override
//			public int compare(Object o1, Object o2) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//		};
//		this.reqQ = new PriorityQueue(11, OrderIsdn);
//
//		this.reqSetList = new ArrayList();
//
//	}
//
//	public void Receive(AbstractMsg abmsg) {
//		System.out.println("Receive Msg. Process Clock" + this.clock.toString()
//				+ " Msg: " + abmsg.toString());
//		// this.sender1.handle(msg);
//		Msg msg = (Msg) abmsg;
//		Thread t1 = new Thread(new Sender());
//		t1.start();
//		Thread t2 = new Thread(new Sender());
//		t2.start();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Thread t3 = new Thread(new Sender());
//		t3.start();
//		
//	}
//
//	public void SendMsg(String ip, int port, String name, AbstractMsg msg) {
//		// this.clock.increase();
//		Registry registry;
//		try {
//			registry = LocateRegistry.getRegistry(ip, port);
//			IProcessInterface process = (IProcessInterface) registry
//					.lookup(name);
//
//			process.post(msg);
//			System.out.println("Msg sent sucessfully. " + msg.toString());
//			Logger.getInstance().log("Msg sent sucessfully. " + msg.toString());
//
//		} catch (RemoteException | NotBoundException e) {
//			System.out.println("Msg Sent Failed!!! " + msg.toString());
//			e.printStackTrace();
//		}
//	}
//
//	public boolean checkDeliver() {
//
//		return false;
//	}
//
//	public void broadcast(AbstractMsg msg) {
//
//	}
//
//	@Override
//	public void post(AbstractMsg msg) {
//		// TODO Auto-generated method stub
//		this.Receive(msg);
//	}
//
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
//
//	public String getName() {
//		return this.name;
//	}
//
//	public int getID() {
//		return this.id;
//	}
//
//	@Override
//	public PriorityQueue getReqQ() {
//		// TODO Auto-generated method stub
//		return this.reqQ;
//	}
//
////	@Override
////	public VClock getVClock() {
////		// TODO Auto-generated method stub
////		return this.clock;
////	}
//
//	@Override
//	public List getRequestSet() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void MulticastingRequest() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void ReceivingRequest() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void ReceivingGrant() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void ReceivingInquire() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void ReceivingRelinquish() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void ReceivingRelease() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void ReceivingPostponed(AbstractMsg abmsg) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public SClock getSClock() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
