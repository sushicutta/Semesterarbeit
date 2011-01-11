package server.business.controll;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import server.business.boundry.ProductRegistrationService;
import server.business.entity.Product;

@Startup
@Singleton
public class RegistrationBean {

	@EJB
    ProductRegistrationService productRegistrationService;
	
	public RegistrationBean() {

	}

	@PostConstruct
	@SuppressWarnings("unused")
	private void init() {
		final Product android = new Product("android", 666);
		final Product iPhone = new Product("iPhone", 10);
		final Product winMobile = new Product("winMobile", 1);
		final Product blackBarry = new Product("blackBarry", 3);
		register(android);
		register(iPhone);
		register(winMobile);
		register(blackBarry);
	}

	// create
	public void register(Product product) {
		productRegistrationService.register(product);
	}

}
