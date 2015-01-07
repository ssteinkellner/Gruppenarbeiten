package factory;

import interfaces.Createable;
import interfaces.Factory;
import server.Balancer;
import server.PiServer;
import exceptions.NotAvailableException;

/**
 * factory, die einen server erstellt
 * @author SSteinkellner
 * @version 2015.01.07
 */
public class ServerFactory implements Factory {

	/**
	 * @see interfaces.Factory#create(java.lang.String)
	 */
	public Createable create(String name) throws NotAvailableException{
		switch(name.toLowerCase()){
			case("piserver"): return new PiServer();
			case("balancer"): return new Balancer();
		}
		return null;
	}

	/**
	 * @see interfaces.Factory#getTypeList()
	 */
	public String getTypeList() {
		return "PiServer, Balancer";
	}
}
