package practica3.maps;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;




public class Dictionary<K, V> extends HashTableMapSC<K, V>{
	
	
	public Dictionary() {
		super();
	}
	
	public Dictionary(int cap){
		super(cap);
	}
	
	public Dictionary(int p, int cap){
		super(p, cap);
	}
	

	
	@Override
	public V put(K key, V value) throws IllegalStateException{
		checkKey(key); 
        //Sacamos el indice en el se tiene que añadir
        int k = hashValue(key);
        
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
        	if(num/capacity > 0.75){
        		rehash(capacity*2);
        	}
    		bucket[k].add(entry);
        }
        num++;
        return entry.getValue();
	}
	
	
	
	public V find(K key){
		return get(key);
	}
	
	public Set<V> findAll(K key){
		checkKey(key);
		int k = hashValue(key);
		Set<V> values = new HashSet<>();
		if(bucket[k] != null){
			for(HashEntry<K, V> e: bucket[k]){
				if (e.getKey() == key){
					values.add(e.getValue());
				}
			}
			if (values.size() == 0){
				return null;
			}
			else{
				return values;
			}
		}
		else{
			return null;
		}
	}
	

}
