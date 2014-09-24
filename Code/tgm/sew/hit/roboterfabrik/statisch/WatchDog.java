package tgm.sew.hit.roboterfabrik.statisch;

import java.util.concurrent.ExecutorService;

public class WatchDog implements Runnable{

	private Thread time;
	private long runtime;
	private ExecutorService mitarbeiter; 

	public WatchDog(ExecutorService mitarbeiter, int runTime) {
		this.time.start();
	}

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
