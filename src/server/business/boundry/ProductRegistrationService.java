package server.business.boundry;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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
import server.business.persistence.RegistrationBean;

import com.sun.jersey.api.NotFoundException;

@Stateless
@Path("products")
public class ProductRegistrationService {

	@EJB
	RegistrationBean registrationBean;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Product> getAllProducts() {
		
		System.out.println(">>> get all Products");

		Map<Integer, Product> sortedProductDirectory = registrationBean.getAll();
		
		List<Product> sortedProductList = new ArrayList<Product>(sortedProductDirectory.values());
		Collections.sort(sortedProductList);
		
		return sortedProductList;
		
	}
	
	public Map<Integer, Product> getProductDirectory() {
		return registrationBean.getAll();
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response register(Product product) {
		
		try {
			
			Integer primaryKey = registrationBean.register(product);
			System.out.println(">>> Registered " + product + " with key " + primaryKey);

			return Response.created(URI.create("/" + primaryKey)).build();

		} catch (Exception e) {
			throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
		}

	}
	
	@Path("{primaryKey}")
	public ProductResource findById(@PathParam("primaryKey") String primaryKeyAsString) {
		
		Integer primaryKey;
		try {
			primaryKey = Integer.valueOf(primaryKeyAsString);
		} catch (NumberFormatException nfe) {
			throw new NotFoundException();
		}
		
		Product product = registrationBean.get(primaryKey);
		if (product == null) {
			throw new NotFoundException();
		}
		return new ProductResource(primaryKey, registrationBean.get(primaryKey), registrationBean);
	}
}
