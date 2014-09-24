package tgm.sew.hit.roboterfabrik.arbeiter;

import tgm.sew.hit.roboterfabrik.Sekretariat;

public abstract class Mitarbeiter implements Runnable, Runnable {

	private int id;

	protected Sekretariat sekretariat;

	private ExecutorService employees;

	public Mitarbeiter(Sekretariat sekretariat, ExecutorService employees) {

	}

	public int getId() {
		return 0;
	}

	public void setId(int id) {

	}

}
