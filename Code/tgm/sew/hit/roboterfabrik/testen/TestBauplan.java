package tgm.sew.hit.roboterfabrik.testen;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.statisch.Bauplan;

/**
 * @author SSteinkellner
 * @version 141001
 */
public class TestBauplan {
	Bauplan bp;
	
	@Before
	public void setup(){
		bp = new Bauplan("..");
	}
	
	@Test
	public void testPartCount() {
		String teil = "auge";
		int anzahl = 5;
		bp.setPartCount(teil, anzahl);
		assertEquals(anzahl, bp.getPartCount(teil));
	}

	@Test
	public void testPartFile() {
		String teil = "auge", file="auge.csv";
		bp.setFile(teil, file);
//		System.out.println(bp.getFile(teil));
		assertTrue(bp.getFile(teil).contains(file));
	}
	
	@Test
	public void testLogPath(){
		String path = "/log.csv";
		bp.setLogPath(path);
//		System.out.println(bp.getLogPath());
		assertEquals(path, bp.getLogPath());
	}
	
	@Test
	public void testPartPath(){
		String path = "/test/parts/", teil = "auge", file="auge.csv";
		bp.setPartPath(path);
		bp.setFile(teil, file);
//		System.out.println(bp.getLogPath());
		assertEquals(path+file, bp.getFile(teil));
	}

	@Test
	public void testDelimiter(){
		assertEquals(';', bp.getDelimiter());
	}

	@Test
	public void testDeliverPath(){
		String file = "auslieferung.csv";
		assertTrue(bp.getDeliverPath().contains(file));
	}

	@Test
	public void testRandomMax(){
		assertEquals(20, bp.getMaxRandomNumber());
	}

	@Test
	public void testRandomMin(){
		assertEquals(0, bp.getMinRandomNumber());
	}

	@Test
	public void testTimeout(){
		assertEquals(60, bp.getTimeRetry());
	}

	@Test
	public void testProduktName(){
		assertEquals("Threadee", bp.getProduktName());
	}
	
	@Test
	public void testPartNames(){
		// teile in umgekehrter alphabetischer reihenfolge
		String[] keys = {"eye","chain","body","arm"};
		assertArrayEquals(keys, bp.getPartNames());
	}
}
