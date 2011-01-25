package server.business.hessian;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;

import server.business.boundry.ProductRegistration;
import server.business.boundry.eao.EntityNotFoundException;
import server.business.boundry.eao.ProductEao;
import server.business.entity.Product;

import com.caucho.hessian.server.HessianServlet;

public class ProductRegistrationServiceEndpoint extends HessianServlet implements ProductRegistration {
	
	private static final long serialVersionUID = 5287984309422891202L;
	
	private static final Logger logger = Logger.getLogger("server.business.hessian.ProductRegistrationServiceEndpoint");
	
	private static final String endpointDescription = ">>> From Hessian";
	
	@EJB
	ProductEao eao;

	@Override
	public List<Product> allProducts() {
		if (logger.isLoggable(Level.INFO)) {
			logger.info(endpointDescription + " :: allProducts()");
		}
		return eao.allProducts();
	}

	@Override
	public Product register(Product product) {
		
		if (logger.isLoggable(Level.INFO)) {
			logger.info(endpointDescription + " :: register()");
		}
		
		try {
			return eao.persist(product);
		} catch (EntityNotFoundException e) {
			// Do nothing
		}
		return null;
	}

	@Override
	public Product get(Long id) throws EntityNotFoundException {
		if (logger.isLoggable(Level.INFO)) {
			logger.info(endpointDescription + " :: get(" + id + ")");
		}
		return eao.find(id);
	}

	@Override
	public Product delete(Long id) throws EntityNotFoundException {
		if (logger.isLoggable(Level.INFO)) {
			logger.info(endpointDescription + " :: delete( " + id + ")");
		}
		return eao.remove(id);
	}

	@Override
	public Product update(Long id, Product product) throws EntityNotFoundException {
		if (logger.isLoggable(Level.INFO)) {
			logger.info(endpointDescription + " :: update()");
		}
		return eao.merge(id, product);
	}

}
