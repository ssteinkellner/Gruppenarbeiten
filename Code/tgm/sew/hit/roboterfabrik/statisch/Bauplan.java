package tgm.sew.hit.roboterfabrik.statisch;

import java.util.HashMap;

public class Bauplan {

	private volatile HashMap parts;

	private volatile HashMap files;

	private volatile String logPath;

	private volatile String partPath;

	public Bauplan(String path) {

	}

	public void setPartCount(String name, int count) {

	}

	public void setFile(String name, String fileName) {

	}

	public void setLogPath(String path) {

	}

	public void setPartPath(String path) {

	}

	public synchronized int getPartCount(String name) {
		return 0;
	}

	public synchronized String getFile(String name) {
		return null;
	}

	public synchronized String getLogPath() {
		return null;
	}

}
