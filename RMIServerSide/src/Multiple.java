

import java.rmi.*;
import java.rmi.server.*;

public class Multiple extends UnicastRemoteObject implements AdditionInterface {

	public Multiple() throws RemoteException {
	}

	public int oper(int a, int b) throws RemoteException {
		int result = a * b;
		return result;
	}
}
