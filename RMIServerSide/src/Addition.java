

import java.rmi.*;
import java.rmi.server.*;

public class Addition extends UnicastRemoteObject implements AdditionInterface {

	public Addition() throws RemoteException {
	}

	public int oper(int a, int b) throws RemoteException {
		int result = a + b;
		return result;
	}
}
