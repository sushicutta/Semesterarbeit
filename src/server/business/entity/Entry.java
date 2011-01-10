package server.business.entity;

public class Entry<K, V> {

	private final K key;
	private final V value;

	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public String toString() {
		return "(" + key + ", " + value + ")";
	}
	
}