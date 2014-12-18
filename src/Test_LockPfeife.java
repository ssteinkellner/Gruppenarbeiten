import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * testet die funktionen der lockpfeife
 * @author SSteinkellner
 * @version 2014.12.18
 */
public class Test_LockPfeife {
	private LockPfeife lp;
	
	private ByteArrayOutputStream outContent;
	private PrintStream original;
	
	/**
	 * Lockpfeife wird initialisierts
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		lp = new LockPfeife();
		
		outContent = new ByteArrayOutputStream();
		original = System.out;
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(original);
	}
	
	/**
	 * Kontrolle, ob die Methode toString() den richtigen Text zurueckgibt
	 */
	@Test
	public void test_getName() {
		Assert.assertEquals("Lockpfeife",lp.toString());
	}
	
	/**
	 * Kontrolle, ob die Methode quaken() den richtigen Text ausgibt
	 */
	@Test
	public void test_quaken() {
		lp.quaken();
		Assert.assertEquals("Kwaak\r\n",outContent.toString());
	}
}
