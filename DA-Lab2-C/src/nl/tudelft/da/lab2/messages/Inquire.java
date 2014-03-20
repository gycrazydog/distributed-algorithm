/**
 * 
 */
package nl.tudelft.da.lab2.messages;

import nl.tudelft.da.lab2.process.SClock;

/**
 * @author vincentgong
 *
 */
public class Inquire extends AbstractMsg {
	public Inquire(String content, String senderProcess, int curClock){
		this.clock = new SClock(curClock);
		this.content = content;
		this.sender = senderProcess;
	}
}
