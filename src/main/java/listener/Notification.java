/**
 * 
 */
package main.java.listener;

/**
 * @author rahulkatare
 *
 */
public class Notification {

	/**
	 * cache key name
	 */
	private String keyName;

	/**
	 * notification message
	 */
	private String message;

	public Notification(String keyName, String message) {
		this.keyName = keyName;
		this.message = message;
	}

	/**
	 * @return the keyName
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * @param keyName
	 *            the keyName to set
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
