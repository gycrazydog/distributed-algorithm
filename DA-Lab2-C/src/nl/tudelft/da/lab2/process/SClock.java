/**
 * 
 */
package nl.tudelft.da.lab2.process;

import java.io.Serializable;

/**
 * @author vincentgong
 * @author canrangou
 */
public class SClock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private int clock;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public SClock(int c) {
		this.clock = c;
	}

	public SClock() {
		this.clock = 0;
	}

	public SClock increase() {
		this.clock++;
		return this;
	}

	public int currentClock() {
		return this.clock;
	}

	public void setClock(int newClock) {
		this.clock = newClock;
	}

	public void mergeClock(int newClock) {
		this.clock = newClock > this.clock ? newClock + 1 : this.clock + 1;
	}

	@Override
	public String toString() {
		return Integer.toString(this.currentClock());
	}
}
