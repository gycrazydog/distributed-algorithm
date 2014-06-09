/**
 * 
 */
package nl.tudelft.da.lab2.msghandler;

import nl.tudelft.da.lab2.messages.AbstractMsg;
import nl.tudelft.da.lab2.process.Process;


/**
 * @author vincentgong
 *
 */
public class AbstractMsgHandler implements IMsgHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AbstractMsg msg;
	
	public AbstractMsgHandler(Process p, AbstractMsg abmsg){
		this.msg = abmsg;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("AbstractMsgHandler msg: " + this.msg.toString());
	}

}
