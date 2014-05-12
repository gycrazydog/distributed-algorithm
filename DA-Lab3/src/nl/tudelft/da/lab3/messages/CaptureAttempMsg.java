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

	public CaptureAttempMsg(String content, String senderProcess){
		this.content = content;
		this.sender = senderProcess;
	}
}
