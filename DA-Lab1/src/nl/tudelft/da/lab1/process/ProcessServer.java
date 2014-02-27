/**
 * 
 */
package nl.tudelft.da.lab1.process;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Iterator;

import nl.tudelft.da.lab1.commom.IProcessInterface;
import nl.tudelft.da.lab1.commom.ProcessItem;
import nl.tudelft.da.lab1.commom.Utils;

/**
 * @author vincentgong
 * 
 */
public class ProcessServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.setSecurityManager(new RMISecurityManager());

			Registry registry = LocateRegistry.createRegistry(3233);
			Iterator it = Utils.getInstance().getProcessesList().iterator();

			int i = 0;
			while (it.hasNext()) {
				
				Thread.sleep(1000);
				ProcessItem pi = (ProcessItem) it.next();
				IProcessInterface process = new Process(pi.id);
				registry.rebind("Process" + i, process);
				
				System.out.println("Process" + i + " is running.");
				i++;
			}

			System.out.println("Server is ready.");
		} catch (Exception e) {
			System.out.println("Server failed: " + e);
		}
	}

}
