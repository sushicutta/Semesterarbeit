package server.business.boundry;

import java.util.List;

import server.business.boundry.eao.EntityNotFoundException;
import server.business.entity.Product;

public interface ProductRegistration {

	public abstract List<Product> allProducts();

	public abstract Product register(Product product);

	public abstract Product get(Long id) throws EntityNotFoundException;

	public abstract Product delete(Long id) throws EntityNotFoundException;

	public abstract Product update(Long id, Product product) throws EntityNotFoundException;

}