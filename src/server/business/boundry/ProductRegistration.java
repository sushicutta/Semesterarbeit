package server.business.boundry;

import server.business.boundry.eao.EntityNotFoundException;
import server.business.entity.Product;

public interface ProductRegistration {
	
	public int add(int a, int b);

	public Product [] allProducts();

	public Product register(ch.hszt.semesterarbeit.Product product);

	public Product get(Long id) throws EntityNotFoundException;

	public Product delete(Long id) throws EntityNotFoundException;

	public Product update(Long id, ch.hszt.semesterarbeit.Product product) throws EntityNotFoundException;

}