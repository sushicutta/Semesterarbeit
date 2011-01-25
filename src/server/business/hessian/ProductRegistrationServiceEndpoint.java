package server.business.hessian;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import server.business.boundry.ProductRegistration;
import server.business.boundry.ProductRegistrationService;
import server.business.entity.Product;

import com.caucho.hessian.server.HessianServlet;

public class ProductRegistrationServiceEndpoint extends HessianServlet implements ProductRegistration {
	
	private static final long serialVersionUID = 5287984309422891202L;
	
	private static final Logger logger = Logger.getLogger("server.business.hessian.ProductRegistrationServiceEndpoint");
	
	private static final String endpointDescription = ">>> From Hessian";
	
	@EJB ProductRegistrationService productRegistrationService;

	@Override
	public List<Product> getAllProducts() {
		if (logger.isLoggable(Level.INFO)) {
			logger.info(endpointDescription + " :: getAllProducts()");
		}
		return productRegistrationService.getAllProducts();
	}

	@Override
	public Response register(Product product) {
		if (logger.isLoggable(Level.INFO)) {
			logger.info(endpointDescription + " :: getAllProducts()");
		}
		return productRegistrationService.register(product);
	}

	@Override
	public Product get(String primaryKeyAsString) {
		if (logger.isLoggable(Level.INFO)) {
			logger.info(endpointDescription + " :: getAllProducts()");
		}
		return productRegistrationService.get(primaryKeyAsString);
	}

	@Override
	public Response delete(String primaryKeyAsString) {
		if (logger.isLoggable(Level.INFO)) {
			logger.info(endpointDescription + " :: getAllProducts()");
		}
		return productRegistrationService.delete(primaryKeyAsString);
	}

	@Override
	public Response update(String primaryKeyAsString, Product product) {
		if (logger.isLoggable(Level.INFO)) {
			logger.info(endpointDescription + " :: getAllProducts()");
		}
		return productRegistrationService.update(primaryKeyAsString, product);
	}

}
