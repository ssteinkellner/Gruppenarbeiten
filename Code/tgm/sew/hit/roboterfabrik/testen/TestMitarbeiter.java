package tgm.sew.hit.roboterfabrik.testen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.arbeiter.Mitarbeiter;

/**
 * 
 * Die Klasse "TestMitarbeiter" testet die Klasse "Mitarbeiter". <br>
 * 
 * @author Stefan Erceg
 * @version 20140929
 *
 */

public class TestMitarbeiter {

	private Mitarbeiter m1;
	private Sekretariat s1;
	
	@Before
	public void doItBefore() {
		this.m1 = new Mitarbeiter(s1);
	}
	
}
