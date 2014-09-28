package tgm.sew.hit.roboterfabrik.testen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.arbeiter.Lieferant;

public class TestLieferant {

	private Lieferant l1;
	private Sekretariat s1;
	
	@Before
	public void doItBefore() {
		this.l1 = new Lieferant(s1);
	}
}
