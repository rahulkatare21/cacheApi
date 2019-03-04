package main.java;

public interface InMemoryCache {

	/**
	 * Cache an object.
	 * @param key unique identifier to retrieve object
	 * @param expireAfter in milliseconds
	 * @param value object to cache
	 */
	public void put(Object key, Object value);

	/**
	 * Fetch an object.
	 * @param key unique identifier to retrieve object
	 * @param expireAfter in milliseconds
	 * @return an object or null in case it isn't stored or it expired
	 */
	public Object get(Object key);

	/**
	 * Remove an object from cache.
	 * @param key unique identifier to retrieve object
	 */
	public void remove(Object key);

}
