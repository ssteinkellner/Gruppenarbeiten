import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Test_MoorEnte {
	MoorEnte ente;
	private ByteArrayOutputStream outContent;
	private PrintStream original;
	
	@Before
	public void setup(){
		ente = new MoorEnte();
		outContent = new ByteArrayOutputStream();
		original = System.out;
		System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void test_quaken(){
		ente.quaken();
		assertEquals("Quak\r\n",outContent.toString());
	}
	
	@Test
	public void test_toString(){
		String text = ente.toString();
		assertEquals("Moorente",text);
	}
	
	@After
	public void tearDown() throws Exception {
		System.setOut(original);
	}
}
