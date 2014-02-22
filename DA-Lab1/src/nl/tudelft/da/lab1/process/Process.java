/**
 * 
 */
package nl.tudelft.da.lab1.process;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import nl.tudelft.da.lab1.commom.IProcessInterface;
import nl.tudelft.da.lab1.commom.ProcessItem;
import nl.tudelft.da.lab1.commom.Utils;
import nl.tudelft.da.lab1.message.Msg;

/**
 * @author vincentgong
 * 
 */
public class Process extends UnicastRemoteObject implements IProcessInterface {

	private SClock clock;
	private PriorityQueue msgQ;
	private List processesList;

	public static void main(String[] args) throws RemoteException {
		Process pr = new Process();
		pr.regProcessWithNewRegistry("TEST", 3233);
	}

	public Process() throws RemoteException {
		this.clock = new SClock();
		this.msgQ = new PriorityQueue();
		this.processesList = Utils.getInstance().getProcessesList();
	}

	public void Receive(Msg msg) {
		msg.clock.increase();
		System.out.println(msg.toString());
	}

	public void SendMsg(String ip, int port, String name, Msg msg) {
		this.clock.increase();
	}

	public boolean deliver() {

		return false;
	}

	public void broadcast(Msg msg) {
		Iterator it = this.processesList.iterator();
		while (it.hasNext()) {
			ProcessItem pi = (ProcessItem) it.next();
			this.SendMsg(pi.IP, pi.port, pi.name, msg);
		}
	}

	@Override
	public void post(Msg msg) {
		// TODO Auto-generated method stub
		this.Receive(msg);
	}

	public void regProcess(Registry registry, String name) {
		try {
			System.setSecurityManager(new RMISecurityManager());
			registry.rebind(name, this);

			System.out.println("Server is ready: " + name);
		} catch (Exception e) {
			System.out.println(name + " server failed: " + e);
		}
	}

	public void regProcessWithNewRegistry(String name, int port) {
		try {
			Registry registry = LocateRegistry.createRegistry(port);
			this.regProcess(registry, name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Override
//	public void post(Msg msg) throws RemoteException {
//		// TODO Auto-generated method stub
//		System.out.println("inpost");
//	}

//	@Override
//	public void postString(String s) {
//		// TODO Auto-generated method stub
//		System.out.println(s);
//	}

//	@Override
//	public int oper(int a, int b) {
//		// TODO Auto-generated method stub
//		return a+b;
//	}

}
