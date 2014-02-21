/**
 * 
 */
package com.vincentgong.da.lab1.commom;
import java.rmi.*;

import com.vincentgong.da.lab1.message.Msg;

/**
 * @author vincentgong
 *
 */
public interface IProcessInterface extends Remote {
	public void post(Msg msg);
}
