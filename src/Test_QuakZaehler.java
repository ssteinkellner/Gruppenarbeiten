import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * die klasse sollte den quackzaehler testen.<br />
 * da aber im quackzaehler die zeahlvariable static gesetzt ist und die
 * testklasse zuerst die zweite methode aufruft, die 2 mal quackt, gibt der
 * erste test dann einen fehler, da sich der zaehler nicht resetten laesst
 * 
 * @author SSteinkellner
 * @version 2014.12.17
 */
public class Test_QuakZaehler {
	Quakfaehig e;
	QuakZaehler z;

	@Before
	public void setup() throws Exception {
		e = new GummiEnte();
		z = new QuakZaehler(e);
	}

	@Test
	public void TestNoQuaks() {
		assertEquals(0, QuakZaehler.getQuaks());
	}

	@Test
	public void TestTwoQuaks() {
		z.quaken();
		z.quaken();
		assertEquals(2, QuakZaehler.getQuaks());
	}
	
	@Test
	public void test_toString(){
		String text = z.toString();
		assertEquals("Gummiente",text);
	}

}
