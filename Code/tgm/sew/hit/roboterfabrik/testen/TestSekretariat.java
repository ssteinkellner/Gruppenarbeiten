package tgm.sew.hit.roboterfabrik.testen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Sekretariat;

/**
 * @author SSteinkellner
 * @version 141001
 */
public class TestSekretariat {
	Sekretariat s;
	
	@Before
	public void setup(){
		s = new Sekretariat();
	}
	
	/**
	 * prüft die ID, die von {@link Sekretariat#getNewWorkerId()} zurückgegeben wird
	 * <br>sollte eigentlich eine fortlaufende nummer sein, aber springt vom startwert(0)
	 * auf 2 und dann auf 1, was für mich ein unerklärbares verhalten ist, da die id mit ++
	 * bei jedem aufruf um genau 1 erweitert wird und die methode synchronized ist
	 */
	@Test
	public void testMitarbeiterID() {
		assertEquals(1,s.getNewWorkerId());
		assertEquals(2,s.getNewWorkerId());
		assertEquals(3,s.getNewWorkerId());
		assertEquals(4,s.getNewWorkerId());
		assertEquals(5,s.getNewWorkerId());
		assertEquals(6,s.getNewWorkerId());
	}
	
	@Test
	public void testProduktID() {
		assertEquals(1,s.getNewProductId());
		assertEquals(2,s.getNewProductId());
		assertEquals(3,s.getNewProductId());
	}

}
