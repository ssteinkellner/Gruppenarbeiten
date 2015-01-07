package factory;

import server.Balancer;
import server.PiServer;
import exceptions.NotAvailableException;
import interfaces.Createable;
import interfaces.Factory;

/**
 * factory, die einen client erstellt
 * @author SSteinkellner
 * @version 2015.01.07
 */
public class ClientFactory implements Factory {

	/**
	 * @see interfaces.Factory#create(java.lang.String)
	 */
	public Createable create(String name) throws NotAvailableException{
		switch(name.toLowerCase()){
		case("simple"): return new PiServer();
		case("graphic"): return new Balancer();
	}
		return null;
	}

	/**
	 * @see interfaces.Factory#getTypeList()
	 */
	public String getTypeList() {
		return "simple, graphic";
	}
}
