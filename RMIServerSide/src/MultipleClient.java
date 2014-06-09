
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MultipleClient {
	public static void main(String[] args) {
		AdditionInterface hello;
		try {
			System.out.println("enter!!!!!!");
			System.setSecurityManager(new RMISecurityManager());
			// hello = (MyInterface)Naming.lookup("rmi://localhost/Addition");
			Registry registry = LocateRegistry.getRegistry("145.94.180.35",3233);
			hello = (AdditionInterface) registry.lookup("ABC");

			int result = hello.oper(9, 25);
			System.out.println("Result is :" + result);

		} catch (Exception e) {
			System.out.println("HelloClient exception: " + e);
		}
	}
}