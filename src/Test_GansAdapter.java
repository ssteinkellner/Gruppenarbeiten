import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test-Cases fuer die Klasse GansAdapter
 * @author Alexander Koelbl
 *
 */
public class Test_GansAdapter {
	GansAdapter adapter;
	Gans gans;
	private ByteArrayOutputStream outContent;
	private PrintStream original;
	
	/**
	 * Gans und GansAdapter werden initalisiert
	 */
	@Before
	public void setup() {
		 gans = new Gans();
		 adapter = new GansAdapter(gans);
		 outContent = new ByteArrayOutputStream();
		 original = System.out;
		 System.setOut(new PrintStream(outContent));
	}
	
	/**
	 * Kontrolle, ob die Methode toString() den richtigen Text fuer den GansAdapter zurueckgibt.
	 *
	 */
	@Test
	public void test_toString(){
		String text = adapter.toString();
		assertEquals("sich als Ente ausgebende Gans",text);
	}
	
	/**
	 * Kontrolle, ob bei ausfueher der Methode quaken() der richtige Text ausgegeben wird.
	 */
	@Test
	public void test_quaken(){
		adapter.quaken();
		assertEquals("Schnatter\r\n",outContent.toString());
	}
	
	@After
	public void tearDown() throws Exception {
		System.setOut(original);
	}
}
