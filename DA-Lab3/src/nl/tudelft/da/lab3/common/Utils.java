/**
 * 
 */
package nl.tudelft.da.lab3.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import nl.tudelft.da.lab3.entity.*;
import nl.tudelft.da.lab3.process.AfekGafniProcessItem;

/**
 * @author vincentgong
 * 
 */
public class Utils {

	/**
	 * @param args
	 */
	public final String processFilePath = "Resource/processes.properties";
	public final String REPORT_PATH = "Resource/Candidates/";
	public final String logFilePath = "Resource/Logs.log";

	public static void main(String[] args) {
		List l = Utils.getInstance().getProcessesList();
		Utils.getInstance().PrintList(l);
	}

	public void PrintList(List l) {
		// TODO Auto-generated method stub
		Iterator it = l.iterator();
		while (it.hasNext()) {
			ProcessItem pi = (ProcessItem) it.next();
			System.out.println(pi);
		}
	}

	public static Utils getInstance() {
		// TODO Auto-generated method stub
		Utils utils = new Utils();
		return utils;
	}

	public List getProcessesList() {
		Properties prop = new Properties();
		InputStream input = null;
		List processList = new LinkedList();

		try {
			input = new FileInputStream(processFilePath);
			// load a properties file
			prop.load(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (input == null) {
			System.out.println("Sorry, unable to find " + processFilePath);
			return processList;
		}

		if (prop.containsKey("processesAmount")) {
			int processesAmount = Integer.parseInt(prop
					.getProperty("processesAmount"));
			for (int i = 0; i< processesAmount; i++) {
				String[] line = prop.getProperty("process" + i).split(",");

				ProcessItem pi = new ProcessItem(i, line[0],
						Integer.parseInt(line[1]), line[2], line[3]);
				processList.add(pi);
			}
		}
		return processList;
	}

	public String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// System.out.println(dateFormat.format(date));
		return dateFormat.format(date);
	}

	public static void regProcess(Registry registry, String name,
			IProcessInterface process) {
		try {
			System.setSecurityManager(new RMISecurityManager());
			registry.rebind(name, process);

			System.out.println("Server is ready: " + name);
		} catch (Exception e) {
			System.out.println(name + " server failed: " + e);
		}
	}

	public static void regProcessWithNewRegistry(String name, int port,
			IProcessInterface process) {
		try {
			Registry registry = LocateRegistry.createRegistry(port);
			regProcess(registry, name, process);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void regProcessWithRegistry(String name, int port,
			IProcessInterface process) {
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(port);
			regProcess(registry, name, process);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void regProcessNoMatterRegistryExist(String name, int port,
			IProcessInterface process) {
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(port);
			regProcess(registry, name, process);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out
					.println("The registry does not exist. Try to build a new onw.");
			regProcessWithNewRegistry(name, port, process);
		}
	}
	
	public static void record(String line){
		System.out.println(line);
		Logger.getInstance().log(line);
	}

	
	public boolean isCandidate(List algorProcessItemList, String sender) {
		// TODO Auto-generated method stub
		Iterator it = algorProcessItemList.iterator();
		while (it.hasNext()) {
			AfekGafniProcessItem api = (AfekGafniProcessItem) it.next();
			if (api.pi.name.equals(sender)) {
				if(api.Candidate){
					return true;
				}
				return false;
			}
		}
		return false;
	}

	public void write4report(String w4rFileName, String line) {
		try {
			MyLineWriter.getInstance().writeLine(Utils.getInstance().REPORT_PATH + w4rFileName, line, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int queryID(List algorProcessItemList, String name) {
		// TODO Auto-generated method stub
		Iterator it = algorProcessItemList.iterator();
		while (it.hasNext()) {
			AfekGafniProcessItem api = (AfekGafniProcessItem) it.next();
			if (api.pi.name.equals(name)){
				return api.pi.ID;
			}
		}
		return -1;
	}
}
