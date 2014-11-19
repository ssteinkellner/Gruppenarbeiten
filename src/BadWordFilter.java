public class BadWordFilter implements Recievable, Activatable {

	private Recievable recievable;
	
	private boolean enabled;


	/**
	 * @see Recievable#recieve()
	 */
	public String recieve() {
		return null;
	}


	/**
	 * @see Activatable#setEnabled(boolean)
	 */
	public void setEnabled(boolean active) {
		this.enabled = active;
	}

}
