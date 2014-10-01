package tgm.sew.hit.roboterfabrik.testen;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.arbeiter.Lieferant;

public class TestLieferant {

	private Lieferant l1;
	private Sekretariat s1;
	private Pattern pattern;
	
	@Before
	public void doItBefore() {
		this.s1 = new Sekretariat(1000, 1, 1);
		this.l1 = new Lieferant(s1);
		pattern = Pattern.compile("{eye, body, chain, arm}[{1-20};]*{1-20}");
	}
	
	@Test
	public void getRandomLine() {
		Matcher m = this.pattern.matcher(l1.getRandomLine());
		assertEquals(true, m.matches());
	}
}
