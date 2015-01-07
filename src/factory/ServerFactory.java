package factory;

import factory.interfaces.Factory;

public class ServerFactory implements Factory {


	/**
	 * @see factory.interfaces.Factory#create(java.lang.String)
	 */
	public Creatable create(String name) {
		return null;
	}


	/**
	 * @see factory.interfaces.Factory#getTypeList()
	 */
	public String getTypeList() {
		return null;
	}

}
