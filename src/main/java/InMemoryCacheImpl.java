/**
 * 
 */
package main.java;

import java.util.LinkedHashMap;
import java.util.concurrent.locks.StampedLock;

import main.java.listener.CacheEvacutionNotificationListener;
import main.java.listener.Notification;
import main.java.listener.NotificationService;

/**
 * @author rahulkatare
 *
 */
public class InMemoryCacheImpl implements InMemoryCache {

	private int cacheSize;
	private long writeExpireAfter;
	private long readExpireAfter;
	private LinkedHashMap<Object, Object> cacheStore;
	private StampedLock lock = new StampedLock();
	private NotificationService notificationService = new NotificationService();

	public InMemoryCacheImpl(int cacheSize, long writeExpireAfter, long readExpireAfter, int totalCacheSize) {
		this.cacheSize = cacheSize;
		this.writeExpireAfter = writeExpireAfter;
		this.readExpireAfter = readExpireAfter;
		this.cacheStore = new LinkedHashMap<Object, Object>(totalCacheSize);
		notificationService.registerListener(new CacheEvacutionNotificationListener());
	}

	@Override
	public void put(Object key, Object value) {
		long stamp = lock.writeLock();
		try {
			if (this.cacheStore.size() >= cacheSize) {
				Object mapKey = this.cacheStore.keySet().iterator().next();
				Object mapValue = this.cacheStore.get(key);
				System.out.println("mapKey" + mapKey + "mapValue-->>" + mapValue);
				this.cacheStore.remove(mapKey);
				Notification notification = new Notification(mapKey.toString(), "size evacution");
				notificationService.addNotification(notification);
			}
			cacheStore.put(key, new CacheValueHolder(value));
			validateCacheValueHolder(key, writeExpireAfter);

		} finally {
			lock.unlockWrite(stamp);
		}
	}

	@Override
	public Object get(Object key) {
		long stamp = lock.readLock();
		try {
			CacheValueHolder cacheValueHolder = validateCacheValueHolder(key, readExpireAfter);
			return cacheValueHolder == null ? null : cacheValueHolder.payload;
		} finally {
			lock.unlockRead(stamp);
		}
	}

	@Override
	public void remove(Object key) {
		long stamp = lock.writeLock();
		try {
			cacheStore.remove(key);
		} finally {
			lock.unlockWrite(stamp);
		}

	}

	/**
	 * get cache value and validate the expireAfter in milliseconds
	 * 
	 * @param key
	 * @param expireAfter
	 * @return
	 */
	private CacheValueHolder validateCacheValueHolder(Object key, long expireAfter) {
		CacheValueHolder cacheValueHolder = (CacheValueHolder) cacheStore.get(key);
		if (cacheValueHolder == null) {
			return null;
		}
		synchronized (cacheValueHolder) {
			if (System.currentTimeMillis() - cacheValueHolder.initTimeInMilli > expireAfter) {
				cacheStore.remove(key);
				System.out.println("expired the key--->>" + key);
				Notification notification = new Notification(key.toString(), "expire after evacution");
				notificationService.addNotification(notification);
				return null;
			}
		}
		return cacheValueHolder;
	}

}
