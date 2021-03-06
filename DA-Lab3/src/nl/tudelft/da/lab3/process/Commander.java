/**
 * 
 */
package nl.tudelft.da.lab3.process;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import nl.tudelft.da.lab3.common.Utils;
import nl.tudelft.da.lab3.entity.ProcessItem;
import nl.tudelft.da.lab3.entity.IAlgorithmProcess;


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
		 * 1. get all processItems, including their resourceSetNumber 
		 * 2. if processItems is not empty, initialize the registry 
		 * 3. for each processItems, put it into the registry with it's name
		 */


		int totalProcessCount=0;
		int totalCandidateCount = 0;
		int totalOrdinaryCount=0;
		
		List processItemList = Utils.getInstance().getProcessesList();
		List processList = new LinkedList();
		List AlgorithmProcessList = new LinkedList();

		if (processItemList.isEmpty()) {
			return;
		}

		Registry registry = LocateRegistry.createRegistry(3233);

		Iterator it = processItemList.iterator();
		while (it.hasNext()) {
			ProcessItem pro = (ProcessItem) it.next();
			try {
				Process process = new Process(pro.name, pro.ID, pro.IP, pro.port);
				
				processList.add(process);
				process.setProcessesItemList(processItemList);//pass the ProcessItemList to this process
				
				//initial the Algorithm Process
				IAlgorithmProcess iap = new AfekGafniProcess(process, pro);
				process.setIap(iap);
				AlgorithmProcessList.add(iap);
				
				AfekGafniProcess tmpIap = (AfekGafniProcess) iap;
				if(tmpIap.Candidate){
					totalCandidateCount++;
				}else{
					totalOrdinaryCount++;
				}
				
				Utils.regProcess(registry, pro.name, process);
				totalProcessCount++;
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Utils.record("--------------------------------------------------------------");

		Utils.record("Total Process: "+ totalProcessCount+"; Candidate: " + totalCandidateCount +"; Ordinary: "+totalOrdinaryCount+".");
		Utils.record("--------------------------------------------------------------");
		
		Iterator it2 = AlgorithmProcessList.iterator();
		while(it2.hasNext()){
			IAlgorithmProcess iap = (IAlgorithmProcess) it2.next();
			Thread t = new Thread(iap.getSender());
			t.start();
		}

	}

}
