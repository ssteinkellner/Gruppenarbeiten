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
 * @author Stefan Erceg
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
	
	public String[] getParts(String part, int count) {
		
		// gewuenschte Datei, in die Zeilen hinzugefuegt werden sollen, wird uebergeben
		String fileName = Sekretariat.getBauplan().getFile(part);
		
		String[] lines = new String[count];
		LinkedList<String> writeBack = new LinkedList<String>();
		
		try {
			
			File f = new File(fileName);
			RandomAccessFile raf = new RandomAccessFile(f, "rw");
			
			long length = f.length() - 1;
			raf.seek(length);
			String s = "";
			int i = 0;
			for (int j = 0; j < 2;j++) {
				while (!s.contains("arm")) {
					i++;
					raf.seek(length-i);
					s = raf.readLine();
				}
				raf.seek(length-i);
				lines[i] = raf.readLine();
				raf.setLength(length-i-2);
			}
			
			raf.close();
			
			// Meldung ins Logfile hineinschreiben
			logger.log(Level.INFO, "Der Part" + part + " wurde " + count + " mal hergegeben");
			
			return lines;
			
		// falls eine Exception auftaucht, wird eine Fehlermeldung ins Logfile hineingeschrieben
		} catch (FileNotFoundException fne) {
			
			logger.log(Level.ERROR, "Datei " + part + " wurde nicht gefunden");
			return null;
			
		} catch (IOException ioe) {
			
			logger.log(Level.ERROR, "IO Fehler");
			return null;
			
		}

	}
	
	/**
	 * fuegt die Bestandteile vom Lieferanten und die zurueckgegebenen Teile vom Montagemitarbeiter ins Lager hinzu
	 * @param parts Bestandteile, die ins Lager eingelagert werden sollen
	 */

	public void addParts(String part, String[] parts) {
		
		// gewuenschte Datei, in die Zeilen hinzugefuegt werden sollen, wird uebergeben
		String fileName = Sekretariat.getBauplan().getFile(part);
		
		try {
			
			File f = new File(fileName);
			RandomAccessFile raf = new RandomAccessFile(f, "rw");
			
			long length = f.length() - 1;
			raf.seek(length);
			for (int i = 0; i<1; i++) {
				raf.writeBytes("\r\n"+parts[i]);
			}
			raf.close();
			
			// Meldung ins Logfile hineinschreiben
			logger.log(Level.INFO, part + " wurde gelagert");
		
		// falls eine Exception auftaucht, wird eine Fehlermeldung ins Logfile hineingeschrieben
		} catch (FileNotFoundException fne) {
			
			logger.log(Level.ERROR, "Datei " + part + " wurde nicht gefunden");
			
		} catch (IOException ioe) {
			
			logger.log(Level.ERROR, "IO Fehler");
			
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
