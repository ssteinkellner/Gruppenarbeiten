package tgm.sew.hit.roboterfabrik.statisch;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.SimpleLayout;

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
		parts.put("body", 1);
		files.put("body","rumpf.csv");
		parts.put("chain", 1);
		files.put("chain", "kette.csv");
		parts.put("arm", 2);
		files.put("arm", "arm.csv");
		parts.put("eye", 2);
		files.put("eye","auge.csv");
		
		/* Default Configuration */
		delimiter=';';
		
		partCount=20;
		randomMax=20;
		randomMin=0;
		retryTimeOut=60;
		produktName="Threadee";
		
		logPath = path+"/log/";
		partPath = path+"/parts/";
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
	 * setzt den deliverypath fuer fertige produkte
	 * @param path pfad zur datei
	 */
	public void setDeliverPath(String path){
		deliverPath = path;
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
	 * @return pfad &amp; Dateiname fuer teile
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
	 * gibt den Pfad der datei, in die die auszuliefernden roboter geschrieben werden sollen zur�ck
	 * @return auslieferungsdateipfad
	 */
	public String getDeliverPath(){
		return deliverPath;
	}
	
	/**
	 * gibt den f�r das csv gew�hlten Delimiter zur�ck
	 * @return Delimiter des csv
	 */
	public char getDelimiter(){
		return delimiter;
	}

	/**
	 * gibt die L�nge der Integerliste zur�ck, die einen Teil repr�sentiert
	 * @return l�nge der liste
	 */
	public int getPartLength() {
		return partCount;
	}
	
	/**
	 * gibt den wert, den eine generierte random Zahl maximal haben darf, zur�ck
	 * @return maximalwert
	 */
	public int getMaxRandomNumber() {
		return randomMax;
	}
	
	/**
	 * gibt den wert, den eine generierte random Zahl minimal haben darf, zur�ck
	 * @return minimalwert
	 */
	public int getMinRandomNumber() {
		return randomMin;
	}
	
	/**
	 * gibt die millisekunden, die ein Monteur warten soll,
	 * bevor er wieder versucht, alle teile zu bekommen, zur�ck
	 * @return timeOut in millisekunden
	 */
	public int getTimeRetry(){
		return retryTimeOut;
	}
	
	/**
	 * gibt den namen des Produkts zur�ck
	 * @return name des produkts
	 */
	public String getProduktName(){
		return produktName;
	}
	
	/**
	 * gibt die Namen der einzelnen Teile zur�ck
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
	 * eine methode, die pr�ft, ob das angegebene directory vorhanden ist, und es erstellt wenn nicht
	 * @param path pfad zum directory mit '/' am ende (alles nach dem letzten '/' wird weggeschnitten)
	 */
	private void checkDir(String path){
		File dir = new File(path.substring(0,path.lastIndexOf("/")));
		if(!dir.exists()){
			dir.mkdirs();
		}
	}

	public void checkPath() {
		checkDir(partPath);
		checkDir(logPath);
		checkDir(deliverPath);
	}
	
	/**
	 * stellt den logger so ein, dass er in den logpath schreibt
	 * <br>jedes neue logfile wird mit aktuellem datum und uhrzeit erstellt
	 */
	public void setupLogger(){
		Calendar c = Calendar.getInstance(new Locale("AT"));
		String path = logPath
				+format(c.get(Calendar.YEAR))
				+format(c.get(Calendar.MONTH)+1)
				+format(c.get(Calendar.DAY_OF_MONTH))
				+"-"
				+format(c.get(Calendar.HOUR_OF_DAY))
				+format(c.get(Calendar.MINUTE))
				+format(c.get(Calendar.SECOND))
				+".log";
		try {
			BasicConfigurator.configure(new FileAppender(new SimpleLayout(),path));
		} catch (IOException e) {
			System.err.println("Bauplan: IOException when configurating Logger!\n "+e.getMessage());
		}
	}
	
	/**
	 * formatiert die eingegebene zahl folgenderma�en:
	 * <br> - input l�nger als 2: letzten 2 ziffern werden zur�ckgegeben
	 * <br> - input k�rzer als 2: vorne wird mit 0 aufgef�llt
	 * @param input
	 * @return
	 */
	private String format(int input){
		String output=""+input;
		if(output.length()>2){
			return output.substring(output.length()-2);
		}else if(output.length()<2){
			return "0"+output;
		}
		return output;
	}
}
