import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.*;
/**
 * Test-Cases fuer die Klasse Gans
 * @author Alexander Koelbl
 *
 */
public class Test_Gans {
	Gans gans;
	private ByteArrayOutputStream outContent;
	private PrintStream original;

	/**
	 * Gans wird initalisiert
	 */
	@Before
	public void setup() {
		gans = new Gans();
		outContent = new ByteArrayOutputStream();
		original = System.out;
		System.setOut(new PrintStream(outContent));
	}
	
	/**
	 * Kontrolle, ob die Methode schnattern() den richtigen Text ausgibt
	 */
	@Test
	public void test_schnattern(){
		gans.schnattern();;
		assertEquals("Schnatter\r\n",outContent.toString());
	}
	
	/**
	 * Kontrolle, ob die Methode toString() den richtigen text zurueckgibt
	 */
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