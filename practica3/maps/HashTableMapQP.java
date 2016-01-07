package practica3.maps;

public class HashTableMapQP<K, V> extends AbstractHashTableMap<K, V> {

    public HashTableMapQP(int size) {
        super(size);
    }

    public HashTableMapQP() {
        super();
    }

    public HashTableMapQP(int p, int cap) {
        super(p, cap);
    }

    @Override
    protected int offset(K key, int i) {
        int c1 = 0;
        int c2 = 1;
        return (c1*i) + c2*(i^2); 
        
    }

}
