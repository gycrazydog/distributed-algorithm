
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MultipleClient {
	public static void main(String[] args) {
		MyInterface hello;
		try {
			System.setSecurityManager(new RMISecurityManager());
			// hello = (MyInterface)Naming.lookup("rmi://localhost/Addition");

			Registry registry = LocateRegistry.getRegistry("localhost", 3232);
			hello = (MyInterface) registry.lookup("Addition");

			int result = hello.oper(9, 20);
			System.out.println("Result is :" + result);

		} catch (Exception e) {
			System.out.println("HelloClient exception: " + e);
		}
	}
}