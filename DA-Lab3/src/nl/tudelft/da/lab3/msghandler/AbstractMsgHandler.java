/**
 * 
 */
package nl.tudelft.da.lab3.msghandler;

import nl.tudelft.da.lab3.entity.IMsgHandler;
import nl.tudelft.da.lab3.messages.AbstractMsg;



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
