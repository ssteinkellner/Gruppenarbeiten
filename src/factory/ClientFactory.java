package factory;

import interfaces.Factory;

public class ClientFactory implements Factory {


	/**
	 * @see interfaces.Factory#create(java.lang.String)
	 */
	public Creatable create(String name) {
		return null;
	}


	/**
	 * @see interfaces.Factory#getTypeList()
	 */
	public String getTypeList() {
		return null;
	}

}
