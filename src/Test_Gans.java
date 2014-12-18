
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;

public class Test_Gans {
	Gans gans;
	private ByteArrayOutputStream outContent;
	private PrintStream original;
	
	@Before
	public void setup() {
		 gans = new Gans();
		 outContent = new ByteArrayOutputStream();
		 original = System.out;
		 System.setOut(new PrintStream(outContent));
	}	
	
	@Test
	public void test_schnattern(){
		gans.schnattern();;
		assertEquals("Schnatter\r\n",outContent.toString());
	}
	
	@Test
	public void test_toString(){
		String text = gans.toString();
		assertEquals("Gans",text);
	}
	
	@After
	public void tearDown() throws Exception {
		System.setOut(original);
	}
}
