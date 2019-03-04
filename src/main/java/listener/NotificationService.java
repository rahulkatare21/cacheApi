/**
 * 
 */
package main.java.listener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rahulkatare
 *
 */
public class NotificationService {
	private List<Notification> notifications = new ArrayList<>();
	private List<CacheEvacutionListener> listeners = new ArrayList<>();

	public void addNotification(Notification notification) {
		this.notifications.add(notification);
		// Notify the list of registered listeners
		this.notifyListeners(notification);
	}

	/**
	 * Register the cache evacuation listener
	 * 
	 * @param listener
	 */
	public void registerListener(CacheEvacutionListener listener) {
		// Add the listener to the list of registered listeners
		this.listeners.add(listener);
	}

	/**
	 * Unregister the cache evacuation listener
	 * 
	 * @param listener
	 */
	public void unregisterListener(CacheEvacutionListener listener) {
		// Remove the listener from the list of the registered listeners
		this.listeners.remove(listener);
	}

	protected void notifyListeners(Notification notification) {
		// Notify each of the listeners in the list of registered listeners
		this.listeners.forEach(listener -> listener.onEvacution(notification));
	}
}
