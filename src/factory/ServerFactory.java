package factory;

import java.rmi.RemoteException;

import interfaces.Factory;
import interfaces.Server;
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
	public Server create(String name) throws NotAvailableException{
		switch(name.toLowerCase()){
			case("piserver"): try {
				return new PiServer();
			} catch (RemoteException e) {
				e.printStackTrace();
				
			}
			case("balancer"): return new Balancer();
		}
		throw new NotAvailableException();
	}

	/**
	 * @see interfaces.Factory#getTypeList()
	 */
	public String getTypeList() {
		return "PiServer, Balancer";
	}
}
