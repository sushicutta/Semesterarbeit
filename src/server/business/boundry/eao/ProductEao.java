package server.business.boundry.eao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import server.business.entity.Product;

@LocalBean
@Stateless
public class ProductEao {
	
	private static final Logger logger = Logger.getLogger("server.business.boundry.eao.ProductEao");
	
    @PersistenceContext
    EntityManager em;
    
    public ProductEao() { }
    
	public ProductEao(EntityManager em) {
		
		this.em = em;
		
	}
    
//    public void flush() {
//        em.flush();
//    }
// 
//	public <T extends Entity> T findOrFail(Class<T> clazz, Integer id)
//			throws EntityNotFoundException {
//		T e = em.find(clazz, id);
//		if (e == null) {
//			throw new EntityNotFoundException(clazz.getClass(), id);
//		}
//		return e;
//	}
// 
//    public <T extends Entity> void persist(T entity) {
//        if (entity.hasId()) {
//            em.merge(entity);
//        } else {
//            em.persist(entity);
//            if (entity.getId() == null) {
//                em.flush();
//            }
//        }
//    }
 
    
    
    public long countProducts() {
        return (Long)em.createQuery(Product.COUNT).getSingleResult();
    }

	public List<Product> allProducts() {
		@SuppressWarnings("unchecked")
		List<Product> sortedProductList = (List<Product>)em.createNamedQuery(Product.ALL).getResultList();
		return sortedProductList;
	}

	public Product find(Long id) throws EntityNotFoundException {
		
		Product product = em.find(Product.class, id);
		
		if (product == null) {
			throw new EntityNotFoundException(Product.class, id);
		}
		
		if (logger.isLoggable(Level.INFO)) {
			logger.info(">>> Find " + product + " with key " + product.getId());
		}

		return product;
	}
	
	public Product persist(Product product) throws EntityNotFoundException {
		
		if (product.hasId()) {
			find(product.getId());
		}
		
		product.setId(null);
		
		em.persist(product);
		
		if (logger.isLoggable(Level.INFO)) {
			logger.info(">>> Persist " + product + " with key " + product.getId());
		}
		
		return product;

	}
	
	public Product remove(Long id) throws EntityNotFoundException {
		
		Product product = find(id);
		
		em.remove(product);

		if (logger.isLoggable(Level.INFO)) {
			logger.info(">>> Remove " + product + " with key " + id);
		}
		
		return product;
	}
	
	public Product merge(Long id, Product product) throws EntityNotFoundException {
		
		Product productDbo = find(id);
		
        productDbo.setName(product.getName());
        productDbo.setNumberOfUnits(product.getNumberOfUnits());

        em.merge(productDbo);
        
		if (logger.isLoggable(Level.INFO)) {
			logger.info(">>> Merge " + productDbo + " with key " + productDbo.getId());
		}

		return productDbo;
	}
	
	
	
	
	
}