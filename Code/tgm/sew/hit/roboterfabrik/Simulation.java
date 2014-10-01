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
	 * übernimmt die parameter, erstellt ein neues sekretariat
	 * <br>und setzt die werte in sekretariat und bauplan ein
	 * @param parameter String[] mit parametern nach folgendem muster:
	 * <br> --prefix1 wert1 --prefix2 wert2 ...
	 */
	public Simulation(String[] parameter){
		sekretariat = new Sekretariat();
		
		for(int i=0;i<parameter.length;i++){
			if(parameter[i].toLowerCase().contains("help")){
				String text = "- - - <[ Roboterfabrik help ]> - - -"
						+ "\n parameter     | Beschreibung              | Beispiel"
						+ "\n --lager       | muss mit einem '/' enden  | /pfad/zum/lager/"
						+ "\n --logs        | muss mit einem '/' enden  | /pfad/zum/loggen/"
						+ "\n --laufzeit    | laufzeit in millisekunden | 10000"
						+ "\n --mounteure   | anzahl der mounteure      | 25"
						+ "\n --lieferanten | anzahl der lieferanten    | 12";
				System.out.println(text);
				System.exit(0);
			}else if(parameter[i].equalsIgnoreCase("--lager")){
				stopIfNoValue(parameter, i);
				i++;
				stopIfNotPath(parameter[i]);
				Sekretariat.getBauplan().setPartPath(parameter[i]);
				Sekretariat.getBauplan().setDeliverPath(parameter[i]+"auslieferung.csv");
			}else if(parameter[i].equalsIgnoreCase("--logs")){
				stopIfNoValue(parameter, i);
				i++;
				stopIfNotPath(parameter[i]);
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
	 * @param index index, des parameterprefixes, dessen zugehöriger wert überprüft werden soll
	 */
	private void stopIfNoValue(String[] parameter, int index){
		if(parameter[index+1].toLowerCase().startsWith("--")){
			throw new IllegalArgumentException("Nach "+parameter[index]+" muss ein Wert folgen. kein anderer --.*");
		}
	}
	
	/**
	 * eine methode, die eine IllegalArgumentException wirft,
	 * wenn ein pfad ohne abschließendes '/' angegeben wurde
	 * @param parameter parameter, der überprüft werden soll
	 */
	private void stopIfNotPath(String parameter){
		if(!parameter.substring(parameter.length()-1).contains("/")){
			throw new IllegalArgumentException("ein Pfad muss mit '/' enden!");
		}
	}
	
	/**
	 * schneidet durch eine regular expression alles aus einem text, was nicht [0-9] entspricht
	 * <br> => der text wird auf die enthaltenen Ziffern reduziert
	 * @param text text, der reduziert werden soll
	 * @return alle ziffern, die in dem text vorkommen
	 */
	private String cutNumbers(String text){
		return "0"+text.replaceAll("[^0-9]", "");
	}
}
