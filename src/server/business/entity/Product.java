package server.business.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements Comparable<Product> {

	@Size(min = 3, max = 20, message = "minimum 3, maximum 20 Zeichen für den Namen")
	private String name;

	@Max(1000)
	private int numberOfUnits;

	public Product() {
	}

	public Product(final String name, final int numberOfUnits) {
		this.name = name;
		this.numberOfUnits = numberOfUnits;
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
