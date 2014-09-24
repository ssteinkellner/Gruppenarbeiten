package tgm.sew.hit.roboterfabrik.arbeiter;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.statisch.Dateizugriff;

public class Montagemitarbeiter extends Mitarbeiter {

	private String[] parts;

	private Dateizugriff auslagerung;

	public Montagemitarbeiter(Sekretariat sekretariat) {
		super(sekretariat);
	}

	public String[] getAllParts() {
		return null;
	}

	/**
	 * Sycronized
	 */
	public void deliverProduct() {

	}

	public void run() {
		// TODO Auto-generated method stub
		
	}

}
