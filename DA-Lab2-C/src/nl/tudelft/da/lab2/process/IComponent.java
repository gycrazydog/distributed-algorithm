/**
 * 
 */
package nl.tudelft.da.lab2.process;

import java.util.List;
import java.util.PriorityQueue;

/**
 * @author vincentgong
 *
 */
public interface IComponent {
	
	public PriorityQueue getReqQ();
	public VClock getVClock();
	public List getRequestSet();
	
	public void MulticastingRequest();
	public void ReceivingRequest();
	public void ReceivingGrant();
	public void ReceivingInquire();
	public void ReceivingRelinquish();
	public void ReceivingRelease();
	public void ReceivingPostponed();
	
}
