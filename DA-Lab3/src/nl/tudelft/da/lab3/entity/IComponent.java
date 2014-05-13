/**
 * 
 */
package nl.tudelft.da.lab3.entity;

import nl.tudelft.da.lab3.messages.AbstractMsg;


/**
 * @author vincentgong
 *
 */
public interface IComponent {
	
//	public void ReceivingCaputredAttemptMsg(AbstractMsg abmsg);
	public void HandleTheMessage(AbstractMsg abmsg, IMsgHandler imh);
	
}
