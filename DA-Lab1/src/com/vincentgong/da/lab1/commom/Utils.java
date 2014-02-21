/**
 * 
 */
package com.vincentgong.da.lab1.commom;

import java.io.InputStream;
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
	public final String processFilePath = "./resource/processes.properties";
	
	public static void main(String[] args) {
		
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
    	
		input = Utils.class.getClassLoader().getResourceAsStream(processFilePath);
		if(input==null){
	            System.out.println("Sorry, unable to find " + processFilePath);
		    return processList;
		}
		
		if(prop.contains("processesAmount")){
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
