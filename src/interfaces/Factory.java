package interfaces;

import exceptions.NotAvailableException;

public interface Factory {

	public abstract Createable create(String name) throws NotAvailableException;

	public abstract String getTypeList();

}
