package tgm.sew.hit.roboterfabrik.arbeiter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.statisch.Bauplan;
import tgm.sew.hit.roboterfabrik.statisch.Log;

/**
 * 
 * Der Lagermitarbeiter verwaltet regelmaeﬂig den Ein- und Ausgang des Lagers. Entweder soll der Lagermitarbeiter neue Teile vom Lieferanten einlagern oder
 * dem Montagemitarbeiter bestimmte Teile uebergeben. Falls er dem Montagemitarbeiter zu wenige Teile fuer den Zusammenbau eines Threadees gibt, muss er
 * die Teile wieder annehmen und dem Montagemitarbeiter erneut Teile uebergeben
 * 
 * @author Stefan Erceg
 * @version 20140927
 *
 */

public class Lagermitarbeiter extends Mitarbeiter {

	/**
	 * Dem Lagermitarbeiter wird wie jedem anderen Mitarbeiter eine ID zugewiesen.
	 * @param sekretariat sorgt fuer die Zuweisung der ID
	 */
	
	public Lagermitarbeiter(Sekretariat sekretariat) {
		super(sekretariat);
	}
	
	/**
	 * 
	 * @param part
	 * @param count
	 * @return
	 */
	
	public String[] getParts(String part, int count) {
		File f=new File(Bauplan.getLogPath()+File.separator+part.toLowerCase()+".csv");
		Log.add("Das Bestandteil " + part + " wurde " + count + "mal hergegeben");
		BufferedReader br = new BufferedReader(new FileReader(f));
		try {
			String line = br.readLine();
            br.close();
            if (line == null || line.equals("")) {
                return null;
            }
            removeLine(f);
            String[] werte = line.split(",");
            for (int a = 1; a < werte.length; a++) {
                li.add(Integer.parseInt(werte[a]));
            }
            return new Teil(t,li);
        } catch (FileNotFoundException ex) {
            logger.log(Level.ERROR, "File wurde nicht gefunden");
        } catch (IOException ex) {
            logger.log(Level.ERROR, "Fehler beim bearbeiten der Datei");
        }
		return new String(part,count);
		}
	}
	
	/**
	 * 
	 * @param parts
	 */

	public void addParts(String[] parts) {
		File f=new File(Bauplan.getLogPath()+File.separator+parts[i].toLowerCase()+".csv");
		
	}

	/**
	 * 
	 */
	
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
