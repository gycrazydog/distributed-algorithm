/**
 * 
 */
package com.vincentgong.da.lab1.commom;

/**
 * @author vincentgong
 * 
 */
public class ProcessItem {
	
	public String IP;
	public int port = 3323;
	public String name;
	
	public ProcessItem(String IP, int port, String name) {
		// TODO Auto-generated constructor stub
		this.IP = IP;
		this.port = port;
		this.name = name;
	}
	
	public String toString(){
		String str = "Process:" + this.name + " IP: " + this.IP + " Port: " + this.port;
		return str;
	}

}
