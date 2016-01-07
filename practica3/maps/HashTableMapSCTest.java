package practica3.maps;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class HashTableMapSCTest {

	 public void setUp(Map<Integer, String> testMap) throws Exception {
	        testMap.put(1, "uno");
	        testMap.put(2, "dos");
	        testMap.put(3, "tres");
	        testMap.put(4, "cuatro");
	        testMap.put(5, "cinco");
	        testMap.put(6, "seis");
	        testMap.put(7, "siete");
	        testMap.put(8, "ocho");
	        testMap.put(9, "nueve");
	        testMap.put(10, "diez");
	        testMap.put(11, "once");
	        testMap.put(12, "doce");
	        testMap.put(13, "trece");
	        testMap.put(14, "catorce");
	        testMap.put(15, "quince");
	    }

	    @Test
	    public void testSize() throws Exception {
	        Map<Integer, String> testMap = new HashTableMapSC<Integer, String>();
	        assertEquals(testMap.size(), 0);
	    }

	    @Test
	    public void testIsEmpty() throws Exception {
	        Map<Integer, String> testMap = new HashTableMapSC<Integer, String>();
	        assertTrue(testMap.isEmpty());
	    }

	    @Test
	    public void testKeySet() throws Exception {
	        Map<Integer, String> testMap = new HashTableMapSC<Integer, String>();
	        Iterable<Integer> keySet = testMap.keys();
	        Iterator<Integer> iterator = keySet.iterator();
	        List<Integer> keyList = new ArrayList<Integer>();
	        while (iterator.hasNext()) {
	            keyList.add(iterator.next());
	        }
	        assertEquals(keyList.size(), 0);
	    }

	    @Test
	    public void testPut() throws Exception {

	        Map<Integer, String> testMap = new HashTableMapSC<Integer, String>();
	        assertNull(testMap.put(5, "cinco"));
	        assertNull(testMap.put(6, "seis"));
	        assertEquals(testMap.size(), 2);
	        String value1 = testMap.get(5);
	        String value2 = testMap.get(6);
	        assertEquals("cinco", value1);
	        assertEquals("seis", value2);
	        assertEquals(null, testMap.get(20));
	        
	        

	    }

	    @Test
	    public void testRemove() throws Exception {
	        Map<Integer, String> testMap = new HashTableMapSC<Integer, String>();
	        setUp(testMap);
	        assertEquals("doce", testMap.get(12));
	        assertEquals("uno", testMap.get(1));
	        
	        assertEquals("seis", testMap.remove(6));
	        assertEquals(null, testMap.get(6));
	    }

	    @Test
	    public void testEntrySet() throws Exception {
	        Map<Integer, String> testMap = new HashTableMapSC<Integer, String>(200);
	        setUp(testMap);
	        Iterable<Entry<Integer, String>> entrySet = testMap.entries();
	        Iterator<Entry<Integer, String>> iterator = entrySet.iterator();
	        List<Entry<Integer, String>> entryList = new ArrayList<Entry<Integer, String>>();
	        while (iterator.hasNext()) {
	            entryList.add(iterator.next());
	        }
	        assertEquals(entryList.size(), 15);
	    }

	    @Test
	    public void testValues() throws Exception {
	        Map<Integer, String> testMap = new HashTableMapSC<Integer, String>();
	        setUp(testMap);
	        Iterable<String> values = testMap.values();
	        Iterator<String> iterator = values.iterator();
	        List<String> valuesList = new ArrayList<String>();
	        while (iterator.hasNext()) {
	            valuesList.add(iterator.next());
	        }
	        assertEquals(valuesList.size(), 15);
	    }

	    @Test
	    public void insertMany() {
	        Map<Integer, String> testMap = new HashTableMapSC<Integer, String>(200);
	        int ammountOfEntriesInserted = 100;
	        for (int i = 0; i < ammountOfEntriesInserted; i++) {
	            testMap.put(i, "valor " + i);
	        }
	        assertEquals(testMap.size(), ammountOfEntriesInserted);
	    }
}