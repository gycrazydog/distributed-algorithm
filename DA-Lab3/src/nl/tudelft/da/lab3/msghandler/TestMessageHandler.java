package nl.tudelft.da.lab3.msghandler;

import java.util.Iterator;
import java.util.List;

import nl.tudelft.da.lab3.entity.IAlgorithmProcess;
import nl.tudelft.da.lab3.entity.IMsgHandler;
import nl.tudelft.da.lab3.messages.AbstractMsg;
import nl.tudelft.da.lab3.messages.TestMessage;
import nl.tudelft.da.lab3.process.AfekGafniProcess;
import nl.tudelft.da.lab3.process.AfekGafniProcessItem;

public class TestMessageHandler implements IMsgHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	IAlgorithmProcess iap;
	TestMessage tm;

	public TestMessageHandler(IAlgorithmProcess iap, AbstractMsg abmsg) {
		this.iap = iap;
		this.tm = (TestMessage) abmsg;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// System.out.println(this.tm.content);

		if (this.iap.getProcess().getName().equals("GX")) {
			AfekGafniProcess agp = (AfekGafniProcess) this.iap;
			this.printAlgorProcessItemList(agp.getAlgorProcessItemList());
			// this.printAlgorUntraversedLinkList(agp.getAlgorUntraversedLinkList());
		}

	}

	public void printAlgorProcessItemList(List apil) {
		Iterator it = apil.iterator();
		while (it.hasNext()) {
			AfekGafniProcessItem agpi = (AfekGafniProcessItem) it.next();
			System.out.println(agpi.toString());
		}
	}

	public void printAlgorUntraversedLinkList(List aull) {
		Iterator it = aull.iterator();
		while (it.hasNext()) {
			AfekGafniProcessItem agpi = (AfekGafniProcessItem) it.next();
			System.out.println(agpi.toString());
		}
	}

}
