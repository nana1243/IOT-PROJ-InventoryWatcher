package top.frame;

public interface Dao<K, V> {
	
	
	default public V select(K k) throws Exception {
		return null;
	}
	
	default public void insert(V v) throws Exception {
		
	}

	

}
