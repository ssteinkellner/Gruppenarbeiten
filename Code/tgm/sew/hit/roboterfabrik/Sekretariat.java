package tgm.sew.hit.roboterfabrik;

import java.util.concurrent.ExecutorService;

import tgm.sew.hit.roboterfabrik.arbeiter.Lieferant;
import tgm.sew.hit.roboterfabrik.arbeiter.Montagemitarbeiter;
import tgm.sew.hit.roboterfabrik.statisch.Bauplan;
import tgm.sew.hit.roboterfabrik.statisch.Dateizugriff;

/**
 * Klasse zur verwaltung der Mitarbeiter
 * @author Steinkellner Sebastian
 * @version 140924
 */
public class Sekretariat {

	private volatile int lastWorkerId;
	private volatile int lastProductId;

	private ExecutorService employees;
	private Dateizugriff store;
	private static Bauplan buildingplan;

	private int runTime;

	public Sekretariat(int runTime, int countLieferant, int countMontage) {
		lastWorkerId = 0;
		lastProductId = 0;
		
		this.runTime = runTime;
		
		for(int i=0; i<countLieferant; i++){
			Lieferant l = new Lieferant(this);
		}
		
		for(int i=0; i<countMontage; i++){
			Montagemitarbeiter m = new Montagemitarbeiter(this);
		}
	}

	public synchronized int getNewWorkerId() {
		lastWorkerId ++;
		return lastWorkerId;
	}

	public synchronized int getNewProductId() {
		lastProductId ++;
		return lastProductId;
	}

	public ExecutorService getEmployees() {
		return employees;
	}

	public static Bauplan getBauplan(){
		return buildingplan;
	}
}
