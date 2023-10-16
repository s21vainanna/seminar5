package service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Product;
import repos.IProductRepo;
import service.ICRUDProductService;


@Service
public class CRUDproductServiceImpl implements ICRUDProductService {

	@Autowired
	private IProductRepo productRepo;

	@Override
	public void addNewProduct(String title, String description, float price, int quantity) throws Exception {

		if (productRepo.existsByTitleAndDescriptionAndPrice(title, description, price)) {
			Product temp = productRepo.findByTitleAndDescriptionAndPrice(title, description, price);
			temp.setQuantity(temp.getQuantity() + quantity);
			productRepo.save(temp);// in this case the existing product will be updated in DB
		} else {
			Product newProduct = new Product(title, description, price, quantity);
			productRepo.save(newProduct);// in this case - new product will be saved in DB
		}
	}

	@Override
	public ArrayList<Product> retrieveAllProducts() {
		return (ArrayList<Product>) productRepo.findAll();
	}

	@Override
	public Product retrieveProductById(long id) throws Exception {
		if (id > 0) {

			if (productRepo.existsById(id)) {
				Product temp = productRepo.findById(id).get();
				return temp;
			} else {
				throw new Exception("There is not product with this ID");
			}
		} else {
			throw new Exception("ID need to be positive");
		}
	}

	@Override
	public void updateById(long id, String title, String description, float price, int quantity) throws Exception {
		if (id > 0) {
			// verify if this product exists with this id
			if (productRepo.existsById(id)) {
				// true -> get this product from database
				Product temp = productRepo.findById(id).get();
				// set all paramaters
				temp.setTitle(title);
				temp.setDescription(description);
				temp.setPrice(price);
				temp.setQuantity(quantity);
				// save updated produvct in DB
				productRepo.save(temp);

			} else {
				throw new Exception("There is not product with this ID");
			}

		} else {
			throw new Exception("ID need to be positive");
		}
	}

	@Override
	public void deleteById(long id) throws Exception {

		if (productRepo.existsById(id)) {
			productRepo.deleteById(id);
		} else {
			throw new Exception("There is not product with this ID");
		}

	}

}