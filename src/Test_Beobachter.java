import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Test-Cases fuer die Klasse Quakologe
 * @author Sebastian Steinkellner, Alexander Koelbl
 *
 */

public class Test_Beobachter {
	private Beobachter b;
	private Quakfaehig q;
	
	/**
	 * Quakologe und GummiEnte werden initialisiert
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		b = new Quakologe();
		q = new GummiEnte();
	}
	
	/**
	 * Test, ob die Methode aktualisieren funktioniert
	 */
	@Test
	public void simulate_quack() {
		b.aktualisieren(q);
	}
	
	/**
	 * Test, ob die Methode toString() wirklich den richtigen Text zurueckgibt
	 */
	@Test
	public void test_getName() {
		assertEquals("Quakologe", b.toString());
	}
}
