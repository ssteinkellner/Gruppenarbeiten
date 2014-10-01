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

	private Sekretariat s;
	private Lagermitarbeiter lm1;
	
	/**
	 * Sekretariat und Lagermitarbeiter werden initialisiert
	 */
	
	@Before
	public void initialize() {
		this.s = new Sekretariat();
		this.lm1 = new Lagermitarbeiter(s);
	}
	
	@Test
	public void sortPart() {
		assertEquals(lm1.getParts("auge",2), "0;1;5;6;10;56");
	}
	
}
