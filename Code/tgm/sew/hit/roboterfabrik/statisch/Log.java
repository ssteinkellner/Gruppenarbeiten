package tgm.sew.hit.roboterfabrik.statisch;

/**
 * 
 * Die Klasse "Log"
 * 
 * @author Stefan Erceg
 * @version1 20140925
 * @version2 20140927
 *
 */

public class Log {

	private static Dateizugriff logFile;

	public static synchronized void add(String text) {
		Option oLogVerzeichnis = obuilder.withLongName("logs").withShortName("o").withRequired(true).withDescription("Verzeichnis des Log-Files")
				.withArgument(abuilder.withName("verzeichnis").withMinimum(1).withMaximum(1).create()).create();
		
		if(cl.hasOption(oLogVerzeichnis)) {
			String pfad = (String) cl.getValue(oLogVerzeichnis);
			File temp = new File(pfad);
			if(!temp.exists())temp.mkdirs();
			if(temp.exists() && temp.isDirectory()){
				try{
					File f=new File(pfad+File.separator+"log.txt");
					f.createNewFile();
				}catch(Exception e){
					e.printStackTrace();
					throw new IllegalArgumentException("Ungueltiger Pfad fuer den Log-Ordner");
				}
				this.logVerzeichnis = pfad;
			}else
				throw new IllegalArgumentException("Ungueltiger Pfad fuer den Log-Ordner!");
		}
	}

}
