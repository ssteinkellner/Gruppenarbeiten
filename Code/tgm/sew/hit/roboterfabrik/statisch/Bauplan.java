package tgm.sew.hit.roboterfabrik.statisch;

import java.util.HashMap;

/**
 * @author Steinkellner Sebastian
 * @version 140924
 * 
 * eine Klasse, in der alle statischen informationen gespeichert werden
 */
public class Bauplan {

	private static  HashMap<String,Integer> parts;
	private static HashMap<String,String> files;
	
	private static String logPath;
	private static String partPath;

	/**
	 * erzeugt einen neuen Bauplan mit default-configuration
	 * @param path masterpfad fuer logging und parts
	 */
	public Bauplan(String path) {
		parts = new HashMap<String, Integer>();
		files = new HashMap<String, String>();
		
		
		/* Default Configuration */
		logPath = path+"/log";
		partPath = path+"/parts";
	}

	/**
	 * legt die Anzahl des genannten Teils fest, die fuer <u>einen</u> Roboter gebraucht wird
	 * @param name name des teils (sollte mit file uebereinstimmen)
	 * @param count anzahl, die fuer einen Roboter gebruahct wird
	 */
	public static void setPartCount(String name, int count) {
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
	public static void setFile(String name, String fileName) {
		if(files.containsKey(name)){
			files.remove(name);
		}
		files.put(name, fileName);
	}

	/**
	 * setzt den masterpfad fuers Loggen
	 * @param path pfad zur datei
	 */
	public static void setLogPath(String path) {
		logPath = path;
	}

	/**
	 * setzt den masterpfad fuer parts
	 * @param path pfad zur datei
	 */
	public static void setPartPath(String path) {
		partPath = path;
	}

	/**
	 * gibt die Anzahl des angefragten Teils zurueck, die fuer <u>einen</u> Roboter gebraucht wird
	 * @param name name des teils
	 * @return benoetigte Anzahl
	 */
	public static synchronized int getPartCount(String name) {
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
	public static synchronized String getFile(String name) {
		if(files.containsKey(name)){
			return partPath + files.get(name);
		}
		return null;
	}

	/**
	 * gibt den Masterpfad (und falls ergaenzt auch den Dateinamen) des Logs zurueck
	 * @return pfad fuer logs
	 */
	public static synchronized String getLogPath() {
		return logPath;
	}
}
