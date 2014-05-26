/**
 * 
 */
package nl.tudelft.da.lab3.messages;


/**
 * @author vincentgong
 *
 */
public class CaptureAttempMsg extends AbstractMsg {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int level;
	public int id;
	public CaptureAttempMsg(int level,int id,String ProcessName){
		this.level = level;
		this.sender = ProcessName;
		this.id = id;
	}
}
