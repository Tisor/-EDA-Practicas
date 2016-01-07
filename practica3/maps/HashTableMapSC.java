package practica3.maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
 * Separate chaining table implementation of hash tables. Note that all
 * "matching" is based on the equals method.
 *
 * @author A. Duarte, J. VÃ©lez, J. SÃ¡nchez-Oro, Carlos Ruiz
 */
public class HashTableMapSC<K, V> implements Map<K, V> {

    public class HashEntry<T, U> implements Entry<T, U> {

        private T key;
        private U value;
    	
    	public HashEntry(T k, U v) {
            this.key = k;
            this.value = v;
        }

        @Override
        public U getValue() {
            return value;
        }

        @Override
        public T getKey() {
            return key;
        }

        @Override
        public boolean equals(Object o) {
            if(o.getClass() != this.getClass()){
            	return false;
            }
            HashEntry<T, U> ent;
            try{
            	ent = (HashEntry<T, U>) o;
            }
            catch(ClassCastException ex){
            	return false;
            }
            return (this.key.equals(ent.key) && this.value.equals(ent.value));
            
        }

        /**
         * Entry visualization.
         */
        @Override
        public String toString() {
        	return "(" + key + "," + value + ")";
        }
    }

    private class HashTableMapIterator<T, U> implements Iterator<Entry<T, U>> {
    	//Como cada entrada puede tener varios datos utilizaremos un array
    	//que contenga arrayList
    	
    	private int pos;
    	private int posArray;
    	private int elemsArray;
    	private int showed;
    	private ArrayList<HashEntry<T, U>>[] bucket;
    	
    	
        //Ejercicio 2.2
        public HashTableMapIterator(ArrayList<HashEntry<T, U>>[] map, int numElems) {
            this.bucket = map;
            if(numElems == 0){
            	this.pos = bucket.length;
            }
            else{
            	this.pos = 0;
            	this.posArray = 0;
            	this.elemsArray = numElems;
            	this.showed = 0;
            	
            }
        }

        private void goToNextElement() {
            //Si estamos dentro de un array, comprobamos primero si hay más elementos
        	// si no ponemos posArray a 0 y aumentamos pos una posicion
        	// y buscamos hasta que encuentre uno distinto de null
        	if(bucket[pos] != null){
            	if(posArray < bucket[pos].size()){
            		posArray++;
            	}
            	else{
            		posArray = 0;
            		pos++;
            		while(bucket[pos] == null && pos < bucket.length){
            			pos++;
            		}
            	}
            }
            //Si resulta que no estamos en un array, es porque ya hemos terminado de recorrer este
        	//por lo que vamos al siguiente elemento
        	else{
        		while(pos < bucket.length){
        			if(bucket[pos] != null){
        				posArray = 0;
        				break;
        			}
        			pos++;
        		}
            }
        }

        @Override
        public boolean hasNext() {
            //Si al llamar al método goToNextElement, resulta que ya no hay más elementos,
        	// pos sera igual a bucket.lenght, por lo que para saber si hay más elementos
        	// pos < bucket.length
        	return showed < elemsArray;
        }

        @Override
        public Entry<T, U> next() {
            Entry<T, U> elem = null;
        	// Como comenzamos en una posición 0, pero comprobamos la posición actual
        	if(bucket[pos] == null){
        		 goToNextElement();
        		 if(pos < bucket.length){
        			 elem = bucket[pos].get(posArray);
        		 }	 
        	}
        	else{
        		elem = bucket[pos].get(posArray);
        		goToNextElement();
        	}
        	if (elem == null){
            	throw new IllegalStateException("The map has not more elements");
        	}
        	pos++;
        	showed++;
        	return elem;
        }

        @Override
        public void remove() {
            // NO HAY QUE IMPLEMENTARLO
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    private class HashTableMapKeyIterator<T, U> implements Iterator<T> {
    	
    	private HashTableMapIterator<T, U> it;
    	
        public HashTableMapKeyIterator(HashTableMapIterator<T, U> it) {
            this.it = it;
        }

        @Override
        public T next() {
            return it.next().getKey();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public void remove() {
            // NO HAY QUE IMPLEMENTARLO
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    private class HashTableMapValueIterator<T, U> implements Iterator<U> {
    	
    	private HashTableMapIterator<T, U> it;
    	
        public HashTableMapValueIterator(HashTableMapIterator<T, U> it) {
            this.it = it;
        }

        @Override
        public U next() {
            return it.next().getValue();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }
    
    protected int num = 0; // number of entries in the dictionary
    protected int prime, capacity; // prime factor and capacity of bucket array
    protected long scale, shift; // the shift and scaling factors
    protected ArrayList<HashEntry<K, V>>[] bucket;// bucket array
    
    
    /**
     * Creates a hash table
     */
    public HashTableMapSC() {
    	this(109345121, 1000); // reusing the constructor HashTableMap(int p, int cap)
    }

    /**
     * Creates a hash table.
     *
     * @param cap initial capacity
     */
    public HashTableMapSC(int cap) {
    	this(109345121, cap); // reusing the constructor HashTableMap(int p, int cap)
    }

    /**
     * Creates a hash table with the given prime factor and capacity.
     *
     * @param p prime number
     * @param cap initial capacity
     */
    public HashTableMapSC(int p, int cap) {
        this.num = 0;
        this.prime = p;
        this.capacity = cap;
        this.bucket = new ArrayList[capacity];
        for(int i = 0; i<capacity; i++){
            bucket[i] = null;
        }
        Random rand = new Random();
        this.scale = rand.nextInt(prime - 1) + 1;
        this.shift = rand.nextInt(prime);
    }

    /**
     * Hash function applying MAD method to default hash code.
     *
     * @param key Key
     * @return
     */
    protected int hashValue(K key) {
    	return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
    }

    /**
     * Returns the number of entries in the hash table.
     *
     * @return the size
     */
    @Override
    public int size() {
        return num;
    }

    /**
     * Returns whether or not the table is empty.
     *
     * @return true if the size is 0
     */
    @Override
    public boolean isEmpty() {
        return (num == 0);
    }

    /**
     * Returns the value associated with a key.
     *
     * @param key
     * @return value
     */
    @Override
    public V get(K key) throws IllegalStateException {
        //Comprobamos la clave
    	checkKey(key);
    	int k = hashValue(key);
    	if(bucket[k] != null){
    		for(HashEntry<K, V> e: bucket[k]){
    			if(e.getKey().equals(key)){
    				return e.getValue();
    			}
    		}
    	}
    	
        return null;
    }

    
    /**
     * Put a key-value pair in the map, replacing previous one if it exists.
     *
     * @param key
     * @param value
     * @return value
     */
    @Override
    public V put(K key, V value) throws IllegalStateException {
        checkKey(key); 
        //Sacamos el indice en el se tiene que añadir
        int k = hashValue(key);
        V oldVal = null;
        
        //Inicializamos el objeto entry con la informacion.
        HashEntry<K, V> entry = new HashEntry<K, V>(key, value);
        
        if(bucket[k] == null){
        	//Primero comprobamos que no sobrepasemos n/N > 0.75
        	if(num/capacity > 0.75){
        		rehash(capacity*2);
        	}
        	//Creamos un arrayList, ya que k está a null
        	ArrayList<HashEntry<K, V>> arr = new ArrayList<>();
        	bucket[k] = arr;
        	bucket[k].add(entry);
        }
        else{
        	
        	//Buscamos el elemento dentro del array
        	int posArray = 0;
        	boolean found = false;
        	for(HashEntry<K, V> e: bucket[k]){
        		if(e.getKey().equals(key)){
        			found = true;
        			break;
        		}
        		else{
        			posArray++;
        		}
        	}
        	//si está, lo borramos y sustituimos por el nuevo
        	if(found){
        		oldVal = bucket[k].get(posArray).getValue();
        		bucket[k].remove(posArray);
        		bucket[k].add(entry);
        	}
        	//Si no simplemente lo añadimos
        	else{
            	if(num/capacity > 0.75){
            		rehash(capacity*2);
            	}
        		bucket[k].add(entry);
        	}
        }
        num++;
        return oldVal;
    }

    /**
     * Removes the key-value pair with a specified key.
     *
     * @param key
     * @return
     */
    @Override
    public V remove(K key) throws IllegalStateException {
        checkKey(key);
        int k = hashValue(key);
        V value = null;
        //Si no está vacio, es posible que esté dentro del array
        if(bucket[k] != null){
        	int posArray = 0;
        	boolean found = false;
        	for(HashEntry<K, V> e: bucket[k]){
        		if(e.getKey().equals(key)){
        			found = true;
        			break;
        		}
        		else{
        			posArray++;
        		}
        	}
        	// Si lo encontramos, value toma el valor y lo borramos del arrayList
        	if(found){
        		value = bucket[k].get(posArray).getValue();
        		bucket[k].remove(posArray);
        		num--;
        	}
        }
        return value;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashTableMapIterator<K, V>(this.bucket, this.num);
    }

    /**
     * Returns an iterable object containing all of the keys.
     *
     * @return
     */
    @Override
    public Iterable<K> keys() {
        return new Iterable<K>() {
            public Iterator<K> iterator() {
                return new HashTableMapKeyIterator<K, V>(new HashTableMapIterator<K, V>(bucket, num));
            }
        };
    }

    /**
     * Returns an iterable object containing all of the values.
     *
     * @return
     */
    @Override
    public Iterable<V> values() {
        return new Iterable<V>() {
            public Iterator<V> iterator() {
                return new HashTableMapValueIterator<K, V>(new HashTableMapIterator<K, V>(bucket, num));
            }
        };
    }

    /**
     * Returns an iterable object containing all of the entries.
     *
     * @return
     */
    @Override
    public Iterable<Entry<K, V>> entries() {
        return new Iterable<Entry<K, V>>() {
            public Iterator<Entry<K, V>> iterator() {
                return new HashTableMapIterator<K, V>(bucket, num);
            }
        };
    }

    /**
     * Determines whether a key is valid.
     *
     * @param k Key
     */
    protected void checkKey(K k) {
        if(k == null){
        	throw new IllegalStateException("Invalid key: null.");
        }
    }

    /**
     * Increase/reduce the size of the hash table and rehashes all the entries.
     */
    protected void rehash(int newCap) {
    	capacity = newCap;
    	ArrayList<HashEntry<K, V>>[] old = bucket;
    	bucket = new ArrayList[capacity];
    	java.util.Random rand = new java.util.Random();
    	scale = rand.nextInt(prime - 1) + 1;
    	shift = rand.nextInt(prime);
    	num = 0;
    	for(ArrayList<HashEntry<K,V>> e: old){
    		if(e != null){
        		for(HashEntry<K, V> f: e){
        			if(f != null){
        				put(f.getKey(), f.getValue());
        			}
        		}
    		}

    	}
    }
}
