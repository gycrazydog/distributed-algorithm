/**
 * 
 */
package nl.tudelft.da.lab1.commom;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * @author vincentgong
 *
 */
public class Utils {

	/**
	 * @param args
	 */
	public final String processFilePath = "Resource/processes.properties";
	
	public static void main(String[] args) {
		List l = Utils.getInstance().getProcessesList();
		Utils.getInstance().PrintList(l);
	}

	public void PrintList(List l) {
		// TODO Auto-generated method stub
		Iterator it = l.iterator();
		while(it.hasNext()){
			ProcessItem pi = (ProcessItem) it.next();
			System.out.println(pi);
		}
	}

	public static Utils getInstance() {
		// TODO Auto-generated method stub
		Utils utils = new Utils();
		return utils;
	}
	
	public List getProcessesList(){
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
		
		if(input==null){
	            System.out.println("Sorry, unable to find " + processFilePath);
		    return processList;
		}
		
		if(prop.containsKey("processesAmount")){
			int processesAmount = Integer.parseInt(prop.getProperty("processesAmount"));
			for(int i=0;i<processesAmount;i++){
				String[] line = prop.getProperty("process"+i).split(",");
				ProcessItem pi = new ProcessItem(line[0],Integer.parseInt(line[1]),line[2]);
				processList.add(pi);
			}
		}
		return processList;
	}

}
