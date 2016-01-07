package practica3.maps;

public class HashTableMapDH<K, V> extends AbstractHashTableMap<K, V> {

    public HashTableMapDH(int size) {
        super(size);
    }

    public HashTableMapDH() {
        super();
    }

    public HashTableMapDH(int p, int cap) {
        super(p, cap);
    }

    @Override
    protected int offset(K key, int i) {
        return this.dHash(this.capacity, key)*i;
    }
    
    //Módulos auxiliares
    private int dHash(int n, K key) throws IllegalStateException{
    	int q = this.ultimoPrimo(n);
    	int k;
    	k = (Integer) key;

    	return q - (k % q);
    }
    
    
    private int ultimoPrimo(int numero){
    	int numPrimo = -1;
    	for(int i = numero-1; i > 0; i--){
    		if (this.esPrimo(i)){
    			numPrimo = i;
    			break;
    		}
    	}
    	return numPrimo;
    }
    
    //Comprueba si es divisible por algun numero
    //entre el rango [2- n) siendo n dicho numero
    //si lo encuentra no es primo
    private boolean esPrimo(int numero){
	  int contador = 2;
	  boolean primo=true;
	  while ((primo) && (contador!=numero)){
	    if (numero % contador == 0)
	      primo = false;
	    contador++;
	  }
	  return primo;
	}

}
