package tgm.sew.hit.roboterfabrik.statisch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Klasse zum allgemeinen zugriff auf files
 * @author Steinkellner Sebastian
 * @version 140924
 */
public class Dateizugriff {

	public synchronized String[] extractLines(String part, int count) {
		String fileName = Bauplan.getFile(part);
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
			while(it.hasNext()){
				raf.writeUTF(it.next());
			}
		} catch (FileNotFoundException fne) {
			fne.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return null;
	}

	public synchronized void addLines(String part, String[] parts) {
		String fileName = Bauplan.getFile(part);
		
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
		} catch (FileNotFoundException fne) {
			fne.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
