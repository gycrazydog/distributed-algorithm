/**
 * 
 */
package nl.tudelft.da.lab2.process;
import java.rmi.Remote;
import java.rmi.RemoteException;

import nl.tudelft.da.lab2.entity.AbstractMsg;

/**
 * @author vincentgong
 *
 */
public interface IProcessInterface extends Remote{
	public void post(AbstractMsg msg) throws RemoteException;
}
