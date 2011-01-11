package server.presentation.registration;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import server.business.boundry.ProductRegistrationService;
import server.business.entity.Product;

@Presenter
public class Index {

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
        System.out.println("From CDI");
        productRegistrationService.register(product);
        return "confirm";
    }

    public Product getProduct() {
        return product;
    }
    
    public List<Product> getAllProducts() {
    	return productRegistrationService.getAllProducts();
    }
    
    public List<Product> getProducts() {
    	return sortedProducts;
    }

}
