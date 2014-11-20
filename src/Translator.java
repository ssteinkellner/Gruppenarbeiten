import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implementiert Methoden, die Woerter der Schreibweise von Chats anpasst
 * @author Alexander Koelbl
 *
 */
public class Translator implements Sendable, Activatable {

	private Sendable sendable;
	
	private boolean enabled; //Kontrolle, ob man die Funktion des Translator will
	
	private ArrayList<String[]> ersetzen; //Liste mit zu ersetztenden Worten
	
	public Translator(Sendable sendable){
		this.sendable = sendable;
		ersetzen= new ArrayList<String[]>();
		ersetzen.add(new String[]{"laecheln","LOL"});
		ersetzen.add(new String[]{"Hallo","Hi"});
		ersetzen.add(new String[]{"lustig","XD"});
	}

	/**
	 * Woerter, die eine andere Schreibweise (wie in Chats ueblich) haben soll, werden ersetzt
	 * @see Sendable#send(java.lang.String)
	 */
	public void send(String text) {
		if(enabled == true){
			Iterator<String[]> i = ersetzen.iterator();
			String[] temp;
			while(i.hasNext()){ //Durch jedes Wort der List ersetzen durchiterieren
				temp = i.next();
				text = text.replaceAll(temp[0], temp[1]); //Text aendern
			}
		}
		sendable.send(text);
	}


	/**
	 * Setter Methode, die bestimmt, ob man die Funktionalitaet des Translator aktivieren bzw. deaktivieren will
	 * @see Activatable#setEnabled(boolean)
	 */
	public void setEnabled(boolean active) {
		this.enabled = active;
	}

}
