package motej.demos.common;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import motej.Mote;
import motej.MoteFinder;
import motej.MoteFinderListener;

public class SimpleMoteFinder implements MoteFinderListener {

//	private Logger log = LoggerFactory.getLogger(SimpleMoteFinder.class);
	private MoteFinder finder;
	private Object lock = new Object();
	private Mote mote;

	public void moteFound(Mote mote) {
            System.out.println("SimpleMoteFinder received notification of a found mote.");
		//log.info("SimpleMoteFinder received notification of a found mote.");
		this.mote = mote;
		synchronized(lock) {
			lock.notifyAll();
		}
	}
	
	public Mote findMote() {
		if (finder == null) {
			finder = MoteFinder.getMoteFinder();
			finder.addMoteFinderListener(this);
		}
		finder.startDiscovery();
		try {
			synchronized(lock) {
				lock.wait();
			}
		} catch (InterruptedException ex) {
                    System.out.println(ex);
			//log.error(ex.getMessage(), ex);
		}
		return mote;
	}

}
