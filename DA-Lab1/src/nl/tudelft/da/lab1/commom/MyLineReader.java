/**
 * 
 */
package nl.tudelft.da.lab1.commom;

import java.io.File;
import java.util.Scanner;

/**
 * @author vincentgong
 *
 */
public class MyLineReader {

	private File f;
	private Scanner sc;
	
	public MyLineReader(String fileName) throws Exception{
		this.f = new File(fileName);
		init();
	}
	
	public MyLineReader(File f) throws Exception{
		this.f = f;
		init();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public void init() throws Exception{
		if(!f.exists()){
			throw new Exception("File not found."+f.getName());
		}
		this.sc = new Scanner(f);
	}
	
	/**
	 * @param
	 * @throws Exception 
	 */
	public String nextLine(int startFromLineNumber) throws Exception{
		if(startFromLineNumber>0){
			for(int i=0;i<startFromLineNumber;i++){
				if(sc.hasNextLine()){
					sc.nextLine();
				}else{
					throw new Exception("Requested lines number("+startFromLineNumber +") exceeds lines in the file: "+i);
				}
			}
		}
		return sc.nextLine();
	}

	public String nextLine() throws Exception{
		return nextLine(0);
	}
	
	public boolean hasNextLine(){
		return sc.hasNextLine();
	}
	
	public void close(){
		if(this.sc!=null){
			sc.close();
		}
	}
}
