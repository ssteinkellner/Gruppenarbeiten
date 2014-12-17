import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;


public class Test_GummiEnte {
	GummiEnte ente;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void cons(){
		ente = new GummiEnte();
		System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void test_quaken(){
		ente.quaken();
		assertEquals("Quietsch\n",outContent.toString());
	}
	
}
