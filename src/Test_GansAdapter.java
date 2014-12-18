import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Test_GansAdapter {
	GansAdapter adapter;
	Gans gans;
	private ByteArrayOutputStream outContent;
	private PrintStream original;
	
	@Before
	public void setup() {
		 gans = new Gans();
		 adapter = new GansAdapter(gans);
		 outContent = new ByteArrayOutputStream();
		 original = System.out;
		 System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void test_toString(){
		String text = adapter.toString();
		assertEquals("sich als Ente ausgebende Gans",text);
	}
	
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
