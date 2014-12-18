import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test-Cases fuer die Klasse Entenfabrik
 * @author Alexander Koelbl
 *
 */
public class Test_Entenfabrik {
	AbstraktEntenFabrik entenFabrik;

	/**
	 * Entenfabrik wird initialisiert
	 */
	@Before
	public void setup(){
		entenFabrik = new Entenfabrik();
	}
	
	/**
	 * Test, ob die Methode erzeugeStockEnte() wirklich eine Stockente erzeugt.
	 * Kontrolle, ob die Methode toString() den richtigen Text fuer die Stockente zurueckgibt.
	 */
	@Test
	public void test_erzeugeStockEnte(){
		Quakfaehig ente = entenFabrik.erzeugeStockEnte();
		String text = ente.toString();
		assertEquals("Stockente",text);
	}
	
	/**
	 * Test, ob die Methode erzeugeGummiEnte() wirklich eine Gummiente erzeugt.
	 * Kontrolle, ob die Methode toString() den richtigen Text fuer die Gummiente zurueckgibt.
	 */
	@Test
	public void test_erzeugeGummiEnte(){
		Quakfaehig ente = entenFabrik.erzeugeGummiEnte();
		String text = ente.toString();
		assertEquals("Gummiente",text);
	}
	
	/**
	 * Test, ob die Methode erzeugeMoorEnte() wirklich eine Moorente erzeugt.
	 * Kontrolle, ob die Methode toString() den richtigen Text fuer die Moorente zurueckgibt.
	 */
	@Test
	public void test_erzeugeMoorEnte(){
		Quakfaehig ente = entenFabrik.erzeugeMoorEnte();
		String text = ente.toString();
		assertEquals("Moorente",text);
	}
	
	/**
	 * Test, ob die Methode erzeugeLockPfeife() wirklich eine Lockpfeife erzeugt.
	 * Kontrolle, ob die Methode toString() den richtigen Text fuer die Lockpfeife zurueckgibt.
	 */
	@Test
	public void test_erzeugeLockPfeife(){
		Quakfaehig lockPfeife = entenFabrik.erzeugeLockPfeife();
		String text = lockPfeife.toString();
		assertEquals("Lockpfeife",text);
	}	
}
