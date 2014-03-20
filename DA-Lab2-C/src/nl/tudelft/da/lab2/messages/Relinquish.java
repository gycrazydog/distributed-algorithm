/**
 * 
 */
package nl.tudelft.da.lab2.messages;

import nl.tudelft.da.lab2.process.SClock;

/**
 * @author vincentgong
 *
 */
public class Relinquish extends AbstractMsg{
	public Relinquish(String content, String senderProcess, int curClock){
		this.clock = new SClock(curClock);
		this.content = content;
		this.sender = senderProcess;
	}
}
