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
import java.util.Random;

import nl.tudelft.da.lab1.commom.IProcessInterface;
import nl.tudelft.da.lab1.commom.Logger;
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
	private ProcessItem pi;

	public static void main(String[] args) throws RemoteException {
		String id = args[0];
		String ip = args[1];
		int port = Integer.parseInt(args[2]);

		Process pr = new Process(id, ip, port);
		pr.regProcessWithNewRegistry(id, port);

		Logger.getInstance().log("");
		Logger.getInstance().log(
				"Process Start: " + id + " IP: " + ip + " Port : " + port);
		Logger.getInstance().logEmptyLine();

		// pr.broadcast(pr.randomMsg(pr.clock));

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
		Msg msg = new Msg("message " + sc.currentClock() + "!", this.ip,
				this.port, this.id, sc.increase());
		return msg;
	}

	public Process(String ProcessID, String ip, int port)
			throws RemoteException {
		this.id = ProcessID;
		this.ip = ip;
		this.port = port;
		this.clock = new SClock();
		Comparator<Msg> OrderIsdn = new Comparator<Msg>() {
			public int compare(Msg m1, Msg m2) {
				int clock1 = m1.clock.currentClock();
				int clock2 = m2.clock.currentClock();
				if (clock1 == clock2) {
					return m1.sender.id.compareTo(m2.sender.id);
				} else {
					return clock1 < clock2 ? -1 : 1;
				}
			}
		};
		this.msgQ = new PriorityQueue<Msg>(11, OrderIsdn);
		this.processesList = Utils.getInstance().getProcessesList();
		this.pi = new ProcessItem(this.ip, this.port, this.id);
	}

	public void Receive(AbstractMsg absmsg) {
		this.clock.increase();
		this.clock.mergeClock(absmsg.clock.currentClock() + 1);
		if (absmsg instanceof Msg) {
			boolean msgArrived = false;
			Msg msg = (Msg) absmsg;

			for (Msg message : this.msgQ) {
				if (message.clock.currentClock() == msg.clock.currentClock()
						&& message.sender.equals(msg.sender)) {
					message.content = msg.content;
					msgArrived = true;
					break;
				}
			}

			if (!msgArrived) {
				msg.AckQueue = new HashMap<String, Boolean>();
				this.msgQ.add(msg);
			}
			Ack ack = new Ack(this.pi, this.clock, msg.sender, msg.clock);
			System.out.println("Receive Msg: " + msg.toString());
			Logger.getInstance().log(
					"At " + this.clock.currentClock() + " Receive Msg: "
							+ msg.toString());
			this.broadcast(ack);

		} else if (absmsg instanceof Ack) {
			boolean msgArrived = false;
			Ack ack = (Ack) absmsg;
			for (Msg message : this.msgQ) {
				if (message.clock.currentClock() == ack.msgClock.currentClock()
						&& message.sender.equals(ack.msgSender)) {
					message.AckQueue.put(ack.sender.id, true);
					msgArrived = true;
					break;
				}
			}
			if (!msgArrived) {
				Msg tempMsg = new Msg("", ack.msgSender, ack.msgClock);
				tempMsg.AckQueue = new HashMap<String, Boolean>();
				tempMsg.AckQueue.put(ack.sender.id, true);
				this.msgQ.add(tempMsg);
			}
			System.out.println("Receive ACK: " + ack.toString());
			this.checkDeliver();
		}

	}

	public void SendMsg(String ip, int port, String name, AbstractMsg msg) {
		this.clock.increase();
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(ip, port);
			IProcessInterface process = (IProcessInterface) registry
					.lookup(name);
			process.post(msg);
		} catch (RemoteException | NotBoundException e) {
			System.out.println("Msg Send Failed!!!");
			e.printStackTrace();
		}
	}

	public boolean checkDeliver() {
		Msg topMsg = this.msgQ.peek();
		if (!this.msgQ.isEmpty())
			System.out.println("check deliver!!" + " topMsg ackqueue size is "
					+ topMsg.AckQueue.size());
		if (!this.msgQ.isEmpty()
				&& topMsg.AckQueue.size() == this.processesList.size()
				&& !topMsg.content.equals("")) {
			System.out.println("delivered msg " + topMsg + " !!!");
			Logger.getInstance().log(
					"At " + this.clock.currentClock() + " Delivered Msg: "
							+ topMsg.toString());
			this.msgQ.poll();
			return true;
		}
		return false;
	}

	public void broadcast(AbstractMsg msg) {
		Random r = new Random(msg.clock.currentClock());
		Iterator it = this.processesList.iterator();
		while (it.hasNext()) {
			ProcessItem pi = (ProcessItem) it.next();
			try {
				Thread.sleep(Math.abs(r.nextInt()) % 3000);
				this.SendMsg(pi.IP, pi.port, pi.id, msg);
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

	public String getID() {
		return this.id;
	}
}
