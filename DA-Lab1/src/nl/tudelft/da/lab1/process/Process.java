/**
 * 
 */
package nl.tudelft.da.lab1.process;

import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import nl.tudelft.da.lab1.commom.IProcessInterface;
import nl.tudelft.da.lab1.commom.ProcessItem;
import nl.tudelft.da.lab1.commom.Utils;
import nl.tudelft.da.lab1.message.AbstractMsg;
import nl.tudelft.da.lab1.message.Ack;
import nl.tudelft.da.lab1.message.Msg;

/**
 * @author vincentgong
 * 
 */
public class Process extends UnicastRemoteObject implements IProcessInterface {

	private SClock clock;
	private PriorityQueue<Msg> msgQ;
	private List processesList;
	private String id;
	private String ip;
	private int port;
	
	public static void main(String[] args) throws RemoteException {
		String id = args[0];
		String ip = args[1];
		int port = Integer.parseInt(args[2]); 

		Process pr = new Process(id, ip, port);
		pr.regProcessWithNewRegistry(id, port);

		while (true) {
			try {
				Thread.sleep(1500);
				pr.broadcast(pr.randomMsg(pr.clock));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private Msg randomMsg(SClock sc) {
		// TODO Auto-generated method stub
		Msg msg = new Msg("message " + sc.currentClock() + "!" , this.ip, this.port, this.id, sc.increase());
		return msg;
	}

	public Process(String ProcessID, String ip, int port)
			throws RemoteException {
		this.id = ProcessID;
		this.ip = ip;
		this.port= port;
		this.clock = new SClock();
		
	}

	public Process(String ProcessID) throws RemoteException {
		this.id = ProcessID;
		this.clock = new SClock();
		Comparator<Msg> OrderIsdn =  new Comparator<Msg>(){  
            public int compare(Msg m1, Msg m2) {  
            	int clock1 = m1.clock.currentClock();
            	int clock2 = m2.clock.currentClock();
                if(clock1==clock2){
                	return m1.sender.id.compareTo(m2.sender.id);
                }
                else
                {
                	return clock1<clock2? -1:1;
                }
            }
		};
		this.msgQ = new PriorityQueue<Msg>(11,OrderIsdn);
		this.processesList = Utils.getInstance().getProcessesList();
		
	}

	public void Receive(AbstractMsg absmsg) {
		this.clock.increase();
		absmsg.clock.increase();
		if (absmsg instanceof Msg) {
			Msg msg = (Msg) absmsg;
			msg.AckQueue = new HashMap<String,Boolean>();
			this.msgQ.add(msg);
			System.out.println(msg.toString());
		}
		else if(absmsg instanceof Ack){
			Ack ack = (Ack) absmsg;
			for(Msg message: this.msgQ){
				if(message.clock.currentClock()==ack.msgClock.currentClock()
				&& message.sender.equals(ack.msgSender)){
					message.AckQueue.put(ack.sender.id,true);
					break;
				}
			}
			System.out.println(ack.toString());
		}
		
	}

	public void SendMsg(String ip, int port, String name, Msg msg) {
		this.clock.increase();
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(ip, port);
			Process process = (Process) registry.lookup("Process");
			process.Receive(msg);
		} catch (RemoteException | NotBoundException e) {
			System.out.println("Msg Send Failed!!!");
			e.printStackTrace();
		}
	}

	public boolean deliver() {
		Msg topMsg = this.msgQ.peek();
		if(topMsg.AckQueue.size() == this.processesList.size())
			return true;
		return false;
	}

	public void broadcast(Msg msg) {
		Iterator it = this.processesList.iterator();
		while (it.hasNext()) {
			ProcessItem pi = (ProcessItem) it.next();
			this.SendMsg(pi.IP, pi.port, pi.id, msg);
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
	public String getID(){
		return this.id;
	}
}
