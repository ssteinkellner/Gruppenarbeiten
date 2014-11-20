package common;

public class Output {
	
	public static void println(String text){
		System.out.println("[OUTPUT]\t" + text);
	}

	public static void println(Number number){
		println(""+number);
	}
	
	public static void debug(String text){
		String pos = getPosition();
		System.out.println("[DEBUG][" + pos + "]\t" + text);
	}

	public static void debug(Number number){
		debug(""+number);
	}
	
	public static void error(String text){
		String pos = getPosition();
		System.err.println("[ERROR][" + pos + "]\t" + text);
	}
	
	private static String getPosition(){
		StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
		String pos = ste.getClassName() + ":" + ste.getLineNumber();
		return pos;
	}
}
