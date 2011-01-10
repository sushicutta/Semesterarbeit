package server.presentation.registration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import server.business.boundry.ProductRegistrationService;
import server.business.entity.Entry;
import server.business.entity.Product;

@Presenter
public class Index {

	@EJB
    ProductRegistrationService productRegistrationService;

	@Context
	HttpServletRequest httpServletRequest;
    
    Product product;
    
    List<Entry<String, Product>> sortedProductDirectory;
    
    public Index () {
    	
    	if (httpServletRequest != null) {
    		String acceptHeaderValue = httpServletRequest.getHeader("Accept");
    		System.out.println("    accept header: " + acceptHeaderValue);
    	}
    	
    }
    
    @PostConstruct
    public void init(){
    	product = new Product();
    	sortedProductDirectory = produceSortedProductDirectory();
    }
    
    public String register(){
        System.out.println("From CDI");
        productRegistrationService.register(product);
        return "confirm";
    }

    public Product getProduct() {
        return product;
    }
    
    public List<Entry<String, Product>> getSortedProductDirectory() {
    	return sortedProductDirectory;
    }
    
    
    public List<Entry<String, Product>> produceSortedProductDirectory() {
    	
    	List<Entry<String, Product>> sortedProductDirectory = new ArrayList<Entry<String, Product>>();
    	
    	// Für jeden Eintrag der Map mit einem Key (Integer) -> Value (Product)
    	// Mach einen Eintrag in der Liste mit Key (String) -> Value (Product)
    	for(Map.Entry<Integer, Product> entry : productRegistrationService.getProductDirectory().entrySet()) {
    		sortedProductDirectory.add(new Entry<String, Product>(String.valueOf(entry.getKey()), entry.getValue()));
    	}
    	
    	// Sortiere die Liste nach Key (String) als Integer
    	Collections.sort(sortedProductDirectory, new Comparator<Entry<String, Product>>() {

			@Override
			public int compare(Entry<String, Product> o1, Entry<String, Product> o2) {
				return Integer.valueOf(o1.getKey()).compareTo(Integer.valueOf(o2.getKey()));
			}
    		
    	});
    	
    	return sortedProductDirectory;
    	
    }

}
