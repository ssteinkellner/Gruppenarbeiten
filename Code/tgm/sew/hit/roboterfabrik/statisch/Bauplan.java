package tgm.sew.hit.roboterfabrik.statisch;

import java.util.HashMap;

/**
 * @author Steinkellner Sebastian
 * @version 140924
 * 
 * eine Klasse, in der alle statischen informationen gespeichert werden
 */
public class Bauplan {

	private HashMap<String,Integer> parts;
	private HashMap<String,String> files;
	
	private String logPath, partPath, deliverPath, produktName;
	private char delimiter;
	private int partCount, randomMax, randomMin, retryTimeOut;

	/**
	 * erzeugt einen neuen Bauplan mit default-configuration
	 * @param path masterpfad fuer logging und parts
	 */
	public Bauplan(String path) {
		parts = new HashMap<String, Integer>();
		files = new HashMap<String, String>();
		
		/* default parts and files */
		parts.put("chain", 1);
		files.put("chain", "kette.csv");
		parts.put("arm", 1);
		files.put("arm", "arm.csv");
		parts.put("eye", 1);
		files.put("eye","auge.csv");
		parts.put("body", 1);
		files.put("body","rumpf.csv");
		
		/* Default Configuration */
		delimiter=';';
		
		partCount=20;
		randomMax=20;
		randomMin=0;
		retryTimeOut=5000;
		produktName="Threadee";
		
		logPath = path+"/log";
		partPath = path+"/parts";
		deliverPath = path+"/auslieferung.csv";
	}

	/**
	 * legt die Anzahl des genannten Teils fest, die fuer <u>einen</u> Roboter gebraucht wird
	 * @param name name des teils (sollte mit file uebereinstimmen)
	 * @param count anzahl, die fuer einen Roboter gebruahct wird
	 */
	public void setPartCount(String name, int count) {
		if(parts.containsKey(name)){
			parts.remove(name);
		}
		parts.put(name, count);
	}

	/**
	 * um den Dateinamen eines Teilefiles festzulegen
	 * @param name name des Teils (sollte mit part uebereinstimmen)
	 * @param fileName name des Files
	 */
	public void setFile(String name, String fileName) {
		if(files.containsKey(name)){
			files.remove(name);
		}
		files.put(name, fileName);
	}

	/**
	 * setzt den masterpfad fuers Loggen
	 * @param path pfad zur datei
	 */
	public void setLogPath(String path) {
		logPath = path;
	}

	/**
	 * setzt den masterpfad fuer parts
	 * @param path pfad zur datei
	 */
	public void setPartPath(String path) {
		partPath = path;
	}

	/**
	 * gibt die Anzahl des angefragten Teils zurueck, die fuer <u>einen</u> Roboter gebraucht wird
	 * @param name name des teils
	 * @return benoetigte Anzahl
	 */
	public int getPartCount(String name) {
		if(parts.containsKey(name)){
			return parts.get(name);
		}
		return 0;
	}

	/**
	 * gibt den Dateinamen samt masterpfad fuer ein gewisses Teil zurueck
	 * @param name name des teils
	 * @return pfad & Dateiname fuer teile
	 */
	public String getFile(String name) {
		if(files.containsKey(name)){
			return partPath + files.get(name);
		}
		return null;
	}

	/**
	 * gibt den Masterpfad (und falls ergaenzt auch den Dateinamen) des Logs zurueck
	 * @return pfad fuer logs
	 */
	public String getLogPath() {
		return logPath;
	}
	
	/**
	 * gibt den für das csv gewählten Delimiter zurück
	 * @return Delimiter des csv
	 */
	public char getDelimiter(){
		return delimiter;
	}

	/**
	 * gibt die Länge der Integerliste zurück, die einen Teil repräsentiert
	 * @return länge der liste
	 */
	public int getPartLength() {
		return partCount;
	}
	
	/**
	 * gibt den wert, den eine generierte random Zahl maximal haben darf, zurück
	 * @return maximalwert
	 */
	public int getMaxRandomNumber() {
		return randomMax;
	}
	
	/**
	 * gibt den wert, den eine generierte random Zahl minimal haben darf, zurück
	 * @return minimalwert
	 */
	public int getMinRandomNumber() {
		return randomMin;
	}
	
	/**
	 * gibt die millisekunden, die ein Monteur warten soll,
	 * bevor er wieder versucht, alle teile zu bekommen, zurück
	 * @return timeOut in millisekunden
	 */
	public int getTimeRetry(){
		return retryTimeOut;
	}
	
	/**
	 * gibt den namen des Produkts zurück
	 * @return name des produkts
	 */
	public String getProduktName(){
		return produktName;
	}
	
	/**
	 * gibt die Namen der einzelnen Teile zurück
	 * @return namen der teile
	 */
	public String[] getPartNames(){
		Object[] keys = parts.keySet().toArray();
		String[] namen = new String[keys.length];
		for(int i=0;i<keys.length;i++){
			namen[i] = (String)keys[i];
		}
		return namen;
	}
	
	/**
	 * gibt den Pfad der datei, in die die auszuliefernden roboter geschrieben werden sollen zurück
	 * @return auslieferungsdateipfad
	 */
	public String getDeliverPath(){
		return deliverPath;
	}
}
