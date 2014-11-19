import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author Alex
 *
 */
public class Translator implements Sendable, Activatable {

	private Sendable sendable;
	
	private boolean enabled;
	
	private ArrayList<String[]> ersetzen;
	
	public Translator(){
		ersetzen= new ArrayList<String[]>();
		ersetzen.add(new String[]{"lächeln","LOL"});
		ersetzen.add(new String[]{"laecheln","LOL"});
	}

	/**
	 * @see Sendable#send(java.lang.String)
	 */
	public void send(String text) {
		if(enabled == true){
			Iterator<String[]> i = ersetzen.iterator();
			String[] temp;
			while(i.hasNext()){
				temp = i.next();
				text = text.replaceAll(temp[0], temp[1]);
			}
		}
		sendable.send(text);
	}


	/**
	 * @see Activatable#setEnabled(boolean)
	 */
	public void setEnabled(boolean active) {
		this.enabled = active;
	}

}
