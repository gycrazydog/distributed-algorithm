/**
 * 
 */
package nl.tudelft.da.lab2.process;

import java.io.Serializable;

/**
 * @author vincentgong
 *
 */
public class VClock implements Serializable{
	private static final long serialVersionUID = 3L;
	public int[] clock = new int[3];
	
	public VClock(int[] clock){
		this.clock[0] = clock[0];
		this.clock[1] = clock[1];
		this.clock[2] = clock[2];
	}
	
	public VClock(){
		this.clock[0] = 0;
		this.clock[1] = 0;
		this.clock[2] = 0;
	}
	
	public String toString() {
		String line = "@" + this.clock[0] + "," + this.clock[1] + "," + this.clock[2];
		return line;
	}
}
