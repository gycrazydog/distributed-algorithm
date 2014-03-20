/**
 * 
 */
package nl.tudelft.da.lab2.messages;

import nl.tudelft.da.lab2.process.SClock;

/**
 * @author vincentgong
 *
 */
public class Release extends AbstractMsg {
	public Release(String content, String senderProcess, int curClock){
		this.clock = new SClock(curClock);
		this.content = content;
		this.sender = senderProcess;
	}
}
