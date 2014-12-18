import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class Test_ZaehlendeEntenFabrik {
	ZaehlendeEntenFabrik entenFabrik;
	Quakfaehig ente;
	QuakZaehler zaehler;
	
	private static int count = QuakZaehler.getQuaks();

	@Before
	public void setup(){
		entenFabrik = new ZaehlendeEntenFabrik();
	}
	
	@Test
	public void test_erzeugeStockEnte(){
		ente = entenFabrik.erzeugeStockEnte();
		zaehler = new QuakZaehler(ente);
		ente.quaken();
		count++;
		assertEquals(count,zaehler.getQuaks());
	}
	
	@Test
	public void test_erzeugeGummiEnte(){
		ente = entenFabrik.erzeugeGummiEnte();
		zaehler = new QuakZaehler(ente);
		ente.quaken();
		count++;
		assertEquals(count,zaehler.getQuaks());
	}
	
	@Test
	public void test_erzeugeMoorEnte(){
		ente = entenFabrik.erzeugeMoorEnte();
		zaehler = new QuakZaehler(ente);
		ente.quaken();
		count++;
		assertEquals(count,zaehler.getQuaks());
	}
	
	@Test
	public void test_erzeugeLockPfeife(){
		Quakfaehig lockPfeife = entenFabrik.erzeugeLockPfeife();
		String text = lockPfeife.toString();
		assertEquals("Lockpfeife",text);
	}
}