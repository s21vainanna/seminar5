package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Table(name = "product_table")//this will be a table in DB
@Entity
public class Product {
	
	@Column(name = "Title")//H2 -> TITLE, Mysql -> title
	@NotNull
	@Size(min = 3, max = 150 )
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only Latin letters and space")//only latin letters and space
	private String title;
	
	
	@Column(name = "Description")
	@NotNull
	@Size(min = 5, max =400)
	@Pattern(regexp = "[A-Z]{1}[a-z0-9\\ ]+", message = "Only Latin letters and space")//only latin letters and space
	private String description;
	
	@Column(name = "Price")
	@Min(0)
	@Max(10000)
	private float price;

	@Column(name = "Quantity")
	@Min(0)
	@Max(1000000)
	private int quantity;
	
	@Column(name = "Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getId() {
		return id;
	}

	public Product(String title, String description, float price, int quantity) {
		this.title = title;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Product() {}
	@Override
	public String toString() {
		return "Product [title=" + title + ", description=" + description + ", price=" + price + ", quantity="
				+ quantity + ", id=" + id + "]";
	}

	
	
}