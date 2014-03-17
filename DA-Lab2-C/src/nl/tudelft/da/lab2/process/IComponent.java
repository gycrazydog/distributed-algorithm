/**
 * 
 */
package nl.tudelft.da.lab2.process;

import java.util.List;
import java.util.PriorityQueue;

import nl.tudelft.da.lab2.messages.AbstractMsg;

/**
 * @author vincentgong
 *
 */
public interface IComponent {
	
	public PriorityQueue getReqQ();
	public SClock getSClock();
	public List getRequestSet();
	
	public void MulticastingRequest();
	public void ReceivingRequest();
	public void ReceivingGrant();
	public void ReceivingInquire();
	public void ReceivingRelinquish();
	public void ReceivingRelease();
	public void ReceivingPostponed(AbstractMsg abmsg);
	
}
