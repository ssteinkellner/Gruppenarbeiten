package common;

import exceptions.NotAvailableException;
import factory.ClientFactory;
import factory.ServerFactory;
import interfaces.Calculator;
import interfaces.Createable;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.AccessControlException;
import java.util.HashMap;
import java.util.Map;

/**
 * eine klasse, die die argumente analysiert und dementsprechend das restliche programm aufsetzt
 * @author SSteinkellner
 * @version 2015.01.07
 */
public class CLI {
	Createable running;

	public CLI(String[] args){
		Map<String, String> values = parseArguments(args);
		//		checkArguments(values);
		useArguments(values);
	}

	private Map<String,String> parseArguments(String[] args){
		HashMap<String,String> values = new HashMap<String,String>();

		for(int i=0;i<args.length;i++){
			if(checkArgument(args[i],"help","h") || args[i].equalsIgnoreCase("help")){
				Output.println("help");
				abort();
			}else if(checkArgument(args[i],"server","s")){
				i++;
				values.put("server",args[i]);
			}else if(checkArgument(args[i],"client","c")){
				i++;
				values.put("client",args[i]);
			}else if(checkArgument(args[i],"host","h")){
				i++;
				values.put("host",args[i]);
			}else if(checkArgument(args[i],"name","n")){
				i++;
				values.put("name",args[i]);
			}
		}

		return values;
	}

	/*	private void checkArguments(Map<String, String> values){}	*/

	private void useArguments(Map<String, String> values){
		if(values.containsKey("server")){
			String name = ((values.containsKey("name"))?values.get("name"):"PiServer");

			ServerFactory f = new ServerFactory();
			try {
				Output.println ("Starting Service.");
				running = f.create(values.get("server"));
				
				if (System.getSecurityManager() == null){
					System.setSecurityManager ( new RMISecurityManager() );
				}
				Naming.bind (name, (Remote) running);
				Output.println ("Service bound.");
			} catch (NotAvailableException e) {
				Output.error("The chosen Server is now available!");
				Output.error("Available Types: " + f.getTypeList());
				abort();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (AlreadyBoundException e) {
				e.printStackTrace();
			} catch(AccessControlException e){
				Output.error("Couldn't bind Naming!");
				e.printStackTrace();
				abort();
			}
		}else if(values.containsKey("client")){
			ClientFactory f = new ClientFactory();
			try {
				running = f.create(values.get("client"));

				if ( System.getSecurityManager() == null ) {
					System.setSecurityManager( new RMISecurityManager() );
				}
				Calculator service = ( Calculator ) Naming.lookup( "rmi://my.host.edu/MyService" );
				Output.println ( service.pi(20) );
			} catch (NotAvailableException e) {
				Output.error("The chosen Client is now available!");
				Output.error("Available Types: " + f.getTypeList());
				abort();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			} catch(AccessControlException e){
				Output.error("Couldn't bind Naming!");
				e.printStackTrace();
				abort();
			}
		}else{
			Output.error("You have to choose, if you want to start a Server or client!");
			abort();
		}
	}

	private boolean checkArgument(String arg, String longName, String shortName){

		if(longName!=null  && !longName.isEmpty()  && arg.equalsIgnoreCase("--"+longName)){ return true; }
		if(shortName!=null && !shortName.isEmpty() && arg.equalsIgnoreCase("-"+shortName)){ return true; }

		return false;
	}

	private void abort(){
		System.exit(0);
	}
}
