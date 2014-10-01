package tgm.sew.hit.roboterfabrik.testen;

import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Simulation;

/**
 * @author SSteinkellner
 * @version 141001
 */
public class testSimulation {

	/* hier wird auf eine exception getestet um herauszufinden,
	 * ob bei einer fehleingabe das programm abrricht,
	 * da der passende fall zur folge h�tte, dass das programm anl�uft
	 */
	@Test
	(expected = java.lang.IllegalArgumentException.class)
	public void testMissingValue() {
		String[] args = {"--lager","--lol"};
		Simulation s = new Simulation(args);
	}
}
