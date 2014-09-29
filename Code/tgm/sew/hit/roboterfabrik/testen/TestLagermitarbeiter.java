package tgm.sew.hit.roboterfabrik.testen;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.arbeiter.Lagermitarbeiter;
import tgm.sew.hit.roboterfabrik.Sekretariat;

/**
 * 
 * Die Klasse "TestLagermitarbeiter" testet die Klasse "Lagermitarbeiter". <br>
 * 
 * @author Stefan Erceg
 * @version 20140929
 *
 */

public class TestLagermitarbeiter {

	private Lagermitarbeiter lm1;
	private Sekretariat s1;
	
	@Before
	public void doItBefore() {
		this.lm1 = new Lagermitarbeiter(s1);
	}
	
	@Test
	public void sortPart() {
		assertEquals(lm1.getParts(auge,2), "0;1;5;6;10;56");
	}
	
}
