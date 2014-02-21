/**
 * 
 */
package com.vincentgong.da.lab1.process;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import com.vincentgong.da.lab1.commom.IProcessInterface;
import com.vincentgong.da.lab1.commom.ProcessItem;
import com.vincentgong.da.lab1.commom.Utils;
import com.vincentgong.da.lab1.message.Msg;

/**
 * @author vincentgong
 * 
 */
public class Process implements IProcessInterface{

	private SClock clock;
	private PriorityQueue msgQ;
	private List processesList;
	
	public static void main(){
		Process pr = new Process();
		pr.regProcessWithNewRegistry("TEST", 3233);
	}

	public void Process() {
		this.clock = new SClock();
		this.msgQ = new PriorityQueue();
		this.processesList = Utils.getInstance().getProcessesList();
	}

	public void Receive(Msg msg) {
		msg.clock.increase();
	}

	public void SendMsg(String ip, int port, String name, Msg msg) {
		this.clock.increase();
	}
	
	public boolean deliver(){
		
		return false;
	}
	
	public void broadcast(Msg msg){
		Iterator it = this.processesList.iterator();
		while(it.hasNext()){
			ProcessItem pi = (ProcessItem) it.next();
			this.SendMsg(pi.IP, pi.port, pi.name, msg);
		}
	}

	@Override
	public void post(Msg msg) {
		// TODO Auto-generated method stub
		this.Receive(msg);
	}
	
	public void regProcess(Registry registry, String name){
		   try {
			   System.setSecurityManager(new RMISecurityManager());
	            registry.rebind(name, this);
 
			   System.out.println("Server is ready: "+name);
			   }catch (Exception e) {
				   System.out.println(name + " server failed: " + e);
				}
	}
	
	public void regProcessWithNewRegistry(String name, int port){
		try {
			Registry registry = LocateRegistry.createRegistry(port);
			this.regProcess(registry, name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
