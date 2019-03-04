/**
 * 
 */
package test.java;

import main.java.InMemoryCache;

/**
 * @author rahulkatare
 *
 */
public class InMemoryMultiThreadTest implements Runnable {
	private InMemoryCache c;

	public InMemoryMultiThreadTest(InMemoryCache cache) {
		this.c = cache;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				c.put(Integer.toString(i), i);
				Thread.sleep(1000);
				System.out.println(("Thread name-->" + Thread.currentThread().getName() + " cache key--->" + i
						+ " cache Value--->" + c.get(Integer.toString(i))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
