import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;
/**
 * Test-Cases fuer die Klasse GummiEnte
 * @author Sebastian Steinkellner, Alexander Koelbl
 *
 */
public class Test_GummiEnte {
	GummiEnte ente;
	private ByteArrayOutputStream outContent;
	private PrintStream original;
	
	/**
	 * GummiEnte wird intialisiert
	 */
	@Before
	public void setup(){
		ente = new GummiEnte();
		outContent = new ByteArrayOutputStream();
		original = System.out;
		System.setOut(new PrintStream(outContent));
	}
	
	/**
	 * Kontrolle, ob die Methode quaken() den richtigen Text ausgibt.
	 */
	@Test
	public void test_quaken(){
		ente.quaken();
		assertEquals("Quietsch\r\n",outContent.toString());
	}
	
	/**
	 * Kontrolle, ob die Methode toString() den richtigen Text zurueckgibt.
	 */
	@Test
	public void test_toString(){
		String text = ente.toString();
		assertEquals("Gummiente",text);
	}	
	
	@After
	public void tearDown() throws Exception {
		System.setOut(original);
	}
}