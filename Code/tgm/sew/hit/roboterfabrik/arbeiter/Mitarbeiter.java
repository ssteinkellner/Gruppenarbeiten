package tgm.sew.hit.roboterfabrik.arbeiter;

import tgm.sew.hit.roboterfabrik.Sekretariat;

public abstract class Mitarbeiter implements Runnable {

	private int id;

	protected Sekretariat sekretariat;

	public Mitarbeiter(Sekretariat sekretariat) {

	}

	public int getId() {
		return 0;
	}

	public void setId(int id) {

	}

}
