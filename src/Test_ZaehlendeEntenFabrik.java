import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author SSteinkellner
 * @version 2014.12.18
 */
public class Test_ZaehlendeEntenFabrik {
	AbstraktEntenFabrik entenFabrik;
	
	@Before
	public void setUp() throws Exception {
		entenFabrik = new ZaehlendeEntenFabrik();
	}
	
	@Test
	public void getGummiEnte() {
		Quakfaehig ente = entenFabrik.erzeugeGummiEnte();
		assertEquals("Gummiente", ente.toString());
	}
	
	@Test
	public void getMoorEnte() {
		Quakfaehig ente = entenFabrik.erzeugeMoorEnte();
		assertEquals("Moorente", ente.toString());
	}

	@Test
	public void getStockEnte() {
		Quakfaehig ente = entenFabrik.erzeugeStockEnte();
		assertEquals("Stockente", ente.toString());
	}

	@Test
	public void getLockPfeife() {
		Quakfaehig ente = entenFabrik.erzeugeLockPfeife();
		assertEquals("Lockpfeife", ente.toString());
	}
}