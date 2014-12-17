
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;

public class Test_Gans {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	Gans gans;
	
	@Before
	public void gans() {
		 gans = new Gans();
		 System.setOut(new PrintStream(outContent));
	}	
	
	@Test
	public void test_schnattern(){
		gans.schnattern();;
		assertEquals("Schnatter\n",outContent.toString());
	}
	
	@Test
	public void test_toString(){
		String text = gans.toString();
		assertEquals("Gans",text);
	}
	
}
