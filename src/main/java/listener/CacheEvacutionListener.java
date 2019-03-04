/**
 * 
 */
package main.java.listener;

/**
 * @author rahulkatare
 *
 */
public interface CacheEvacutionListener {
	
	/**
	 * On cache evacution send notification
	 * @param notification
	 */
	public void onEvacution(Notification notification);
}
