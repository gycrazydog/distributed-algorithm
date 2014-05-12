package nl.tudelft.da.lab3.process;

/**
 * 
 */

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import nl.tudelft.da.lab3.common.Logger;
import nl.tudelft.da.lab3.entity.IComponent;
import nl.tudelft.da.lab3.entity.IProcessInterface;
import nl.tudelft.da.lab3.entity.ProcessItem;
import nl.tudelft.da.lab3.messages.AbstractMsg;

/**
 * @author vincentgong
 * 
 */
public class Process extends UnicastRemoteObject implements IProcessInterface,
		IComponent {

	public PriorityQueue reqQ;

	private String name;
	private int id;
	private String ip;
	private int port;
	private List processesItemList;

	public static void main(String[] args) throws RemoteException, Exception {
		// String id = args[0];
		// String ip = args[1];
		// int port = Integer.parseInt(args[2]);

		// String name = "A";
		// int id = 1;
		// String ip = "127.0.0.1";
		// int port = 9527;
		//
		// Process pr = new Process(name, id, ip, port);
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

	public Process(String ProcessName, int processID, String ip, int port)
			throws RemoteException {
		this.id = processID;
		this.name = ProcessName;
		this.ip = ip;
		this.port = port;

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

	public void broadcast(AbstractMsg msg) {
		Random r = new Random();
		Iterator it = this.processesItemList.iterator();
		while (it.hasNext()) {
			ProcessItem pi = (ProcessItem) it.next();
			try {
				Thread.sleep(Math.abs(r.nextInt()) % 3000);
				this.SendMsg(pi.IP, pi.port, pi.name, msg);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void post(AbstractMsg msg) {
		// TODO Auto-generated method stub
		this.Receive(msg);
	}

	private void Receive(AbstractMsg msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void CaptureAttempt(AbstractMsg abmsg) {
		// TODO Auto-generated method stub
		
	}

}
