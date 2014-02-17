import java.rmi.*;

public interface AdditionInterface extends Remote {
	public int oper(int a, int b) throws RemoteException;
}