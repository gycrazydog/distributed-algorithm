/**
 * 
 */
package nl.tudelft.da.lab1.commom;
import java.rmi.Remote;
import java.rmi.RemoteException;

import nl.tudelft.da.lab1.message.AbstractMsg;
import nl.tudelft.da.lab1.message.Msg;

/**
 * @author vincentgong
 *
 */
public interface IProcessInterface extends Remote{
	public void post(AbstractMsg msg) throws RemoteException;
}
