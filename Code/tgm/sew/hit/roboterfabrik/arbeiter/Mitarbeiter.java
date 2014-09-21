package tgm.sew.hit.roboterfabrik.arbeiter;

import tgm.sew.hit.roboterfabrik.Sekretariat;

public class Mitarbeiter implements Runnable, Watchable {

	private int id;

	protected Sekretariat sekretariat;

	private int keepGoing;

	public Mitarbeiter(Sekretariat sekretariat) {

	}

	public int getId() {
		return 0;
	}

	public void setId(int id) {

	}

	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

}
