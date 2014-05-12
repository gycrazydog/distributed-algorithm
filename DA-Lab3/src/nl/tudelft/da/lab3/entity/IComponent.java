/**
 * 
 */
package nl.tudelft.da.lab3.entity;

import java.util.List;
import java.util.PriorityQueue;

import nl.tudelft.da.lab3.messages.AbstractMsg;


/**
 * @author vincentgong
 *
 */
public interface IComponent {
	
	public void CaptureAttempt(AbstractMsg abmsg);
	
}
