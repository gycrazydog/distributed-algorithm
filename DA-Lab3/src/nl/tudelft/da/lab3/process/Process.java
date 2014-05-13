package nl.tudelft.da.lab3.process;

/**
 * 
 */

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import nl.tudelft.da.lab3.common.Logger;
import nl.tudelft.da.lab3.entity.IAlgorithmProcess;
import nl.tudelft.da.lab3.entity.IProcessInterface;
import nl.tudelft.da.lab3.entity.ProcessItem;
import nl.tudelft.da.lab3.messages.AbstractMsg;

/**
 * @author vincentgong
 * 
 */
public class Process extends UnicastRemoteObject implements IProcessInterface
		 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private int id;
	private String ip;
	private int port;
	private List processesItemList;
	private IAlgorithmProcess iap;

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

	public void SendMsg(String ip, int port, String receiverName, AbstractMsg msg) {
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(ip, port);
			IProcessInterface process = (IProcessInterface) registry
					.lookup(receiverName);
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

	//call back
	private void Receive(AbstractMsg msg) {
		// TODO Auto-generated method stub
		this.iap.Receive(msg);
	}

	public List getProcessesItemList() {
		return processesItemList;
	}

	public void setProcessesItemList(List processesItemList) {
		this.processesItemList = processesItemList;
	}

	public IAlgorithmProcess getIap() {
		return iap;
	}

	public void setIap(IAlgorithmProcess iap) {
		this.iap = iap;
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
}
