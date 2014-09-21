package tgm.sew.hit.roboterfabrik.arbeiter;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.statisch.Dateizugriff;

public class Montagemitarbeiter extends Mitarbeiter {

	public Montagemitarbeiter(Sekretariat sekretariat) {
		super(sekretariat);
		// TODO Auto-generated constructor stub
	}

	private String[] parts;

	private Dateizugriff auslagerung;

	

	public String[] getAllParts() {
		return null;
	}

	/**
	 * Sycronized
	 */
	public void deliverProduct() {

	}

	public void shutdown() {

	}

}
