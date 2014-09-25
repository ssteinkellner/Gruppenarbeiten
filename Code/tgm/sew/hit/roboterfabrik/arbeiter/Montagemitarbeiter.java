package tgm.sew.hit.roboterfabrik.arbeiter;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.statisch.Dateizugriff;
import tgm.sew.hit.roboterfabrik.statisch.Log;

/**
 * 
 * @author Martin Kritzl
 * @version 20140925
 * 
 * Ist fuer die Sortierung der Parts und dadurch die Produktion der Threadees verantwortlich
 */
public class Montagemitarbeiter extends Mitarbeiter {

	private String[] parts;

	private Dateizugriff auslagerung;

	/**Erzeugt einen neuen Montagemitarbeiter
	 * 
	 * @param sekretariat
	 */
	
	public Montagemitarbeiter(Sekretariat sekretariat) {
		super(sekretariat);
	}

	/**Holt sich alle notwendigen Teile vom lagermitarbeiter und gibt sie gleich wieder
	 * zurueck wenn diese nicht vollstaendig sind. Im zweiten Fall wird ebenso eine Pause
	 * eingelegt und auf lagermitarbeiter gewartet
	 * 
	 * @return Ob alle Teile angefordert werden konnten
	 */
	
	private boolean getAllParts() {
		String[] needParts = Bauplan.getParts();
		String[] parts;
		String part;
		for (int i = 0; i < needParts.length; i++) {
			parts = this.lagermitarbeiter.getParts(parts[i], Bauplan.getPartCount(parts[i]));
			Log.add("Montagemitarbeiter " + this.getId() + ": Habe folgende Parts erhalten: " + getConcatElements(this.parts));
			for (int j = 0; j < parts.length; j++) {
				part = parts[i];
				if (part != null) {
					this.parts[i]=part;
				} else {
					this.lagermitarbeiter.addParts(this.parts);
					Log.add("Montagemitarbeiter " + this.getId() + ": Gebe folgende Parts zurueck: " + getConcatElements(this.parts));
					Thread.sleep(100);
					this.wait(this.lagermitarbeiter);
					return false;
				}
			}
		}
		
	}

	/**
	 * 
	 * @param array Ein array gefuellt mit Strings
	 * @return  Ein String der alle Elemente des Eingabearrays, getrennt mit dem im Bauplan
	 * 			festgelegten Trennzeichen, beeinhaltet
	 */
	
	private String getConcatElements(String[] array) {
		String concatParts = "";
		for (int i = 0; i < array.length-1;i++) {
			if (array[i] != null) {
				concatParts += array[i] + Bauplan.getDelimiter;
			}
		}
		//concatParts += this.parts[this.parts.length-1];
		return concatParts;
	}
	
	/**
	 * uebergibt dem Dateizugriff den hinzuzufuegenden Threadee
	 */
	
	private void deliverProduct() {
		String[] addThreadee;
		addThreadee[0]=Bauplan.getName() + "-ID" + this.sekretariat.getNewProductId() + ", Mitarbeiter-ID" + this.getId();
		addThreadee[1]=getConcatElements(this.parts);
		auslagerung.addLines(Bauplan.getDeliverPath, addThreadee);
		Log.add(addThreadee[0] + "\r\n" + addThreadee[1]); 
		this.parts = null;
	}
	
	/**Macht aus einem Part als String ein int[] in dem nur die Zahlen stehen
	 * 
	 * @param part Ein String der einen Part repraesentiert
	 * @return Ein int[] in dem nur die Zahlen stehen
	 */
	
	private int[] getIntArray(String part) {
		String delimiter = sekretariat.getBuildingplan().getDelimiter();
		String[] pieces = part.split(delimiter);
		int[] numbers = new int[pieces.length - 1];
		if (numbers.length != this.sekretariat.getBuildingplan().getPartLength()) {
			Log.add("Montagemitarbeiter " + this.getId() + ": Fehlerhafter Part(Nicht gewuenschte anzahl der Zahlen");
		}
		try{
			for (int i = 1; i<pieces.length-1;i++) {
				numbers[i] = Integer.parseInt(pieces[i]);
			}
		} catch(NumberFormatException e) {
			Log.add("Montagemitarbeiter " + this.getId() + ": Fehlerhafter Part(NumberFormatException)");
		}
		return numbers;
	}
	
	/**Sortiert einen Part
	 * 
	 * @param part Ein String der einen Part repraesentiert
	 * @return Einen String der einen sortierten/fertigen Part repraesentiert
	 */
	
	public String sortPart(String part) {
		int[] numbers = getIntArray(part);
		
		int temp;
		int counter;
		boolean goOn;
		do {
            goOn = false;
            //"counter" ist das Gegenteil von i, das heißt er beginnt von hinten zu zaehlen an
            counter = numbers.length - 1;
            for (int i = 1; i < numbers.length; i++) {
                //wenn die linke zahl groeßer der rechten ist wird die linke in eine Variable kopiert
                //und dann von der rechten ueberschrieben, danach wird die Variable auf die rechte
                //Seite ueberschrieben
                if (numbers[i-1] > numbers[i]) {
                    temp = numbers[i-1];
                    numbers[i-1] =numbers[i];
                    numbers[i] = temp;
                    goOn = true;
                }
                //genau das selbe nur das es von hinten nach vorne geht
                if (numbers[counter-1] > numbers[counter]) {
                    temp = numbers[counter-1];
                    numbers[counter-1] = numbers[counter];
                    numbers[counter] = temp;
                    goOn = true;
                }
                counter--;
            }  
        } while (goOn);
		String finishedPart = part.substring(0, sekretariat.getBuildingplan().getDelimiter());
		finishedPart += concatParts(numbers);
		for (int j = 0; j<numbers.length -2; j++) {
			finishedPart += numbers[j] + sekretariat.getBuildingplan().getDelimiter();
		}
		finishedPart += numbers[numbers.length-1];
		
		return part;
		
	}

	/**
	 * Holt staendig die erforderlichen Parts, sortiert sie und bringt sie ins Lager
	 */
	
	public void run() {
		while(!this.sekretariat.getEmployees().isShutdown()) {
			if (getAllParts()) {
				for (int i = 0; i < this.parts.length;i++) {
					parts[i] = sortPart(parts[i]);
				}
				deliverProduct();
			}
		}
		
	}

}
