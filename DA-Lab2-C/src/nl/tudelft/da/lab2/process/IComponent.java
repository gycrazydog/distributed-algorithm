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
	public void ReceivingRequest(AbstractMsg abmsg);
	public void ReceivingGrant(AbstractMsg abmsg);
	public void ReceivingInquire(AbstractMsg abmsg);
	public void ReceivingRelinquish(AbstractMsg abmsg);
	public void ReceivingRelease(AbstractMsg abmsg);
	public void ReceivingPostponed(AbstractMsg abmsg);
	
}
