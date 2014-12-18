/**
 * Simulator, der die verschiedenen objekte erzeugt und die methoden ausfuehrt
 * @author TLins
 */
public class EntenSimulator {
	/**
	 * programmeinstiegspunkt
	 * @param args UNUSED
	 */
	public static void main(String[] args) {
		EntenSimulator simulator = new EntenSimulator();
		AbstraktEntenFabrik entenFabrik = new ZaehlendeEntenFabrik();
		simulator.simulieren(entenFabrik);
	}

	/**
	 * simuliert einen programmablauf
	 * @param entenfabrik konkrete entenfabrik, die verwendet werden soll
	 */
	void simulieren(AbstraktEntenFabrik entenfabrik) {
		Quakfaehig moorEnte = entenfabrik.erzeugeMoorEnte();
		Quakfaehig lockPfeife = entenfabrik.erzeugeLockPfeife();
		Quakfaehig gummiEnte = entenfabrik.erzeugeGummiEnte();
		Quakfaehig gansEnte = new GansAdapter(new Gans());

		Schar EntenSchar = new Schar();

		EntenSchar.hinzufügen(moorEnte);
		EntenSchar.hinzufügen(lockPfeife);
		EntenSchar.hinzufügen(gummiEnte);
		EntenSchar.hinzufügen(gansEnte);

		Schar stockEntenSchar = new Schar();

		Quakfaehig stockEnte1 = entenfabrik.erzeugeStockEnte();
		Quakfaehig stockEnte2 = entenfabrik.erzeugeStockEnte();
		Quakfaehig stockEnte3 = entenfabrik.erzeugeStockEnte();
		Quakfaehig stockEnte4 = entenfabrik.erzeugeStockEnte();

		stockEntenSchar.hinzufügen(stockEnte1);
		stockEntenSchar.hinzufügen(stockEnte2);
		stockEntenSchar.hinzufügen(stockEnte3);
		stockEntenSchar.hinzufügen(stockEnte4);
		EntenSchar.hinzufügen(stockEntenSchar);

		System.out.println("\nEntensimulator: mit Observer");
		
		Quakologe quakologe = new Quakologe();
		EntenSchar.registriereBeobachter(quakologe);
		simulieren(EntenSchar);
		System.out.println("\nDie Enten haben " +
				QuakZaehler.getQuaks() +
				"-mal gequakt.");
		}

	/**
	 * simuliert eine einzelne ente
	 * @param ente konkrete ente, die simuliert werden soll
	 */
	void simulieren(Quakfaehig ente) {
		ente.quaken();
	}
}
