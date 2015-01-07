package interfaces;

public interface Factory {

	public abstract Creatable create(String name);

	public abstract String getTypeList();

}
