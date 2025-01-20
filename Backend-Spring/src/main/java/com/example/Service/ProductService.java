package com.example.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.Exception.ProdectException;
import com.example.Model.Product;
import com.example.Model.Size;
import com.example.Request.createProductRequest;

public interface ProductService {
	public Product createProduct(createProductRequest req);
	public String deleteProduct(Long productId)throws ProdectException;
	public Product updateProduct(Long productId,Product req)throws ProdectException;
	public Product findProductById(Long productId)throws ProdectException;
	public List<Product> findProductByCategory(String category);
	public Page<Product> getAllProduct(String category,List<String>color ,Integer minPrice,Integer maxPrice,Integer minDiscount,String sort,String stock,Integer pageNumber,Integer pageSize,List<String>size);
}
