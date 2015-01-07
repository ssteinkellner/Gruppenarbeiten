package interfaces;

public interface Factory {

	public abstract Createable create(String name);

	public abstract String getTypeList();

}
