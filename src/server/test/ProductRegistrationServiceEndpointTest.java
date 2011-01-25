package server.test;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.business.boundry.ProductRegistration;
import server.business.boundry.eao.EntityNotFoundException;
import server.business.entity.Product;

import com.caucho.hessian.client.HessianProxyFactory;

public class ProductRegistrationServiceEndpointTest {
	
	private ProductRegistration productRegistration;

	@Before
	public void initProxy() throws MalformedURLException {
		
		String url = "http://localhost:8080/Semesterarbeit/ProductRegistrationService";
		HessianProxyFactory factory = new HessianProxyFactory();
		this.productRegistration = (ProductRegistration) factory.create(ProductRegistration.class, url);
		assertNotNull(productRegistration);
		
	}
	
	@Test
	public void getAllProducts() {
		
		List<Product> products  = this.productRegistration.allProducts();
		assertTrue(products.size() > 0);
		
	}
	
	@Test
	public void getProduct() throws EntityNotFoundException {
		
		Product product = this.productRegistration.get(new Long(1));
		assertEquals(1, product.getId().longValue());
		assertEquals("android", product.getName());
		assertEquals(666, product.getNumberOfUnits());

	}
	
	@Test
	public void updateProduct() throws EntityNotFoundException {
	
		Product product = this.productRegistration.get(new Long(2));
		assertEquals(2, product.getId().longValue());
		assertEquals("iPhone", product.getName());
		assertEquals(10, product.getNumberOfUnits());

		product.setName("ZweiPhone");
		product.setNumberOfUnits(11);

		this.productRegistration.update(new Long(2), product);
		
		Product updatedProduct = this.productRegistration.get(new Long(2));
		assertEquals(2, updatedProduct.getId().longValue());
		assertEquals("ZweiPhone", updatedProduct.getName());
		assertEquals(11, updatedProduct.getNumberOfUnits());

	}
	
	@Test
	public void registerProduct() throws EntityNotFoundException {
	
		Product product = new Product("Nokia", 321);

		this.productRegistration.register(product);
		
		Product registeredProduct = this.productRegistration.get(new Long(5));
		
		assertEquals(5, registeredProduct.getId().longValue());
		assertEquals("Nokia", registeredProduct.getName());
		assertEquals(321, registeredProduct.getNumberOfUnits());

	}
	
	@Test
	public void deleteProduct() throws EntityNotFoundException {
		
		Product product = this.productRegistration.get(new Long(5));
		
		assertEquals(5, product.getId().longValue());
		assertEquals("Nokia", product.getName());
		assertEquals(321, product.getNumberOfUnits());
		
		this.productRegistration.delete(new Long(5));
		
		try {
			this.productRegistration.get(new Long(5));
		} catch (EntityNotFoundException e) {
			assertNotNull(e);
		}
		
	}
}