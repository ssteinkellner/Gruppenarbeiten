package common;


/**
 * eine klasse für die verschiedenen ausgaben
 * erleichtert die einbindung von anderen ausgabemedien (zb. eine loglibrary)
 * @author Steinkellner Sebastian
 * @version 2014.11.20
 */
public class Output {
	
	/**
	 * gibt einen text aus
	 * @param text text der ausgegeben werden soll
	 */
	public static void println(String text){
		System.out.println("[OUTPUT]\t" + text);
	}

	/**
	 * gibt eine zahl aus
	 * @param number zahl die ausgegeben werden soll
	 */
	public static void println(Number number){
		println(""+number);
	}
	
	/**
	 * gibt eine debugnachricht im folgenden format aus
	 * <br>[DEBUG][KlassenName:ZeilenNummer] Nachricht
	 * @param text text der ausgegeben werden soll
	 */
	public static void debug(String text){
		String pos = getPosition();
		System.out.println("[DEBUG][" + pos + "]\t" + text);
	}

	/**
	 * gibt eine debugnachricht im folgenden format aus
	 * <br>[DEBUG][KlassenName:ZeilenNummer] Zahl
	 * @param number Zahl die ausgegeben werden soll
	 */
	public static void debug(Number number){
		debug(""+number);
	}
	
	/**
	 * gibt eine fehlernachricht im folgenden format aus
	 * <br>[ERROR][KlassenName:ZeilenNummer] Nachricht
	 * @param text fehler der ausgegeben werden soll
	 */
	public static void error(String text){
		String pos = getPosition();
		System.err.println("[ERROR][" + pos + "]\t" + text);
	}
	
	/**
	 * gibt die position der aufrufenden methode zurueck
	 * @return position der aufrufenden methode
	 */
	private static String getPosition(){
		StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
		String pos = ste.getClassName() + ":" + ste.getLineNumber();
		return pos;
	}
}
