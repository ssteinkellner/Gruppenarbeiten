
public class MoorEnte implements Quakfaehig {
	
	SenderRing senderRing;
	
	public MoorEnte() {
		senderRing = new SenderRing(this);
	}

	public void quaken() {
		System.out.println("Quak");
		benachrichtigeBeobachtende();
	}

	public void registriereBeobachter(Beobachter beobachter) {
		senderRing.registriereBeobachter(beobachter);
	}

	public void benachrichtigeBeobachtende() {
		senderRing.benachrichtigeBeobachtende();
	}

	public String toString() {
		return "Moorente";
	}
}