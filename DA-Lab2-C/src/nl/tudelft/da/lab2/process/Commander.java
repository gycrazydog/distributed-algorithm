/**
 * 
 */
package nl.tudelft.da.lab2.process;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import nl.tudelft.da.lab2.commom.Utils;
import nl.tudelft.da.lab2.messages.ProcessItem;

/**
 * @author vincentgong
 * 
 */
public class Commander {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		/*
		 * 1. get all processItems, including their resourceSetNumber 2. if
		 * processItems is not empty, initialize the registry 3. for each
		 * processItems, put it into the registry with it's name
		 */

		List processItemList = Utils.getInstance().getProcessesList();
		List processList = new LinkedList();

		if (processItemList.isEmpty()) {
			return;
		}

		Registry registry = LocateRegistry.createRegistry(3233);

		Iterator it = processItemList.iterator();
		int processID = 0;
		while (it.hasNext()) {
			ProcessItem pro = (ProcessItem) it.next();
			try {
				Process process = new Process(pro.name, processID, pro.IP,
						pro.port);
				process.setReqSetNumberList(pro.resourceSetNumber);//pass the resource Set number of this process
				process.setProcessesItemList(processItemList);//pass the ProcessItemList to this process
				process.initReqSetList();//initialize the requestSet list of this process
				
				processList.add(process);
				
				Utils.regProcess(registry, pro.name, process);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			processID++;
		}
		
		Iterator it1 = processList.iterator();
		while(it1.hasNext()){
			Process process = (Process) it1.next();
			Thread t = new Thread(process.sender);
			t.start();
		}

	}

}
