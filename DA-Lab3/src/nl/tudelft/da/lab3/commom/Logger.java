/**
 * 
 */
package nl.tudelft.da.lab3.commom;

/**
 * @author vincentgong
 * 
 */
public class Logger {

	private static Logger logger;
	private static String loggerFilePath;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public synchronized static Logger getInstance() {
		if (logger == null || logger.equals(null)) {
			logger = new Logger();
			logger.loggerFilePath = Utils.getInstance().logFilePath;
		}
		return logger;
	}
	
	public void log(String line){
		try {
			MyLineWriter.getInstance().writeLine(loggerFilePath, line, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Looger File Fails");
			e.printStackTrace();
		}
	}
	
	public void logEmptyLine(){
		try {
			MyLineWriter.getInstance().writeLine(loggerFilePath, "", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Looger File Fails");
			e.printStackTrace();
		}
	}
}
