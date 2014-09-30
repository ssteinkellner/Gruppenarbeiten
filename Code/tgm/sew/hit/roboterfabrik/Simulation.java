package tgm.sew.hit.roboterfabrik;

/**
 * Klasse zum starten des programms
 * @author Steinkellner Sebastian
 * @version 140924
 */
public class Simulation {

	private static Sekretariat sekreteriat;

	public static void main(String[] args) {
		new Simulation(args);
	}
	
	public Simulation(String[] parameter){
		int[] tempi = {0,0,0};
		String[] temps = new String[2];
		
		for(int i=0;i<parameter.length;i++){
			if(parameter[i].equalsIgnoreCase("--lager")){
				stopIfNoValue(parameter, i);
				i++;
				temps[0] = parameter[i];
			}else if(parameter[i].equalsIgnoreCase("--logs")){
				stopIfNoValue(parameter, i);
				i++;
				temps[1] = parameter[i];
			}else if(parameter[i].equalsIgnoreCase("--laufzeit")){
				stopIfNoValue(parameter, i);
				i++;
				tempi[0] = Integer.parseInt(cutNumbers(parameter[i]));
			}else if(parameter[i].equalsIgnoreCase("--lieferanten")){
				stopIfNoValue(parameter, i);
				i++;
				tempi[1] = Integer.parseInt(cutNumbers(parameter[i]));
			}else if(parameter[i].equalsIgnoreCase("--monteure")){
				stopIfNoValue(parameter, i);
				i++;
				tempi[2] = Integer.parseInt(cutNumbers(parameter[i]));
			}
		}
		
		sekreteriat = new Sekretariat(tempi[0], tempi[1], tempi[2]);
		sekreteriat.getBauplan().setPartPath(temps[0]);
		sekreteriat.getBauplan().setLogPath(temps[1]);
	}

	
	private void stopIfNoValue(String[] parameter, int index){
		if(parameter[index+1].toLowerCase().startsWith("--")){
			throw(new IllegalArgumentException("Nach "+parameter[index]+" muss ein Wert folgen. kein anderer --.*"));
		}
	}
	
	private String cutNumbers(String text){
		return text.replaceAll("[^0-9]", "");
	}
}
