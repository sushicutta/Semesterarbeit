package server.presentation.registration;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import server.business.boundry.ProductRegistrationService;
import server.business.entity.Product;

@Presenter
public class Index {
	
	private static final Logger logger = Logger.getLogger("server.presentation.registration.Index");
	
	private static final String endpointDescription = ">>> From Faces 2.0";
	
	@EJB
    ProductRegistrationService productRegistrationService;

	@Context
	HttpServletRequest httpServletRequest;
    
    Product product;
    
    List<Product> sortedProducts;
    
    public Index () {
    	
    	if (httpServletRequest != null) {
    		String acceptHeaderValue = httpServletRequest.getHeader("Accept");
    		System.out.println("    accept header: " + acceptHeaderValue);
    	}
    	
    }
    
    @PostConstruct
    public void init(){
    	product = new Product();
    	sortedProducts = getAllProducts();
    }
    
    public String register(){
		if (logger.isLoggable(Level.INFO)) {
			logger.info(endpointDescription + " :: register()");
		}
        productRegistrationService.register(product);
        return "confirm";
    }

    public Product getProduct() {
        return product;
    }
    
    public List<Product> getAllProducts() {
		if (logger.isLoggable(Level.INFO)) {
			logger.info(endpointDescription + " :: getAllProducts()");
		}
    	return productRegistrationService.getAllProducts();
    }
    
    public List<Product> getProducts() {
    	return sortedProducts;
    }

}
