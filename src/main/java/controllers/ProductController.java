package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import model.Product;
import service.CRUDproductServiceImpl;

@Controller
public class ProductController {
	
	@Autowired
	private CRUDproductServiceImpl CRUDservice;
	
	@GetMapping("/select") //localhost:8080/all-products-find?id=2
	public String selectAll(Model model) {
		{
			model.addAttribute("packet", CRUDservice.retrieveAllProducts());
			return "all-products-page";// will show all-products-page.html
		}		
	}
	
	@GetMapping("/select/{id}")//localhost:8080/all-products/2
	public String selectById(@PathVariable(name="id") long id, Model model) {
		try {
			Product prod = CRUDservice.retrieveProductById(id);
			model.addAttribute("packet", prod);
			return "one-product-page";//will call one-product-page.html
		}
		catch (Exception e) {
			model.addAttribute("packetError", e.getMessage());
			return "error-page";//will call error-page.html
		}		
	}
	
	@GetMapping("/insert")//localhost:8080/add-product
	public String insertNewProductGet(Model model) {
		model.addAttribute("product", new Product());// send and empty product
		return "add-product-page";//will call add-product-page.html
	}
	
	@PostMapping("/insert")
	public String insertNewProductPost(Product product) {
		try
		{
			CRUDservice.addNewProduct(product.getTitle(), product.getDescription(),
				product.getPrice(), product.getQuantity());
			return "redirect:/all-products";//will call /all-products endpoint
		}
		catch (Exception e) {
			return "redirect:/error";
		}
	}/*
	
	@GetMapping("/update/{id}") //localhost:8080/update-product/2
	public String getUpdateProductFunc(@PathVariable("id") long id, Product product, Model model) {
	    if (id > 0) { // Check if the 'id' is a positive value.
	        for (int i = 0; i < allProducts.size(); i++) { // Loop through the 'allProducts' list.
	            Product temp = allProducts.get(i); // Get the 'i-th' element from the list.
	            if (temp.getId() == id) { // Check if the 'id' matches the product's ID.
	                model.addAttribute("product", temp); // Add the 'product' to the model.
	                return "update-product-page"; // Return the view for updating the product.
	            }
	        }
	    }

	    model.addAttribute("packetError", "Wrong ID at update"); // Add an error message to the model.
	    return "error-page"; // Return the error page.
	}

	
	@PostMapping("/update/{id}")
	public String postUpdateProductFunc(@PathVariable("id") long id, Product product) {
	    for (int i = 0; i < allProducts.size(); i++) {
	        if (allProducts.get(i).getId() == id) {
	            // Update the product details using setters
	            allProducts.get(i).setTitle(product.getTitle());
	            allProducts.get(i).setDescription(product.getDescription());
	            allProducts.get(i).setPrice(product.getPrice());
	            allProducts.get(i).setQuantity(product.getQuantity());

	            return "redirect:/all-products/" + id; // Redirect to a page displaying the updated product
	        }
	    }

	    return "redirect:/error"; // Redirect to an error page if the product with the given ID is not found
	}
	
	@GetMapping("/delete/{id}")//localhost:8080/delete-product/2
	public String getDeleteProductFunc(@PathVariable("id") long id, Model model) {
	    if (id > 0) {
	        // Use a for loop to iterate through the products
	        for (int i = 0; i < allProducts.size(); i++) {
	            if (allProducts.get(i).getId() == id) {
	                // Remove the product from the list
	                allProducts.remove(i);
	                model.addAttribute("packet", allProducts);
	                return "all-products-page"; // Redirect to a page displaying the updated product list
	            }
	        }
	    }

	    model.addAttribute("packetError", "Wrong ID at delete");
	    return "error-page"; // Redirect to an error page if the product with the given ID is not found
	}*/
}