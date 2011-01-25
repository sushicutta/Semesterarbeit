package server.business.boundry;

import java.util.List;

import javax.ws.rs.core.Response;

import server.business.entity.Product;

public interface ProductRegistration {

	public abstract List<Product> getAllProducts();

	public abstract Response register(Product product);

	public abstract Product get(String primaryKeyAsString);

	public abstract Response delete(String primaryKeyAsString);

	public abstract Response update(String primaryKeyAsString, Product product);

}