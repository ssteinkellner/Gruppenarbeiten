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
 * Der Lagermitarbeiter verwaltet regelmaeßig den Ein- und Ausgang des Lagers. Entweder soll der Lagermitarbeiter 
 * neue Teile vom Lieferanten einlagern oder dem Montagemitarbeiter bestimmte Teile uebergeben. Falls er dem 
 * Montagemitarbeiter zu wenige Teile fuer den Zusammenbau eines Threadees gibt, muss er die Teile wieder annehmen und 
 * dem Montagemitarbeiter erneut Teile uebergeben.
 * 
 * @author Stefan Erceg
 * @version 20140927
 *
 */

public class Lagermitarbeiter extends Mitarbeiter {

	private static final Logger logger = Logger.getLogger("Ablauf");
	
	/**
	 * Dem Lagermitarbeiter wird wie jedem anderen Mitarbeiter eine ID zugewiesen.
	 * @param sekretariat sorgt fuer die Zuweisung der ID
	 */
	
	public Lagermitarbeiter(Sekretariat sekretariat) {
		super(sekretariat);
	}
	
	/**
	 * liefert dem Lagermitarbeiter die angeforderten Teile
	 * @param part Bestandteil, welcher angefordert wird
	 * @param count Anzahl des Bestandteils
	 * @return
	 */
	
	public String[] getParts(String part, int count) {
		
		String fileName = Sekretariat.getBauplan().getFile(part);
		String[] lines = new String[count];
		LinkedList<String> writeBack = new LinkedList<String>();
		int i=0;
		
		try {
			
			File f = new File(fileName);
			RandomAccessFile raf = new RandomAccessFile(f, "rw");
			
			String line = raf.readLine();
			while(line != null){
				line = raf.readLine();
				if(i<lines.length){
					lines[i]=line;
					i++;
				}else{
					writeBack.add(line);
				}
			}
			
			Iterator<String> it = writeBack.iterator();
			while(it.hasNext()) {
				raf.writeUTF(it.next());
			}
			
			raf.close();
			
			logger.log(Level.INFO, "Der Part" + part + " wurde " + count + " mal hergegeben");
		
		} catch (FileNotFoundException fne) {
			
			logger.log(Level.ERROR, "Datei wurde nicht gefunden");
			
		} catch (IOException ioe) {
			
			logger.log(Level.ERROR, "IO Fehler");
			
		}
		
		return null;

	}
	
	/**
	 * fügt die Bestandteile vom Lieferanten und die zurückgegebenen Teile vom Montagemitarbeiter ins Lager hinzu
	 * @param parts Bestandteile, die ins Lager eingelagert werden sollen
	 */

	public void addParts(String part, String[] parts) {
		
		String fileName = Sekretariat.getBauplan().getFile(part);
		
		try {
			
			File f = new File(fileName);
			RandomAccessFile raf = new RandomAccessFile(f, "rw");
			
			// ans ende der datei laufen
			String line = raf.readLine();
			while(line != null){ line = raf.readLine(); }
			
			//zeilen dazuschreiben
			for(int i=0;i<parts.length;i++){
				raf.writeUTF(parts[i]);
			}
			
			raf.close();
			
			logger.log(Level.INFO, part + " wurde gelagert");
			
		} catch (FileNotFoundException fne) {
			
			logger.log(Level.ERROR, "Datei wurde nicht gefunden");
			
		} catch (IOException ioe) {
			
			logger.log(Level.ERROR, "IO Fehler");
			
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
