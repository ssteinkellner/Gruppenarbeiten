package tgm.sew.hit.roboterfabrik;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.SimpleLayout;

/**
 * Klasse zum starten des programms
 * @author Steinkellner Sebastian
 * @version 140924
 */
public class Simulation {

	private static Sekretariat sekretariat;

	public static void main(String[] args) {
		BasicConfigurator.configure();
		new Simulation(args);
	}
	
	/**
	 * �bernimmt die parameter, erstellt ein neues sekretariat
	 * <br />und setzt die werte in sekretariat und bauplan ein
	 * @param parameter String[] mit parametern nach folgendem muster:
	 * <br /> --prefix1 wert1 --prefix2 wert2 ...
	 */
	public Simulation(String[] parameter){
		sekretariat = new Sekretariat();
		
		for(int i=0;i<parameter.length;i++){
			if(parameter[i].equalsIgnoreCase("--lager")){
				stopIfNoValue(parameter, i);
				i++;
				Sekretariat.getBauplan().setPartPath(parameter[i]);
				Sekretariat.getBauplan().setDeliverPath(parameter[i]+"auslieferung.csv");
			}else if(parameter[i].equalsIgnoreCase("--logs")){
				stopIfNoValue(parameter, i);
				i++;
				Sekretariat.getBauplan().setLogPath(parameter[i]);
			}else if(parameter[i].equalsIgnoreCase("--laufzeit")){
				stopIfNoValue(parameter, i);
				i++;
				sekretariat.setRuntime(Integer.parseInt(cutNumbers(parameter[i])));
			}else if(parameter[i].equalsIgnoreCase("--lieferanten")){
				stopIfNoValue(parameter, i);
				i++;
				sekretariat.setLieferantCount(Integer.parseInt(cutNumbers(parameter[i])));
			}else if(parameter[i].equalsIgnoreCase("--monteure")){
				stopIfNoValue(parameter, i);
				i++;
				sekretariat.setMonteurCount(Integer.parseInt(cutNumbers(parameter[i])));
			}
		}
		
		sekretariat.start();
	}

	/**
	 * eine methode, die eine IllegalArgumentException wirft,
	 * wenn ein nach einem parameter-prefix ('--') kein wert angegeben wird
	 * @param parameter liste der parameter
	 * @param index index, des parameterprefixes, dessen zugeh�riger wert �berpr�ft werden soll
	 */
	private void stopIfNoValue(String[] parameter, int index){
		if(parameter[index+1].toLowerCase().startsWith("--")){
			throw new IllegalArgumentException("Nach "+parameter[index]+" muss ein Wert folgen. kein anderer --.*");
		}
	}
	
	/**
	 * schneidet durch eine regular expression alles aus einem text, was nicht [0-9] entspricht
	 * <br /> => der text wird auf die enthaltenen Ziffern reduziert
	 * @param text text, der reduziert werden soll
	 * @return alle ziffern, die in dem text vorkommen
	 */
	private String cutNumbers(String text){
		return "0"+text.replaceAll("[^0-9]", "");
	}
}
