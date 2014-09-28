package tgm.sew.hit.roboterfabrik.testen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.arbeiter.Lagermitarbeiter;
import tgm.sew.hit.roboterfabrik.arbeiter.Montagemitarbeiter;

public class TestMontagemitarbeiter {

	private Montagemitarbeiter m1;
	private Sekretariat s1;
	private Lagermitarbeiter l1;
	
	@Before
	public void doItBefore() {
		this.m1 = new Montagemitarbeiter(s1);
		l1 = Mockito.mock(Lagermitarbeiter.class);
	}
	
	@Test
	public void sortPart() {
		assertEquals(m1.sortPart("6;5;10;56;0;1"), "0;1;5;6;10;56");
	}
	
	public void getAllParts1() {
		Mockito.when(l1.getParts("eye", 2)).thenReturn(new String[]{"auge;6;4;9;1;34", "auge;7;3;1;16;3"});
		Mockito.when(l1.getParts("body", 1)).thenReturn(new String[]{"body;6;4;9;1;34", "body;7;3;1;16;3"});
		Mockito.when(l1.getParts("arm", 2)).thenReturn(new String[]{"arm;6;4;9;1;34", "arm;7;3;1;16;3"});
		Mockito.when(l1.getParts("chain", 1)).thenReturn(new String[]{"chain;6;4;9;1;34", "chain;7;3;1;16;3"});
		assertEquals(true, m1.getAllParts());
	}
	
	public void getAllParts2() {
		Mockito.when(l1.getParts("eye", 2)).thenReturn(new String[]{"auge;6;4;9;1;34"});
		Mockito.when(l1.getParts("body", 1)).thenReturn(new String[]{"body;6;4;9;1;34", "body;7;3;1;16;3"});
		Mockito.when(l1.getParts("arm", 2)).thenReturn(new String[]{"arm;6;4;9;1;34", "arm;7;3;1;16;3"});
		Mockito.when(l1.getParts("chain", 1)).thenReturn(new String[]{"chain;6;4;9;1;34", "chain;7;3;1;16;3"});
		assertEquals(false, m1.getAllParts());
	}
	
	
	

}
