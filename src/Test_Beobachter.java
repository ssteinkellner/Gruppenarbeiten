import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Test_Beobachter {
	private Beobachter b;
	private Quakfaehig q;
	
	@Before
	public void setUp() throws Exception {
		b = new Quakologe();
		q = new GummiEnte();
	}
	
	@Test
	public void simulate_quack() {
		b.aktualisieren(q);
	}

	@Test
	public void test_getName() {
		assertEquals("Quakologe", b.toString());
	}
}
