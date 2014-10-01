package tgm.sew.hit.roboterfabrik.testen;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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
	
	/**
	 * testen, ob bei der Methode "getParts" die richtige Zeile zurueckgeliefert wird
	 */
	
	@Test
	public void testGetParts() {
		String[] parts = new String[]{"chain;5;3;10;18;1;9;16;5;7;17;1;5;3;15;18;14;17;4;16;2"};
		lm1.addParts("chain", parts);
		assertEquals(lm1.getParts("chain",1),parts[0]);
	}
	
	/**
	 * testen, ob bei der Methode "getParts" null zurueckgeliefert wird, wenn die Datei leer ist
	 */
	
	@Test
	public void testGetPartsNull() {
		assertEquals(lm1.getParts("chain",1)[0],null);
	}
	
}
