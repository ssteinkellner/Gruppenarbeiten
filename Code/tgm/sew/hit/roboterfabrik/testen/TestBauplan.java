package tgm.sew.hit.roboterfabrik.testen;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.statisch.Bauplan;

public class TestBauplan {
	Bauplan bp;
	
	@Before
	public void setup(){
		bp = new Bauplan("..");
	}
	
	@Test
	public void testSetPartCount() {
		String teil = "auge";
		int anzahl = 5;
		bp.setPartCount(teil, anzahl);
		Assert.assertEquals(anzahl, bp.getPartCount(teil));
	}

	@Test
	public void testSetFile() {
		String teil = "auge", file="auge.csv";
		bp.setFile(teil, teil);
		Assert.assertEquals(file, bp.getFile(teil));
	}
}
