/**
 * 
 */
package test.java;

import java.util.concurrent.TimeUnit;

import main.java.InMemoryCacheImpl;

/**
 * @author rahulkatare
 *
 */
public class MultiThreadTestMain {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		InMemoryMultiThreadTest clientThread = new InMemoryMultiThreadTest(new InMemoryCacheImpl(1000,1000,1000,1000));
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(clientThread);
			thread.start();
		}
		TimeUnit.SECONDS.sleep(2);
	}

}
