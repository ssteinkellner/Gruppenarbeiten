package tgm.sew.hit.roboterfabrik.arbeiter;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.statisch.Dateizugriff;
import tgm.sew.hit.roboterfabrik.statisch.Log;

public class Montagemitarbeiter extends Mitarbeiter {

	private String[] parts;

	private Dateizugriff auslagerung;

	public Montagemitarbeiter(Sekretariat sekretariat) {
		super(sekretariat);
	}

	public String[] getAllParts() {
		return null;
	}


	
	private void deliverProduct() {
		auslagerung.addLines(sekretariat.getBuildingplan().getDeliverPath, parts);
	}
	
	private int[] getIntArray(String part) {
		String delimiter = sekretariat.getBuildingplan().getDelimiter();
		String[] pieces = part.split(delimiter);
		int[] numbers = new int[pieces.length - 1];
		if (numbers.length != this.sekretariat.getBuildingplan().getPartLength()) {
			Log.add("Montagemitarbeiter " + this.getId() + ": Fehlerhafter Part(Nicht gewünschte anzahl der Zahlen");
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
	
	public String sortPart(String part) {
		int[] numbers = getIntArray(part);
		
		int temp;
		int counter;
		boolean goOn;
		do {
            goOn = false;
            //"counter" ist das Gegenteil von i, das heißt er beginnt von hinten zu zählen an
            counter = numbers.length - 1;
            for (int i = 1; i < numbers.length; i++) {
                //wenn die linke zahl größer der rechten ist wird die linke in eine Variable kopiert
                //und dann von der rechten überschrieben, danach wird die Variable auf die rechte
                //Seite überschrieben
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
		String finishedPart = substring(0, sekretariat.getBuildingplan().getDelimiter());
		for (int j = 0; j<numbers.length -2; j++) {
			finishedPart += numbers[j] + sekretariat.getBuildingplan().getDelimiter();
		}
		finishedPart += numbers[numbers.length-1];
		
		return part;
		
	}

	public void run() {
		while(!this.sekretariat.getEmployees().isShutdown()) {
			
		}
		
	}

}
