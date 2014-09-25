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
					Log.add("Montagemitarbeiter " + this.getId() + ": Gebe folgende Parts zurück: " + getConcatElements(this.parts));
					Thread.sleep(100);
					this.wait(this.lagermitarbeiter);
					return false;
				}
			}
		}
		
	}

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
	
	private void deliverProduct() {
		auslagerung.addLines(sekretariat.getBuildingplan().getDeliverPath, this.parts);
		getConcatElements(this.parts);
		Log.add(Bauplan.getName() + "-ID" + this.sekretariat.getNewProductId() + ", Mitarbeiter-ID" + this.getId() + ", " + concatParts); 
		this.parts = null;
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
		String finishedPart = part.substring(0, sekretariat.getBuildingplan().getDelimiter());
		finishedPart += concatParts(numbers);
		for (int j = 0; j<numbers.length -2; j++) {
			finishedPart += numbers[j] + sekretariat.getBuildingplan().getDelimiter();
		}
		finishedPart += numbers[numbers.length-1];
		
		return part;
		
	}

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
