package tgm.sew.hit.roboterfabrik;

import tgm.sew.hit.roboterfabrik.statisch.Dateizugriff;
import tgm.sew.hit.roboterfabrik.statisch.Bauplan;

public class Sekretariat {

	private volatile int lastWorkerId;

	private volatile int lastProductId;

	private ExecutorService employees;

	protected Dateizugriff store;

	protected Bauplan buildingplan;

	private int runTime;

	public Sekretariat(int runTime, int countLieferant, int countMontage) {

	}

	public synchronized int getNewWorkerId() {
		return 0;
	}

	public synchronized int getNewProductId() {
		return 0;
	}

}
