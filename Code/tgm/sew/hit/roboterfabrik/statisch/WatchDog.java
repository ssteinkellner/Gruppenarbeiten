package tgm.sew.hit.roboterfabrik.statisch;

import java.util.concurrent.ExecutorService;

/**
 * 
 * @author Martin Kritzl
 * @version 20140925
 */

public class WatchDog implements Runnable{

	private Thread time;
	private long runtime;
	private ExecutorService mitarbeiter; 

	/**Erzeugt einen neuen Watchdog
	 * 
	 * @param mitarbeiter Die zu ueberwachenden Mitarbeiter
	 * @param runTime Die gewuenschte Laufzeit der Mitarbeiter
	 */
	
	public WatchDog(ExecutorService mitarbeiter, int runTime) {
		this.time.start();
	}
	
	/**
	 * Wartet die gesamte Laufzeit ueber und ruft faehrt dann die mitarbeiter herunter
	 */

	public void run() {
		try {
			this.time.sleep(runtime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.mitarbeiter.shutdown();
		
	}

}
