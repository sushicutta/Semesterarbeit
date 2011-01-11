package server.business.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name=Product.ALL,query="Select p from Product p order by p.id asc")
public class Product implements Comparable<Product> {
	
    public final static String ALL = "all";

    @Id
    @GeneratedValue
    private Long id;
    
//	@Size(min = 3, max = 20, message = "minimum 3, maximum 20 Zeichen für den Namen")
	private String name;

//	@Max(1000)
	private int numberOfUnits;

	public Product() {
		// Wird bei JPA gebraucht.
	}

	public Product(final String name, final int numberOfUnits) {
		this.name = name;
		this.numberOfUnits = numberOfUnits;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
    public Long getId() {
        return id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfUnits() {
		return numberOfUnits;
	}

	public void setNumberOfUnits(int numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}

	@Override
	public String toString() {
		return "Product{" + "name=" + name + " numberOfUnits=" + numberOfUnits
				+ "}";
	}

	@Override
	public int compareTo(Product compareProduct) {
		return name.compareToIgnoreCase(compareProduct.getName());
	}

}
