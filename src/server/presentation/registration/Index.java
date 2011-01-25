package server.presentation.registration;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import server.business.boundry.eao.EntityNotFoundException;
import server.business.boundry.eao.ProductEao;
import server.business.entity.Product;

@Presenter
public class Index {
	
	private static final Logger logger = Logger.getLogger("server.presentation.registration.Index");
	
	private static final String endpointDescription = ">>> From Faces 2.0";
	
	@EJB
	ProductEao eao;

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
    	sortedProducts = allProducts();
    }
    
    public String register(){
		try {
			eao.persist(product);
		} catch (EntityNotFoundException e) {
			// Do nothing
		}

		if (logger.isLoggable(Level.INFO)) {
			logger.info(endpointDescription + " :: register()");
		}

		return "confirm";
    }

    public Product getProduct() {
        return product;
    }
    
    public List<Product> allProducts() {
    	
    	List<Product> products = eao.allProducts();
    	
		if (logger.isLoggable(Level.INFO)) {
			logger.info(endpointDescription + " :: allProducts()");
		}
		
    	return products;
    }
    
    public List<Product> getProducts() {
    	return sortedProducts;
    }

}
