package top.frame;

public interface Biz<K, V> {

	
	default public V get(K k) throws Exception {
		return null;
	};

	
}
