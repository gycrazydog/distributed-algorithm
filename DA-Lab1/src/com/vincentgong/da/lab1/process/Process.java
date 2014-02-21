/**
 * 
 */
package com.vincentgong.da.lab1.process;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import com.vincentgong.da.lab1.commom.ProcessItem;
import com.vincentgong.da.lab1.commom.Utils;
import com.vincentgong.da.lab1.message.Msg;

/**
 * @author vincentgong
 * 
 */
public class Process {

	private SClock clock;
	private PriorityQueue msgQ;
	private List processesList;

	public void Process() {
		this.clock = new SClock();
		this.msgQ = new PriorityQueue();
		this.processesList = Utils.getInstance().getProcessesList();
	}

	public void Receive(Msg msg) {
		
	}

	public void SendMsg(String ip, int port, String name, Msg msg) {
		
	}
	
	public void broadcast(Msg msg){
		Iterator it = this.processesList.iterator();
		while(it.hasNext()){
			ProcessItem pi = (ProcessItem) it.next();
			this.SendMsg(pi.IP, pi.port, pi.name, msg);
		}
	}

}
