package factory;

import interfaces.Client;
import interfaces.Factory;
import client.GraphicClient;
import client.SimpleClient;
import exceptions.NotAvailableException;

/**
 * factory, die einen client erstellt
 * @author SSteinkellner
 * @version 2015.01.07
 */
public class ClientFactory implements Factory {

	/**
	 * @see interfaces.Factory#create(java.lang.String)
	 */
	public Client create(String name) throws NotAvailableException{
		switch(name.toLowerCase()){
		case("simple"): return new SimpleClient();
		case("graphic"): return new GraphicClient();
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
