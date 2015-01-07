package factory;

import interfaces.Createable;
import interfaces.Factory;

public class ClientFactory implements Factory {


	/**
	 * @see interfaces.Factory#create(java.lang.String)
	 */
	public Createable create(String name) {
		return null;
	}


	/**
	 * @see interfaces.Factory#getTypeList()
	 */
	public String getTypeList() {
		return null;
	}

}
