package com.example.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.Exception.ProdectException;
import com.example.Model.Category;
import com.example.Model.Product;
import com.example.Model.Size;
import com.example.Repository.CategoryRepository;
import com.example.Repository.ProductRepository;
import com.example.Request.createProductRequest;

@Service
public class ProductServiceImplementation implements ProductService {

	private CategoryRepository categoryRepository;
	private UserService userService;
	private ProductRepository productRepository;

	public ProductServiceImplementation(CategoryRepository categoryRepository, UserService userService,
			ProductRepository productRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.userService = userService;
		this.productRepository = productRepository;
	}

	@Override
	public Product createProduct(createProductRequest req) {

		Category topLevel = categoryRepository.findByName(req.getTopLevelCategory());
		if (topLevel == null) {
			Category topLevelCategory = new Category();
			topLevelCategory.setName(req.getTopLevelCategory());
			topLevelCategory.setLevel(1);

			topLevel = categoryRepository.save(topLevelCategory);
		}

		Category secondLevel = categoryRepository.findByNameAndParant(req.getSecondLevelCategory(), topLevel.getName());
		if (secondLevel == null) {
			Category secondLevelCategory = new Category();
			secondLevelCategory.setName(req.getSecondLevelCategory());
			secondLevelCategory.setParentCategory(topLevel);
			secondLevelCategory.setLevel(2);

			secondLevel = categoryRepository.save(secondLevelCategory);
		}

		Category thirdLevel = categoryRepository.findByNameAndParant(req.getThirdLevelCategory(),
				secondLevel.getName());
		if (thirdLevel == null) {
			Category thirdLevelCategory = new Category();
			thirdLevelCategory.setName(req.getThirdLevelCategory());
			thirdLevelCategory.setParentCategory(secondLevel);
			thirdLevelCategory.setLevel(3);

			thirdLevel = categoryRepository.save(thirdLevelCategory);
		}

		Product product = new Product();
		product.setTitle(req.getTitle());
		product.setDescription(req.getDescription());
		product.setPrice(req.getPrice());
		product.setDiscountedPrice(req.getDiscountedPrice());
		product.setDiscountedPersent(req.getDiscountPersent());
		product.setQuantity(req.getQuantity());
		product.setBrand(req.getBrand());
		product.setColor(req.getColor());
		product.setSizes(req.getSize());
		product.setImageUrl(req.getImageUrl());
		product.setCategory(thirdLevel);
		product.setCreatedAt(LocalDateTime.now());

		Product saveProduct = productRepository.save(product);

		return saveProduct;
	}

	@Override
	public String deleteProduct(Long productId) throws ProdectException {
		Product product = findProductById(productId);
		product.getSizes().clear();
		productRepository.delete(product);
		return "Product delete Successfully...";
	}

	@Override
	public Product updateProduct(Long productId, Product req) throws ProdectException {
		Product product = findProductById(productId);
		if (req.getQuantity() != 0) {
			product.setQuantity(req.getQuantity());
		}
		productRepository.delete(product);
		return product;
	}

	@Override
	public Product findProductById(Long productId) throws ProdectException {
		Optional<Product> opt = productRepository.findById(productId);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new ProdectException("Product Not found With Id : " + productId);
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> getAllProduct(String category, List<String> color, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize,List<String>size) {
		
		if(pageNumber<0)pageNumber=0;
		PageRequest pageable = PageRequest.of(pageNumber, pageSize);
		
		List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);
		if(size!=null)
			if (!size.isEmpty()) {
			    System.out.println("Filtering by sizes: " + size);
			    products = products.stream()
			        .filter(p -> {
			            boolean matches = p.getSizes().stream()
			                .anyMatch(s -> size.stream()
			                    .anyMatch(c -> c.equalsIgnoreCase(s.getName())));
			            return matches;
			        })
			        .collect(Collectors.toList());
			}
		if(color!=null)
		if (!color.isEmpty()) {
			products = products.stream().filter(p -> color.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
					.collect(Collectors.toList());
		}
		if (stock != null) {
			if (stock.equals("in_stock")) {
				products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
			} else if (stock.equals("out_of_stock")) {
				products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
			}
		}
		
		int startIndex = (pageNumber)*pageSize;
	    int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());
	    // Handle cases where the page is empty
	    if (startIndex >= products.size()) {
	        return new PageImpl<>(Collections.emptyList(), pageable, products.size());
	    }

	    List<Product> pageContent = products.subList(startIndex, endIndex); 
	    return new PageImpl<>(pageContent, pageable, products.size());
	}

}
