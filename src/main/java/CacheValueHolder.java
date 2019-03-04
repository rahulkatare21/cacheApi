package main.java;

/**
 * @author rahulkatare
 *
 */
public final class CacheValueHolder {
	long initTimeInMilli;
	Object payload;

	public CacheValueHolder(Object payload) {
		this.initTimeInMilli = System.currentTimeMillis();
		this.payload = payload;
	}
}
