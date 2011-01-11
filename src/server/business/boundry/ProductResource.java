//package server.business.boundry;
//
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import server.business.entity.Product;
//
//@Path("products/{primaryKey}")
//@Stateless
//public class ProductResource {
//
//    @PersistenceContext
//    EntityManager em;
//	
//	@GET
//	@Path("{primaryKey}")
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Product get(@PathParam("primaryKey") String primaryKeyAsString) {
//		Product product = em.find(Product.class, Long.valueOf(primaryKeyAsString));
//		System.out.println("get Product: " + product + " with key " + product.getId());
//		return product;
//	}
//
//	@DELETE
//	@Path("{primaryKey}")
//	public Response delete(@PathParam("primaryKey") String primaryKeyAsString) {
//		Product product = em.find(Product.class, Long.valueOf(primaryKeyAsString));
//		em.remove(product);
//		System.out.println("delete Product " + product + " with key " + product.getId());
//		return Response.ok().build();
//	}
//
//	@PUT
//	@Path("{primaryKey}")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response update(@PathParam("primaryKey") String primaryKeyAsString, Product product) {
//		
//        Product productDbo = em.find(Product.class, product.getId());
//        productDbo.setName(product.getName());
//        productDbo.setNumberOfUnits(product.getNumberOfUnits());
////        em.merge(productDbo);
//		System.out.println("update Product " + product + " with key " + product.getId());
//		return Response.ok().build();
//	}
//
//}
