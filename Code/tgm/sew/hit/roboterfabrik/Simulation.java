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
		int[] temp = new int[3];
		
		
		for(int i=0;i<parameter.length;i++){
			if(parameter[i].equalsIgnoreCase("--lager")){
				stopIfNoValue(parameter, i);
				
			}else if(parameter[i].equalsIgnoreCase("--logs")){
				stopIfNoValue(parameter, i);
				
			}else if(parameter[i].equalsIgnoreCase("--lieferanten")){
				stopIfNoValue(parameter, i);
				
			}else if(parameter[i].equalsIgnoreCase("--monteure")){
				stopIfNoValue(parameter, i);
				
			}else if(parameter[i].equalsIgnoreCase("--laufzeit")){
				stopIfNoValue(parameter, i);
				
			}
		}
		
		sekreteriat = new Sekretariat(0, 0, 0);
//		sekretariat.getBauplan().setLogPath();
//		sekretariat.getBauplan().setFilePath();
	}

	
	private void stopIfNoValue(String[] parameter, int index){
		if(parameter[index+1].toLowerCase().startsWith("--")){
			throw(new IllegalArgumentException("Nach "+parameter[index]+" muss ein Wert folgen. kein anderer --.*"));
		}
	}
}
