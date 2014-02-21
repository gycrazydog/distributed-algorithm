/**
 * 
 */
package com.vincentgong.da.lab1.process;

/**
 * @author vincentgong
 * 
 */
public class SClock {

	private int clock = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int increase() {
		this.clock++;
		return currentClock();
	}

	public int currentClock() {
		return this.clock;
	}

	public void setClock(int newClock) {
		this.clock = newClock;
	}

	public void mergeClock(int newClock) {
		this.clock = newClock > this.clock ? newClock : this.clock;
	}
}
