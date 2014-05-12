/**
 * 
 */
package nl.tudelft.da.lab3.process;
import java.rmi.Remote;
import java.rmi.RemoteException;

import nl.tudelft.da.lab2.messages.AbstractMsg;

/**
 * @author vincentgong
 *
 */
public interface IProcessInterface extends Remote{
	public void post(AbstractMsg msg) throws RemoteException;
}
