import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class Test_Entenfabrik {
	AbstraktEntenFabrik entenFabrik;

	
	@Before
	public void setup(){
		entenFabrik = new Entenfabrik();
	}
	
	@Test
	public void test_erzeugeStockEnte(){
		Quakfaehig ente = entenFabrik.erzeugeStockEnte();
		String text = ente.toString();
		assertEquals("Stockente",text);
	}
	
	@Test
	public void test_erzeugeGummiEnte(){
		Quakfaehig ente = entenFabrik.erzeugeGummiEnte();
		String text = ente.toString();
		assertEquals("Gummiente",text);
	}
	
	@Test
	public void test_erzeugeMoorEnte(){
		Quakfaehig ente = entenFabrik.erzeugeMoorEnte();
		String text = ente.toString();
		assertEquals("Moorente",text);
	}
	@Test
	public void test_erzeugeStEnte(){
		Quakfaehig lockPfeife = entenFabrik.erzeugeLockPfeife();
		String text = lockPfeife.toString();
		assertEquals("Lockpfeife",text);
	}	
}
