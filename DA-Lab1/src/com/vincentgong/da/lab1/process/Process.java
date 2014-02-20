/**
 * 
 */
package com.vincentgong.da.lab1.process;

import java.util.PriorityQueue;

import com.vincentgong.da.lab1.message.Msg;

/**
 * @author vincentgong
 * 
 */
public class Process {
	
	private SClock clock;
	private PriorityQueue msgQ;
	
	public void Process() {
		this.clock = new SClock();
		this.msgQ = new PriorityQueue();
	}

	public void Receive(Msg msg){
		
	}
	
	public void SendMsg(String ip, int port, Msg msg){
		
	}
	
	
}
