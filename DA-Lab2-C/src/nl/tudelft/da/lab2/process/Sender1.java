/**
 * 
 */
package nl.tudelft.da.lab2.process;

import java.io.Serializable;

import nl.tudelft.da.lab2.entity.AbstractMsg;

/**
 * @author vincentgong
 * 
 */
public class Sender1 implements Runnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Sender1.run()");
	}

	public void handle(AbstractMsg absMsg) {
		System.out.println("Print the msg. Msg: " + absMsg.toString());
	}
}
