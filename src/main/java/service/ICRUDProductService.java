package service;
import java.util.ArrayList;
import model.Product;

public interface ICRUDProductService {
	//CRUD
	
	//C - create
	public abstract void addNewProduct(String title, String description,
			float price, int quantity) throws Exception;
	
	//R - retrieve - all
	public abstract ArrayList<Product> retrieveAllProducts();
	
	//R - retrieve - by id
	public abstract Product retrieveProductById(long id) throws Exception;
	
	//U - update
	public abstract void updateById(long id,String title, String description,
			float price, int quantity) throws Exception;
	
	//D - delete
	public abstract void deleteById(long id) throws Exception;
	
}