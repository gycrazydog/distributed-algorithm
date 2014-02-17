import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 
 */

/**
 * @author vincentgong
 * 
 */
public class AdditionClient {
	public static void main(String[] args) {

		// if (System.getSecurityManager() == null) {
		// System.setSecurityManager(new RMISecurityManager());
		// }

		AdditionInterface hello;
		try {
			System.setSecurityManager(new RMISecurityManager());
			// hello = (MyInterface) Naming.lookup("rmi://localhost/Multi");
			Registry registry = LocateRegistry.getRegistry("192.168.1.9", 3232);
			hello = (AdditionInterface) registry.lookup("multi");

			int result = hello.oper(7, 20);
			System.out.println("Result is :" + result);

		} catch (Exception e) {
			System.out.println("HelloClient exception: " + e);
		}
	}
}
