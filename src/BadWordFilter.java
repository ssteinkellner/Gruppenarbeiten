import java.util.ArrayList;
import java.util.Iterator;
/**
 * Implemntiert Methoden zur Schipfworterkennung und ersetzt diese  
 * @author Alexander Koelbl
 *
 */
public class BadWordFilter implements Recievable, Activatable {

	private Recievable recievable;
	
	private boolean enabled; //Kontrolle, ob man die Funktion des BadWordFilters will
	
	private ArrayList<String> ersetzen;//Liste mit zu ersetztenden Worten
	
	/**
	 * Konstruktor, der die Worte definiert, die nicht erlaubt sind
	 */
	public BadWordFilter(Recievable recievable){
		this.recievable = recievable;
		ersetzen= new ArrayList<String>();
		ersetzen.add("ficken");
		ersetzen.add("Arschloch");
		ersetzen.add("Faggot");
		ersetzen.add("Hurensohn");
		ersetzen.add("Hure");
	}

	/**
	 * Woerter, die nicht erwuenscht sind, werden zensiert
	 * @see Recievable#recieve()
	 */
	public String recieve() {
		String text = recievable.recieve();
		if(enabled == true){
			Iterator<String> i = ersetzen.iterator();
			String temp,temp2,sterne;
			while(i.hasNext()){ //Durch jedes unerwuenschte Wort durchiterieren
				temp = i.next();
				sterne = "";
				for(int j = 0; j < (temp.length()-2); j++){ //
					sterne = sterne + "*";
				}
				temp2 = temp.charAt(0) + sterne + temp.charAt(temp.length()); //Erster und letzter Buchstabe bleiben erhalten, die restlichen Buchstaben werden durch Sterne ersetzt
				text = text.replaceAll(temp, temp2); //Woerter, die unerwuenscht sind werden durch die zensierte Version ersetzt
			}
		}
		return text;
	}


	/**
	 * Setter Methode, die bestimmt, ob man die Funktionalitaet des BadWordFilter aktivieren bzw. deaktivieren will
	 * @see Activatable#setEnabled(boolean)
	 */
	public void setEnabled(boolean active) {
		this.enabled = active;
	}

}
