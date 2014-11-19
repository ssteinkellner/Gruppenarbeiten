import java.util.ArrayList;
import java.util.Iterator;

public class BadWordFilter implements Recievable, Activatable {

	private Recievable recievable;
	
	private boolean enabled;
	
	private ArrayList<String> ersetzen;
	
	public BadWordFilter(){
		ersetzen= new ArrayList<String>();
		ersetzen.add("ficken");
		ersetzen.add("Arschloch");
		ersetzen.add("Faggot");
	}

	/**
	 * @see Recievable#recieve()
	 */
	public String recieve() {
		String text = recievable.recieve();
		if(enabled == true){
			Iterator<String> i = ersetzen.iterator();
			String temp,temp2;
			String sterne;
			while(i.hasNext()){
				temp = i.next();
				sterne = "";
				for(int j = 0; j < (temp.length()-2); j++){
					sterne = sterne + "*";
				}
				temp2 = temp.charAt(0) + sterne + temp.charAt(temp.length());
				text = text.replaceAll(temp, temp2);
			}
		}
		return text;
	}


	/**
	 * @see Activatable#setEnabled(boolean)
	 */
	public void setEnabled(boolean active) {
		this.enabled = active;
	}

}
