package server.business.boundry;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import server.business.entity.Product;
import server.business.persistence.RegistrationBean;

public class ProductResource {

	private Integer primaryKey;
	private Product product;
	private RegistrationBean registrationBean;

	public ProductResource(Integer primaryKey, Product product, RegistrationBean registrationBean) {
		this.primaryKey = primaryKey;
		this.product = product;
		this.registrationBean = registrationBean;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Product get() {
		System.out.println("get Product: " + product + " with key " + primaryKey);
		return product;
	}

	@DELETE
	public Response delete() {
		System.out.println("delete Product " + product + " with key " + primaryKey);
		registrationBean.delete(primaryKey);
		return Response.ok().build();
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(Product product) {
		Product updatedProduct = registrationBean.update(primaryKey, product);
		if (updatedProduct != null) {
			System.out.println("update Product " + product + " with key " + primaryKey);
			return Response.ok().build();
		} else {
			return Response.serverError().build();
		}
	}

}
