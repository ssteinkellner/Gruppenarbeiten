package tgm.sew.hit.roboterfabrik.testen;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
		this.s1 = new Sekretariat(1000, 1, 1);
		this.m1 = new Montagemitarbeiter(s1);
		l1 = Mockito.mock(Lagermitarbeiter.class);
	}
	
	@Test
	public void sortPart() {
		assertEquals("eye;1;1;2;3;3;4;5;5;5;7;9;10;14;15;16;16;17;17;18;18", m1.sortPart("eye;5;3;10;18;1;9;16;5;7;17;1;5;3;15;18;14;17;4;16;2"));
	}
	
	@Test
	public void getAllParts1() {
		ArrayList<String[]> para= new ArrayList<String[]>();
		para.add(new String[]{"eye;5;3;10;18;1;9;16;5;7;17;1;5;3;15;18;14;17;4;16;2", "eye;5;3;10;18;1;9;16;5;7;17;1;5;3;15;18;14;17;4;16;2"});
		para.add(new String[]{"body;5;3;10;18;1;9;16;5;7;17;1;5;3;15;18;14;17;4;16;2"});
		para.add(new String[]{"arm;5;3;10;18;1;9;16;5;7;17;1;5;3;15;18;14;17;4;16;2", "arm;5;3;10;18;1;9;16;5;7;17;1;5;3;15;18;14;17;4;16;2"});
		para.add(new String[]{"chain;5;3;10;18;1;9;16;5;7;17;1;5;3;15;18;14;17;4;16;2"});
		Mockito.when(l1.getParts("eye", 2)).thenReturn(para.get(0));
		Mockito.when(l1.getParts("body", 1)).thenReturn(para.get(1));
		Mockito.when(l1.getParts("arm", 2)).thenReturn(para.get(2));
		Mockito.when(l1.getParts("chain", 1)).thenReturn(para.get(3));
		String[] allParts = m1.getAllParts();
		for (int i = 0; i<allParts.length;i++) {
			assertEquals(para.get(i), allParts[i]);
		}
	}
	
	
	public void getAllParts2() {
		Mockito.when(l1.getParts("eye", 2)).thenReturn(new String[]{"eye;6;4;9;1;34"});
		Mockito.when(l1.getParts("body", 1)).thenReturn(new String[]{"body;6;4;9;1;34", "body;7;3;1;16;3"});
		Mockito.when(l1.getParts("arm", 2)).thenReturn(new String[]{"arm;6;4;9;1;34", "arm;7;3;1;16;3"});
		Mockito.when(l1.getParts("chain", 1)).thenReturn(new String[]{"chain;6;4;9;1;34", "chain;7;3;1;16;3"});
		assertEquals(null, m1.getAllParts());
	}
	
	@Test
	public void getConcatElements() {
		assertEquals("arm;7;4;10;1;56	body;8;1;19;3;4", m1.getConcatElements(new String[]{"arm;7;4;10;1;56", "body;8;1;19;3;4"}));
	}
	
	

}
