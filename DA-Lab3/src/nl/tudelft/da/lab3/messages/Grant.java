/**
 * 
 */
package nl.tudelft.da.lab3.messages;

import nl.tudelft.da.lab2.process.SClock;

/**
 * @author vincentgong
 *
 */
public class Grant extends AbstractMsg {
	public Grant(String content, String senderProcess, int curClock){
		this.clock = new SClock(curClock);
		this.content = content;
		this.sender = senderProcess;
	}
}
