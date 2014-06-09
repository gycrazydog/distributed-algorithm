/**
 * 
 */
package nl.tudelft.da.lab3.entity;
import java.rmi.Remote;
import java.rmi.RemoteException;

import nl.tudelft.da.lab3.messages.AbstractMsg;

/**
 * @author vincentgong
 *
 */
public interface IProcessInterface extends Remote{
	public void post(AbstractMsg msg) throws RemoteException;
}
