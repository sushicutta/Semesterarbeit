package server.business.persistence;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;

import server.business.entity.Product;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class RegistrationBean {

	private AtomicInteger primaryKeyPool = new AtomicInteger(1);
	
	private ConcurrentHashMap<Integer, Product> productStore = new ConcurrentHashMap<Integer, Product>();

	public RegistrationBean() {

	}

	@PostConstruct
	@SuppressWarnings("unused")
	private void init() {
		final Product android = new Product("android", 666);
		final Product iPhone = new Product("iPhone", 10);
		final Product winMobile = new Product("winMobile", 1);
		final Product blackBarry = new Product("blackBarry", 3);
		register(android);
		register(iPhone);
		register(winMobile);
		register(blackBarry);
	}

	// create
	public Integer register(Product product) {
		Integer primaryKey = primaryKeyPool.getAndIncrement();
		productStore.put(primaryKey, product);
		return primaryKey; 
	}

	// read
	public Product get(Integer primaryKey) {
		return productStore.get(primaryKey);
	}

	// read All
	public Map<Integer, Product> getAll() {
		return new TreeMap<Integer, Product>(productStore);
	}

	// update
	public Product update(Integer primaryKey, Product product) {
		if (productStore.containsKey(primaryKey)) {
			return productStore.put(primaryKey, product);
		}
		return null;
	}

	// delete
	public Product delete(Integer key) {
		return productStore.remove(key);
	}
}
