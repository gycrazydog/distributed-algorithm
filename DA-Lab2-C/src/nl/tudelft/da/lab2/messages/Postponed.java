/**
 * 
 */
package nl.tudelft.da.lab2.messages;
import nl.tudelft.da.lab2.process.Process;
import nl.tudelft.da.lab2.process.SClock;

/**
 * @author vincentgong
 *
 */
public class Postponed extends AbstractMsg {

	/**
	 * 
	 */
	public Postponed(String content, String senderProcess, int curClock){
		this.clock = new SClock(curClock);
		this.content = content;
		this.sender = senderProcess;
	}
	private static final long serialVersionUID = 1L;
	
	public String toString() {
		String line = "Clock: " + this.clock.toString() + " msg from "
				+ this.sender + " Msg: " + this.content;
		return line;
	}
}
