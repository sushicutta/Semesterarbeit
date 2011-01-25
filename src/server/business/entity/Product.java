package server.business.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(
			name=Product.ALL,
			query="Select p from Product p order by p.id asc"),
	@NamedQuery(
			name=Product.COUNT,
			query="select count(p) from Product p")
})
public class Product extends server.business.entity.Entity implements Serializable, Comparable<Product> {
	
	private static final long serialVersionUID = 8092491969425592554L;

	public final static String ALL = "all";
	public final static String COUNT = "count";

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
		return "Product{id=" + id + " name=" + name + " numberOfUnits=" + numberOfUnits + "}";
	}

	@Override
	public int compareTo(Product compareProduct) {
		return name.compareToIgnoreCase(compareProduct.getName());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + numberOfUnits;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numberOfUnits != other.numberOfUnits)
			return false;
		return true;
	}
	
}
