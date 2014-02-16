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

		MyInterface hello;
		try {
			System.setSecurityManager(new RMISecurityManager());
			// hello = (MyInterface) Naming.lookup("rmi://localhost/Multi");
			Registry registry = LocateRegistry.getRegistry("localhost", 3232);
			hello = (MyInterface) registry.lookup("Multi");

			int result = hello.oper(7, 20);
			System.out.println("Result is :" + result);

		} catch (Exception e) {
			System.out.println("HelloClient exception: " + e);
		}
	}
}
