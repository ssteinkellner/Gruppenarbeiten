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

	private int lastWorkerId, lastProductId, runTime;
	private int[] count;

	private ExecutorService employees;
	private static Bauplan buildingplan;
	private Lagermitarbeiter storeWorker;

	public Sekretariat(int runTime, int countLieferant, int countMontage) {
		this();
		
		count[0]=countLieferant;
		count[1]=countMontage;
		this.runTime = runTime;
		
		start();
	}

	public Sekretariat(){
		lastWorkerId = 0;
		lastProductId = 0;
		count=new int[2];
		
		buildingplan = new Bauplan("..");
		storeWorker = new Lagermitarbeiter(this);
	}
	
	/**
	 * gibt die n�chste freie mitarbeiterID zur�ck
	 * @return id f�r neuen mitarbeiter
	 */
	public synchronized int getNewWorkerId() {
		lastWorkerId ++;
		return lastWorkerId;
	}

	/**
	 * gibt die n�chste freie produktID zur�ck
	 * @return id f�r neues produkt
	 */
	public synchronized int getNewProductId() {
		lastProductId ++;
		return lastProductId;
	}

	/**
	 * gibt den executerservice zur�ck, in dem alle monteure und lieferanten laufen
	 * @return executorservice der mitarbeiter
	 */
	public ExecutorService getEmployees() {
		return employees;
	}

	/**
	 * gibt den bauplan zur�ck, in dem alle informationen f�r den threadee gespeichert sind
	 * @return bauplan des threadee
	 */
	public static Bauplan getBauplan(){
		return buildingplan;
	}
	
	/**
	 * gibt den lagermitarbeiter zur�ck, der den dateizugriff verwaltet
	 * @return lagermitarbeiter => dateizugriff
	 */
	public Lagermitarbeiter getLagermitarbeiter(){
		return storeWorker;
	}
	
	/**
	 * setzt die anzahl der lieferanten, die die fabrik beliefern sollen
	 * @param count anzahl der lieferanten
	 */
	public synchronized void setLieferantCount(int count){
		this.count[0]=count;
	}

	/**
	 * setzt die anzahl der Monteure, die in der fabrik arbeiten sollen
	 * @param count anzahl der monteure
	 */
	public synchronized void setMonteurCount(int count){
		this.count[1]=count;
	}
	
	/**
	 * setzt den zeitraum, nach dem in der fabrik feierabend gemacht wird
	 * @param time zeitspanne zum arbeiten
	 */
	public synchronized void setRuntime(int time){
		runTime = time;
	}
	
	/**
	 * erstellt die lieferanten und monteure in einem threadpool und startet den watchdog
	 * <h1>ACHUNG!</h1>
	 * vorher <b>sollten</b> folgende Methoden aufgerufen werden:
	 * <br /> - setRuntime(int)
	 * <br /> - setMonteurCount(int)
	 * <br /> - setLieferantCount(int)
	 */
	public void start(){
		employees = Executors.newFixedThreadPool(count[0]+count[1]);
		
		for(int i=0; i<count[0]; i++){
			employees.execute(new Lieferant(this));
		}
		
		for(int i=0; i<count[1]; i++){
			employees.execute(new Montagemitarbeiter(this));
		}
		
		new WatchDog(employees, runTime);
	}
}
