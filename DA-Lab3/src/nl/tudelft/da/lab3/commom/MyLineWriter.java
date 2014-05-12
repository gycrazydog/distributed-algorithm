/**
 * 
 */
package nl.tudelft.da.lab3.commom;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * {@link MyLineWriter} is a shared tool Class to write one String to a file line by line.
 * Note: It's a Singleton class. Please use it in the proper way. See sample in main().
 * @author vincentgong Singleton Line Writer in java.
 */
public class MyLineWriter {

	private static MyLineWriter instance;

	private MyLineWriter() {
	}

	public static MyLineWriter getInstance() {
		if (instance == null) {
			instance = new MyLineWriter();
		}
		return instance;
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String sampleStr = "This is the sample code.";
		MyLineWriter.getInstance().writeLine("Sample.txt", sampleStr, true);
		
	}
	
	/**
	 * @param append=true: line will be written to the rare of the file.
	 * @throws Exception 
	 */
	public void writeLine(String fileName, String line, boolean append)
			throws Exception {
		
		boolean newFile = false;
		File f = new File(fileName);
		if (!f.exists()) {
			f.createNewFile();
			newFile = true;
		}
		BufferedWriter bw;
		if (append) {
			bw = new BufferedWriter(new FileWriter(f, true));
		} else {
			bw = new BufferedWriter(new FileWriter(f));
		}
		
		if(!newFile){
			bw.newLine();
		}
		bw.write(line);
		bw.close();
	}
	
	/**
	 * @param Line will be written to a new file, which means over write the file existed with the same name.
	 * @throws Exception 
	 */
	public void writeLine(String fileName, String line) throws Exception{
		writeLine(fileName, line, true);
	}
	
	public void writeLine(String fileName) throws Exception{
		writeLine(fileName, "", true);
	}
	
	
}
