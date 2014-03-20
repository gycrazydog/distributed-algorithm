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

	private static final long serialVersionUID = 1L;

	public PostponedHandler(Process p, AbstractMsg abmsg) {
		this.msg = (Postponed) abmsg;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("PostponedHandler is running, for "
				+ this.msg.toString());
	}

}
