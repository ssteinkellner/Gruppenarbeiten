package tgm.sew.hit.roboterfabrik.testen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.arbeiter.Lagermitarbeiter;

/**
 * 
 * Die Klasse "TestMitarbeiter" testet die Klasse "Mitarbeiter". <br>
 * 
 * @author Stefan Erceg
 * @version 20140929
 *
 */

public class TestMitarbeiter {

	private Lagermitarbeiter lm1;
	private Sekretariat s;
	
	/**
	 * Sekretariat und Lagermitarbeiter werden initialisiert
	 */
	
	@Before
	public void initialize() {
		this.s = new Sekretariat();
		this.lm1 = new Lagermitarbeiter(s);
	}
	
	/**
	 * testet, ob dem Lagermitarbeiter eine korrekte ID vergeben wird
	 */
	
	@Test
	public void testReturnId() {
		assertEquals(1,lm1.getId());
	}
	
}
