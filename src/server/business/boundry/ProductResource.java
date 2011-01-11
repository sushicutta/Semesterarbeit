package server.business.boundry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import server.business.entity.Product;

public class ProductResource {

    @PersistenceContext
    EntityManager em;
	
	private Product product;

	public ProductResource(Product product) {
		this.product = product;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Product get() {
		System.out.println("get Product: " + product + " with key " + product.getId());
		return product;
	}

	@DELETE
	public Response delete() {
		System.out.println("delete Product " + product + " with key " + product.getId());
		
		em.remove(product);
		return Response.ok().build();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(Product product) {
		
        Product productDbo = em.find(Product.class, product.getId());
        productDbo.setName(product.getName());
        productDbo.setNumberOfUnits(product.getNumberOfUnits());
        
		System.out.println("update Product " + product + " with key " + product.getId());
		return Response.ok().build();
	}

}
