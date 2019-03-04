/**
 * 
 */
package main.java.listener;

/**
 * @author rahulkatare
 *
 */
public class CacheEvacutionNotificationListener implements CacheEvacutionListener {

	@Override
	public void onEvacution(Notification notification) {
		System.out.println("key is evacuted '" + notification.getKeyName() + "'");
		System.out.println("notification message '" + notification.getMessage() + "'");
	}

}
