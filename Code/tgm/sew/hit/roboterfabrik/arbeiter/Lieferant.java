package tgm.sew.hit.roboterfabrik.arbeiter;

import tgm.sew.hit.roboterfabrik.Sekretariat;

public class Lieferant extends Mitarbeiter {

	private String currentPart;

	public Lieferant(Sekretariat sekretariat) {
		super(sekretariat);
	}

	public void changePart() {
		String[] parts = Bauplan.getParts();
		int random = (int) (Math.random() * parts.length);
		this.currentPart = parts[random];
	}

	public String getRandomLine() {
		String part = currentPart;
        for (int i = 0; i < Bauplan.getPartLength(); i++) {
            part += Bauplan.getDelimiter() + (int) (Math.random() * Bauplan.getMaxRandomNumber() + 1);  
        }
        return part;
	}

	public void run() {
		while(!this.sekretariat.getEmployees().isShutdown()) {
			
		}
		
	}

}
