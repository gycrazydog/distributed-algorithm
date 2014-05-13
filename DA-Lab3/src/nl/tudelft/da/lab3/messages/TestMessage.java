package nl.tudelft.da.lab3.messages;

public class TestMessage extends AbstractMsg{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TestMessage(String senderName, String content){
		this.sender = senderName;
		this.content = content;
	}
}
