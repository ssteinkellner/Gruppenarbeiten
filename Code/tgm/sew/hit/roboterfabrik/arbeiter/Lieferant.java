package tgm.sew.hit.roboterfabrik.arbeiter;

import java.util.Random;

import tgm.sew.hit.roboterfabrik.Sekretariat;

public class Lieferant extends Mitarbeiter {

	private String currentPart;

	public Lieferant(Sekretariat sekretariat) {
		super(sekretariat);
	}

	public void changePart() {
		String[] parts = Bauplan.getParts();
		int random = new Random().nextInt(parts.length);
		this.currentPart = parts[random];
	}

	public String getRandomLine() {
		if (new Random().nextInt(10)<1) {
			changePart();
		}
		String part = currentPart;
        for (int i = 0; i < Bauplan.getPartLength(); i++) {
            part += Bauplan.getDelimiter() + new Random().nextInt(1, Bauplan.getMaxRandomNumber() + 1)+1;  
        }
        return part;
	}
	public void run() {
		while(!this.sekretariat.getEmployees().isShutdown()) {
			String[] parts = null;
			int counter = 0;
			do {
				parts[counter] = getRandomLine();
				counter++;
			}
			while(new Random().nextInt(20)<1);
			this.lagermitarbeiter.addParts(parts);
		}
		
	}

}
