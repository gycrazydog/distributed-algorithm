/**
 * 
 */
package nl.tudelft.da.lab1.commom;

/**
 * @author vincentgong
 *
 */
public class MyStringBuffer {

	StringBuffer sb;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public MyStringBuffer(){
		this.sb = new StringBuffer();
	}
	
	public StringBuffer appendLine(String line){
		sb.append(line);
		sb.append("\n");
		return sb;
	}
	
	public String toString(){
		return sb.toString();
		
	}
}
