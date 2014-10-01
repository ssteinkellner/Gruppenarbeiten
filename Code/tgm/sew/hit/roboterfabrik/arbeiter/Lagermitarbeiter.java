package tgm.sew.hit.roboterfabrik.arbeiter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

import tgm.sew.hit.roboterfabrik.Sekretariat;

/**
 * 
 * Der Lagermitarbeiter verwaltet regelmaessig den Ein- und Ausgang des Lagers. Entweder soll der Lagermitarbeiter 
 * neue Teile vom Lieferanten einlagern oder dem Montagemitarbeiter bestimmte Teile uebergeben. Falls er dem 
 * Montagemitarbeiter zu wenige Teile fuer den Zusammenbau eines Threadees gibt, muss er die Teile wieder annehmen und 
 * dem Montagemitarbeiter erneut Teile uebergeben.
 * 
 * @author Martin Kritzl, Stefan Erceg
 * @version 20140927
 *
 */

public class Lagermitarbeiter extends Mitarbeiter {
	
	// Logger wird definiert
	private static Logger logger;
	
	/**
	 * Dem Lagermitarbeiter wird wie jedem anderen Mitarbeiter eine ID zugewiesen und der Logger wird initialisiert.
	 * @param sekretariat sorgt fuer die Zuweisung der ID
	 */
	
	public Lagermitarbeiter(Sekretariat sekretariat) {
		super(sekretariat);
		logger = Logger.getLogger(sekretariat.getBauplan().getLogPath());
	}
	
	/**
	 * liefert dem Lagermitarbeiter die angeforderten Teile
	 * @param part Bestandteil, welcher angefordert wird
	 * @param count Anzahl des Bestandteils
	 * @return
	 */
	
	public synchronized String[] getParts(String part, int count) {
		if (part == null) {
			logger.log(Level.ERROR, "Lagermitarbeiter " + this.getId() + ": Der Part " + part + " konnte nicht gefunden werden");
			return null;
		}
		//Es wird der Filename des dazugehörigen Parts gesucht
		String fileName = Sekretariat.getBauplan().getFile(part);
		
		String[] lines = new String[count];
		
		try {
			//Es wird ein neues RandomAccessFile generiert
			File f = new File(fileName);
			RandomAccessFile raf = new RandomAccessFile(f, "rw");
			//Die Laenge des Files wird ermittelt
			long length = f.length();
			//Wenn nichts mehr in dem File steht, wird dies protokolliert und null zurueckgegeben
			if (length <= 1) {
				raf.close();
				logger.log(Level.INFO, "Lagermitarbeiter " + this.getId() + ": Das File " + fileName + " ist leer");
				return null;
			}
			//Der Zeiger des Files wird ganz am Ende plaziert
			raf.seek(length);
			String s = "";
			int i = 0;
			//Wird so oft ausgeführt, wie es Teile des gleichen Typs braucht
			for (int j = 0; j < count;j++) {
				//Der Pointer wird auf eine Stelle gesetzt die sicher nach dem Namen des Parts ist.
				//Damit kann solange zurückgegangen werden bis der Partname gefunden ist
				length = f.length() - 2*Sekretariat.getBauplan().getPartLength();
				raf.seek(length);
				s="";
				//Solange der gelesene String nicht den Namen des Parts beinhaltet wird der Pointer um eine
				//stelle nach vorne gestellt
				while (!s.contains(part)) {
					i++;
					raf.seek(length-i);
					s = raf.readLine();
				}
				//Wenn der Name gefunden wurde, das heißt eine komplette Zeile gelesen wurde wird
				//der String in lines geschrieben. Danach wird das Ende des Files vor dem Zeilenumbruch gesetzt
				//setLength(urspruengliche Laenge - Laenge des gelesenen Strings - Zeilenumbruch
				raf.seek(length-i);
				lines[j] = raf.readLine();
				raf.setLength(length-i-2);
			}
			
			raf.close();
			
			// Meldung ins Logfile hineinschreiben
			
			logger.log(Level.INFO, "Lagermitarbeiter " + this.getId() + ": Der Part " + part + " wurde " + count + " mal hergegeben");
			
			return lines;
			
		// falls eine Exception auftaucht, wird eine Fehlermeldung ins Logfile hineingeschrieben
		} catch (FileNotFoundException fne) {
			
			logger.log(Level.ERROR, "Lagermitarbeiter " + this.getId() + ": Datei " + fileName + " wurde nicht gefunden");
			return null;
			
		} catch (IOException ioe) {
			
			logger.log(Level.ERROR, "Lagermitarbeiter " + this.getId() + ": IO Fehler im File " + fileName);
			return null;
			
		}

	}
	
	/**
	 * fuegt die Bestandteile vom Lieferanten und die zurueckgegebenen Teile vom Montagemitarbeiter ins Lager hinzu
	 * @param parts Bestandteile, die ins Lager eingelagert werden sollen
	 */

	public synchronized void addParts(String part, String[] parts) {
		
		//Es wird der Filename des dazugehörigen Parts gesucht
		String fileName = Sekretariat.getBauplan().getFile(part);
		//Fals es sich um den Namen Threadee handeln wird der notwendige Path gesetzt
		if (Sekretariat.getBauplan().getProduktName()==part) {
			fileName = Sekretariat.getBauplan().getDeliverPath();
		}
		int count = parts.length;
		try {
			//Es wird ein neues RandomAccessFile generiert
			File f = new File(fileName);
			RandomAccessFile raf = new RandomAccessFile(f, "rw");
			//Wenn die 
			long length = f.length() - 1;
			//Wenn schon Zeilen vorhanden sind, zum Schluss springen
			if (length > 0) {
				raf.seek(length);
			}
			//Schreiben der Parts und davor einen Zeilenumbruch
			for (int i = 0; i<count; i++) {
				raf.writeBytes("\r\n"+parts[i]);
			}
			raf.close();
			
			// Meldung ins Logfile hineinschreiben
			logger.log(Level.INFO, "Lagermitarbeiter " + this.getId() + ": Der Part " + part + " wurde " + count + " mal gelagert");
		
		// falls eine Exception auftaucht, wird eine Fehlermeldung ins Logfile hineingeschrieben
		} catch (FileNotFoundException fne) {
			
			logger.log(Level.ERROR, "Lagermitarbeiter " + this.getId() + ": Datei " + fileName + " wurde nicht gefunden");
			
		} catch (IOException ioe) {
			
			logger.log(Level.ERROR, "Lagermitarbeiter " + this.getId() + ": IO Fehler im File " + fileName);
			
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
