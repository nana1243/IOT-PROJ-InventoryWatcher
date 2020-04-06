package top.frame;

public interface Dao<K, V> {
	
	
	default public V select(K k) throws Exception {
		return null;
	}

	

}
