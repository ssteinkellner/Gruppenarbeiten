import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * testet die funktionen der Schar
 * @author SSteinkellner
 * @version 2014.12.18
 */
public class Test_Schar {
	private Schar s;
	private Quakfaehig q;
	private Beobachter b;
	
	private ByteArrayOutputStream outContent;
	private PrintStream original;

	@Before
	public void setUp() throws Exception {
		s = new Schar();
		q = new GummiEnte();
		s.hinzufuegen(q);

		outContent = new ByteArrayOutputStream();
		original = System.out;
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(original);
	}
	
	@Test
	public void test_getName() {
		assertEquals("Entenschar", s.toString());
	}

	@Test
	public void test_quacken(){
		s.quaken();
		assertEquals("Quietsch\r\n",outContent.toString());
	}
	
	@Test
	public void test_beobachter(){
		b = new Quakologe();
		s.registriereBeobachter(b);
		
		//unused methode aufrufen, um 100% zu erreichen
		s.benachrichtigeBeobachtende();
	}
}
