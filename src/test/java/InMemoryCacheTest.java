
package test.java;
import junit.framework.TestCase;
import main.java.InMemoryCache;
import main.java.InMemoryCacheImpl;
/**
 * @author rahulkatare
 *
 */
public class InMemoryCacheTest extends TestCase {

	public void testPutGet () {
		InMemoryCache c = new InMemoryCacheImpl(4,60,60,5);
		c.put("key1", "value1");
		assertEquals("value1", c.get("key1"));
		c.put("key2", "value2");
		assertEquals("value2", c.get("key2"));
		c.put("key3", "value3");
		c.put("key4", "value4");
		assertEquals("value3", c.get("key3"));
		assertEquals("value4", c.get("key4"));
	}
	
	public void testExpireAfter () throws InterruptedException {
		InMemoryCache c = new InMemoryCacheImpl(5,1000,1000,5);
		c.put("key1", "value1");
		assertEquals("value1", c.get("key1"));
		Thread.sleep(1500);
		assertNull(c.get("key1"));
		c.put("key2", "value2");
		Thread.sleep(750);
		c.put("key3", "value3");
		Thread.sleep(750);
		assertNull(c.get("key2"));
		assertNotNull(c.get("key3"));
		Thread.sleep(750);
		assertNull(c.get("key3"));
	}
	
	public void testPutSizeEvacutionGet () {
		InMemoryCache c = new InMemoryCacheImpl(3,60*60*1000,60*60*1000,5);
		c.put("key1", "value1");
		c.put("key2", "value2");
		c.put("key3", "value3");
		c.put("key4", "value4");
		assertNull("value1", c.get("key1"));
		assertEquals("value2", c.get("key2"));
		assertEquals("value3", c.get("key3"));
		assertEquals("value4", c.get("key4"));
	}

}
