/**
 * 
 */
package nl.tudelft.da.lab2.msghandler;

import nl.tudelft.da.lab2.messages.AbstractMsg;
import nl.tudelft.da.lab2.messages.Postponed;
import nl.tudelft.da.lab2.process.Process;

/**
 * @author vincentgong
 * 
 */
public class PostponedHandler implements IMsgHandler {

	/**
	 * 
	 */

	private Postponed msg;
	private Process pro;

	private static final long serialVersionUID = 1L;

	public PostponedHandler(Process p, AbstractMsg abmsg) {
		this.msg = (Postponed) abmsg;
		this.pro = p;
	}

	@Override
	public void run() {
		this.pro.postponed = true;
		System.out.println("PostponedHandler is running, for " + this.pro.getName() + " to "
				+ this.msg.sender);
	}

}
