package server.business.boundry;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import server.business.entity.Product;

import com.sun.jersey.api.NotFoundException;

@Path("products")
@Stateless
public class ProductRegistrationService {

    @PersistenceContext
    EntityManager em;
    
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Product> getAllProducts() {
		
		System.out.println(">>> get all Products");
		
		@SuppressWarnings("unchecked")
		List<Product> sortedProductList = em.createNamedQuery(Product.ALL).getResultList();
		
		return sortedProductList;
		
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response register(Product product) {
		
		try {
			
			em.persist(product);
			System.out.println(">>> Registered " + product + " with key " + product.getId());

			return Response.created(URI.create("/" + product.getId())).build();

		} catch (Exception e) {
			throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
		}

	}
	
	@Path("{primaryKey}")
	public ProductResource findById(@PathParam("primaryKey") String primaryKeyAsString) {
		
		Long primaryKey;
		try {
			primaryKey = Long.valueOf(primaryKeyAsString);
		} catch (NumberFormatException nfe) {
			throw new NotFoundException();
		}
		
		Product product = em.find(Product.class, primaryKey);
		if (product == null) {
			throw new NotFoundException();
		}
		
		return new ProductResource(em.find(Product.class, primaryKey));

	}
}
