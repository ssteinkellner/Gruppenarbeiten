package tgm.sew.hit.roboterfabrik;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tgm.sew.hit.roboterfabrik.arbeiter.Lagermitarbeiter;
import tgm.sew.hit.roboterfabrik.arbeiter.Lieferant;
import tgm.sew.hit.roboterfabrik.arbeiter.Montagemitarbeiter;
import tgm.sew.hit.roboterfabrik.statisch.Bauplan;
import tgm.sew.hit.roboterfabrik.statisch.WatchDog;

/**
 * Klasse zur verwaltung der Mitarbeiter
 * @author Steinkellner Sebastian
 * @version 140924
 */
public class Sekretariat {

	private int lastWorkerId;
	private int lastProductId;

	private ExecutorService employees;
	private static Bauplan buildingplan;
	private Lagermitarbeiter storeWorker;

	public Sekretariat(int runTime, int countLieferant, int countMontage) {
		lastWorkerId = 0;
		lastProductId = 0;
		
		buildingplan = new Bauplan("..");
		storeWorker = new Lagermitarbeiter(this);
		
		employees = Executors.newFixedThreadPool(countLieferant+countMontage);
		
		for(int i=0; i<countLieferant; i++){
			employees.execute(new Lieferant(this));
		}
		
		for(int i=0; i<countMontage; i++){
			employees.execute(new Montagemitarbeiter(this));
		}
		
		new WatchDog(employees, runTime);
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
	
	public Lagermitarbeiter getLagermitarbeiter(){
		return storeWorker;
	}
}
