package tgm.sew.hit.roboterfabrik;

import org.apache.log4j.BasicConfigurator;

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
	
	public Simulation(String[] parameter){
/*		int[] tempi = {0,0,0};
		String[] temps = new String[2];*/
		sekretariat = new Sekretariat();
		
		for(int i=0;i<parameter.length;i++){
			if(parameter[i].equalsIgnoreCase("--lager")){
				stopIfNoValue(parameter, i);
				i++;
				Sekretariat.getBauplan().setPartPath(parameter[i]);
//				temps[0] = parameter[i];
			}else if(parameter[i].equalsIgnoreCase("--logs")){
				stopIfNoValue(parameter, i);
				i++;
				Sekretariat.getBauplan().setLogPath(parameter[i]);
//				temps[1] = parameter[i];
			}else if(parameter[i].equalsIgnoreCase("--laufzeit")){
				stopIfNoValue(parameter, i);
				i++;
				sekretariat.setRuntime(Integer.parseInt(cutNumbers(parameter[i])));
//				tempi[0] = Integer.parseInt(cutNumbers(parameter[i]));
			}else if(parameter[i].equalsIgnoreCase("--lieferanten")){
				stopIfNoValue(parameter, i);
				i++;
				sekretariat.setLieferantCount(Integer.parseInt(cutNumbers(parameter[i])));
//				tempi[1] = Integer.parseInt(cutNumbers(parameter[i]));
			}else if(parameter[i].equalsIgnoreCase("--monteure")){
				stopIfNoValue(parameter, i);
				i++;
				sekretariat.setMonteurCount(Integer.parseInt(cutNumbers(parameter[i])));
//				tempi[2] = Integer.parseInt(cutNumbers(parameter[i]));
			}
		}
		
		sekretariat.start();
/*		sekretariat = new Sekretariat(tempi[0], tempi[1], tempi[2]);
		sekretariat.getBauplan().setPartPath(temps[0]);
		sekretariat.getBauplan().setLogPath(temps[1]);*/
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
	 * schneidet durch eine regular expression alles aus einem text, was nicht [0-9] entspricht
	 * <br /> => der text wird auf die enthaltenen Ziffern reduziert
	 * @param text text, der reduziert werden soll
	 * @return alle ziffern, die in dem text vorkommen
	 */
	private String cutNumbers(String text){
		return text.replaceAll("[^0-9]", "");
	}
}
