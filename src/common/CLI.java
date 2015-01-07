package common;

import exceptions.NotAvailableException;
import factory.ClientFactory;
import factory.ServerFactory;
import interfaces.Createable;

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
	
	private Map<String, String> parseArguments(String[] args){
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
			}
		}
		
		return values;
	}

/*	private void checkArguments(Map<String, String> values){}	*/
	
	private void useArguments(Map<String, String> values){
		if(values.containsKey("server")){
			try {
				running = new ServerFactory().create(values.get("server"));
			} catch (NotAvailableException e) {
				Output.error("The chosen server is not available!");
				abort();
			}
		}else if(values.containsKey("client")){
			try {
				running = new ClientFactory().create(values.get("client"));
			} catch (NotAvailableException e) {
				Output.error("The chosen client is not available!");
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
