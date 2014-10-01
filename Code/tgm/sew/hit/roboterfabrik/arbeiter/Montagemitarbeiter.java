package tgm.sew.hit.roboterfabrik.arbeiter;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.statisch.Bauplan;


/**
 * 
 * @author Martin Kritzl
 * @version 20140925
 * 
 * Ist fuer die Sortierung der Parts und dadurch die Produktion der Threadees verantwortlich
 */
public class Montagemitarbeiter extends Mitarbeiter {

	private String[] parts;

	private Lagermitarbeiter auslagerung;
	
	private static Logger logger;

	/**Erzeugt einen neuen Montagemitarbeiter
	 * 
	 * @param sekretariat
	 */
	
	public Montagemitarbeiter(Sekretariat sekretariat) {
		super(sekretariat);
		this.auslagerung = new Lagermitarbeiter(sekretariat);
		logger = Logger.getLogger(sekretariat.getBauplan().getLogPath());
		this.parts = new String[6];
		setNull();
	}

	/**Holt sich alle notwendigen Teile vom lagermitarbeiter und gibt sie gleich wieder
	 * zurueck wenn diese nicht vollstaendig sind. Im zweiten Fall wird ebenso eine Pause
	 * eingelegt und auf lagermitarbeiter gewartet
	 * 
	 * @return Ob alle Teile angefordert werden konnten
	 */
	
	public String[] getAllParts() {
		//Es wird die Liste der benötigten Teile vom Bauplan geholt
		String[] needParts = this.sekretariat.getBauplan().getPartNames();
		ArrayList<String[]> gotParts = new ArrayList<String[]>();
		String part = "";
		//Es werden die verschiedenen Bestandteile einzeln angefordert
		for (int i = 0; i < needParts.length; i++) {
			//Wie viele Bestandteile dieser Art werden benötigt
			int currentCount = this.sekretariat.getBauplan().getPartCount(needParts[i]);
			//Holt si
			String[] currentParts = this.sekretariat.getLagermitarbeiter().getParts(needParts[i], currentCount);
			//Falls Teile nicht mehr vorhanden sind 
			if (currentParts == null) {
				giveBack(gotParts);
				return null;
			}
			gotParts.add(currentParts);
			logger.log(Level.INFO, "Montagemitarbeiter " + this.getId() + ": Habe folgende Parts erhalten: " + getConcatElements(currentParts));
				for(String s : gotParts.get(i)) {
					for (int j = 0; j <this.parts.length;j++) {
						if (this.parts[j] == null) {
							this.parts[j] = s;
							break;
						}
					}
			}
		}
		return this.parts;
	}
	
	/**Gibt die Teile zurück die gerade im Besitz des Montagemitarbeiters sind
	 * 
	 * @return Die Teile die gerade verarbeitet werden
	 */
	
	public String[] getParts() {
		return this.parts;
	}
	
	public void setNull() {
		for (int i = 0;i<this.parts.length;i++) {
			this.parts[i] = null;
		}
	}
	
	/**
	 * Gibt die Teile wieder an den Lagermitarbeiter zurück
	 */
	
	private void giveBack(ArrayList<String[]> addParts) {
		for (int i = 0; i < addParts.size(); i++) {
			this.sekretariat.getLagermitarbeiter().addParts(addParts.get(i)[0], addParts.get(i));
			logger.log(Level.INFO, "Montagemitarbeiter " + this.getId() + ": Gebe folgende Parts zurueck: " + getConcatElements(addParts.get(i)));
		}
		setNull();
		logger.log(Level.INFO, "Montagemitarbeiter " + this.getId() + ": Frage in " + this.sekretariat.getBauplan().getTimeRetry() + "ms erneut nach Teilen");
		try {
			Thread.sleep(this.sekretariat.getBauplan().getTimeRetry());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		try {
			this.sekretariat.getLagermitarbeiter().wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	/**
	 * 
	 * @param array Ein array gefuellt mit Strings
	 * @return  Ein String der alle Elemente des Eingabearrays, getrennt mit dem im Bauplan
	 * 			festgelegten Trennzeichen, beeinhaltet
	 */
	
	public String getConcatElements(String[] array) {
		String concatParts = "";
		if (array != null) {
			for (int i = 0; i < array.length-1;i++) {
				if (array[i] != null) {
					concatParts += array[i] + "\n";
				}
			}
			concatParts += array[array.length-1];
			return concatParts;
		} else {
			return null;
		}
	}
	
	/**
	 * uebergibt dem Dateizugriff den hinzuzufuegenden Threadee
	 */
	
	private void deliverProduct() {
		int idThreadee = this.sekretariat.getNewProductId();
		String addThreadee = this.sekretariat.getBauplan().getProduktName() + "-ID" + idThreadee + ", Mitarbeiter-ID" + this.getId()+ "\r\n" + getConcatElements(this.parts)+"\r\n";
		auslagerung.addParts(this.sekretariat.getBauplan().getProduktName(), new String[]{addThreadee});
		logger.log(Level.INFO, "Montagemitarbeiter " + this.getId() + ": Threadee Nr. " + idThreadee + " abgeliefert");
		setNull();
	}
	
	/**Sortiert einen Part
	 * 
	 * @param part Ein String der einen Part repraesentiert
	 * @return Einen String der einen sortierten/fertigen Part repraesentiert
	 */

	public String sortPart(String part) {
		char delimiter = this.sekretariat.getBauplan().getDelimiter();
		String prefix = part.substring(0, part.indexOf(delimiter));
		part = part.substring(part.indexOf(delimiter)+1, part.length());
		ArrayList<Integer> parts = new ArrayList<Integer>();
		try{
			for (int i = 0; i < this.sekretariat.getBauplan().getPartLength()-1;i++){
				parts.add(Integer.parseInt(part.substring(0, part.indexOf(delimiter))));
				part = part.substring(part.indexOf(delimiter)+1, part.length());
			}
			parts.add(Integer.parseInt(part.substring(0, part.length())));
		} catch(NumberFormatException e) {
			logger.log(Level.ERROR, "Montagemitarbeiter " + this.getId() + ": Fehlerhafter Part(NumberFormatException)");
		}
		Collections.sort(parts);
		String sortedPart = prefix + delimiter;
		for (int number : parts) {
			sortedPart += "" + number + delimiter;
		}
		sortedPart = sortedPart.substring(0, sortedPart.lastIndexOf(delimiter));
 		return sortedPart;
	}
	
	/**
	 * Holt staendig die erforderlichen Parts, sortiert sie und bringt sie ins Lager
	 */
	
	public void run() {
		while(!this.sekretariat.getEmployees().isShutdown()) {
			if (this.getAllParts() != null) {
				for (int i = 0; i < this.parts.length;i++) {
					parts[i] = sortPart(parts[i]);
				}
				this.deliverProduct();
			}
		}
		
	}

}
