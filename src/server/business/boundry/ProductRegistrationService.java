package server.business.boundry;

import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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

import server.business.boundry.eao.EntityNotFoundException;
import server.business.boundry.eao.ProductEao;
import server.business.entity.Product;

import com.sun.jersey.api.NotFoundException;

@Path("products")
@Stateless
public class ProductRegistrationService {
	
	private static final Logger logger = Logger.getLogger("server.business.boundry.ProductRegistrationService");
	
	private static final String serviceDescription = ">>> From REST";

	@EJB
	ProductEao eao;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Product> allProducts() {
		
		if (logger.isLoggable(Level.INFO)) {
			logger.info(serviceDescription + " :: allProducts()");			
		}
		
		List<Product> sortedProductList = eao.allProducts();
		
		return sortedProductList;
		
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response register(Product product) {
		
		if (product.hasId()) {
			Product productDbo = null;
			try {
				productDbo = eao.find(product.getId());
			} catch (EntityNotFoundException e) {
				// can happen
			}
			if (productDbo != null) {
				throw new WebApplicationException(Response.Status.CONFLICT);
			}
		}
		
		product.setId(null);
		
		try {
			
			eao.persist(product);
			
			if (logger.isLoggable(Level.INFO)) {
				logger.info(serviceDescription + " :: register()");			
			}
			
			return Response.created(URI.create("/" + product.getId())).build();

		} catch (Exception e) {
//			throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}

	}
	
	// Scheint ein Bug zu sein, dass es nicht richtig mit der ProductResource funktioniert.
	// Desshalb ist diese Methode auskommentiert.
	
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
		
		Product product;
		try {
			product = eao.find(primaryKey);
		} catch (EntityNotFoundException e) {
			throw new NotFoundException();
		}

		if (logger.isLoggable(Level.INFO)) {
			logger.info(serviceDescription + " :: get(" + primaryKeyAsString + ")");			
		}

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
		
		Product product = null;
		try {
			product = eao.find(primaryKey);
		} catch (EntityNotFoundException e) {
			throw new NotFoundException();
		}
		
		try {
			eao.remove(product.getId());
		} catch (EntityNotFoundException e) {
			throw new NotFoundException();
		}

		if (logger.isLoggable(Level.INFO)) {
			logger.info(serviceDescription + " :: delete(" + primaryKeyAsString + ")");			
		}
		
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

		try {
			eao.merge(primaryKey, product);
		} catch (EntityNotFoundException e) {
			throw new NotFoundException();
		}

		if (logger.isLoggable(Level.INFO)) {
			logger.info(serviceDescription + " :: update(" + primaryKeyAsString + ", " + product + ")");			
		}

		return Response.ok().build();
	}

	
}
