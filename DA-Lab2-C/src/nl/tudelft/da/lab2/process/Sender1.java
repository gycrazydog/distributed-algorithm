/**
 * 
 */
package nl.tudelft.da.lab2.process;

import java.io.Serializable;

import nl.tudelft.da.lab2.messages.AbstractMsg;

/**
 * @author vincentgong
 * 
 */
public class Sender1 implements Runnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int i;
	public Sender1(int i){
		this.i = i;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(1==this.i){
			while(true){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("11111111");
			}
		}
		System.out.println("Sender1.run() " + this.i);
	}

	public void handle(AbstractMsg absMsg) {
		System.out.println("Print the msg. Msg: " + absMsg.toString());
	}
}
