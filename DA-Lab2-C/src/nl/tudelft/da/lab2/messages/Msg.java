///**
// * 
// */
//package nl.tudelft.da.lab2.messages;
//
//import java.util.HashMap;
//
//import nl.tudelft.da.lab2.process.SClock;
//
///**
// * @author vincentgong
// * 
// */
//public class Msg extends AbstractMsg {
//
//	private static final long serialVersionUID = 1L;
//	public String content;
//	public int SenderFiledInVector;
//	public HashMap<String, Boolean> AckQueue;
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//	public Msg() {
//		this.clock = new SClock();
//	}
//
//	public Msg(String content, ProcessItem sender, int curClock,int SenderFiledInVector) {
//		this.clock.setClock(curClock);
//		this.content = content;
//		this.sender = sender;
//		this.SenderFiledInVector = SenderFiledInVector;
//	}
//
//	public Msg(String content, String senderIP, int senderPort,
//			String senderName, int curClock, int SenderFiledInVector) {
//		this.clock.setClock(curClock);
//		this.content = content;
//		this.sender = new ProcessItem(senderIP, senderPort, senderName);
//		this.SenderFiledInVector = SenderFiledInVector;
//	}
//
//	@Override
//	public String toString() {
//		String line = "Clock: " + this.clock.toString() + " msg from "
//				+ this.sender.toShortString() + " Msg: " + content;
//		return line;
//	}
//
//}
