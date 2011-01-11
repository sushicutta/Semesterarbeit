package server.business.boundry;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
		
		if (product.getId() != null) {
			Product productDbo = em.find(Product.class, product.getId());
			if (productDbo != null) {
				throw new WebApplicationException(Response.Status.CONFLICT);
			}
		}
		
		product.setId(null);
		
		try {
			em.persist(product);
			System.out.println(">>> Registered " + product + " with key " + product.getId());

			return Response.created(URI.create("/" + product.getId())).build();

		} catch (Exception e) {
//			throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}

	}
//	
//	@Path("{primaryKey}")
//	public ProductResource findById(@PathParam("primaryKey") String primaryKeyAsString) {
//		
//		Long primaryKey;
//		try {
//			primaryKey = Long.valueOf(primaryKeyAsString);
//		} catch (NumberFormatException nfe) {
//			throw new NotFoundException();
//		}
//		
//		Product product = em.find(Product.class, primaryKey);
//		if (product == null) {
//			throw new NotFoundException();
//		}
//		
//		return productResource;
//
//	}
//	

	@GET
	@Path("{primaryKey}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Product get(@PathParam("primaryKey") String primaryKeyAsString) {
		
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
		
		System.out.println("get Product: " + product + " with key " + product.getId());
		return product;
	}

	@DELETE
	@Path("{primaryKey}")
	public Response delete(@PathParam("primaryKey") String primaryKeyAsString) {
		
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
		
		System.out.println("delete Product " + product + " with key " + product.getId());
		em.remove(product);
		return Response.ok().build();
	}

	@PUT
	@Path("{primaryKey}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(@PathParam("primaryKey") String primaryKeyAsString, Product product) {
		
		Long primaryKey;
		try {
			primaryKey = Long.valueOf(primaryKeyAsString);
		} catch (NumberFormatException nfe) {
			throw new NotFoundException();
		}
		
		Product productDbo = em.find(Product.class, primaryKey);
		if (productDbo == null) {
			throw new NotFoundException();
		}
		
        productDbo.setName(product.getName());
        productDbo.setNumberOfUnits(product.getNumberOfUnits());
        em.merge(productDbo);
		System.out.println("update Product " + product + " with key " + product.getId());
		return Response.ok().build();
	}

	
}
