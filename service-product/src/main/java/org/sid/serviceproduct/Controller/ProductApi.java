package org.sid.serviceproduct.Controller;

import java.util.List;

import org.sid.serviceproduct.Entity.Product;
import org.sid.serviceproduct.Repo.ProductRepo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductApi {
	
	private ProductRepo productRepo;
	
	public ProductApi(ProductRepo productRepo) {
		super();
		this.productRepo = productRepo;
	}

	@GetMapping("products")
	List<Product> getProducts(){
		return productRepo.findAll();
	}
	
	@PostMapping("addProduct")
	Product addProduct(@RequestBody Product product) {
		return productRepo.save(product);
	}
	
	@PutMapping("updateProduct/{id}")
	Product updateProduct(@RequestBody Product product,Integer id) {
		Product prdct=productRepo.findById(id).orElseThrow(()-> new RuntimeException("product "+id+" not fount"));
		
		if(product.getName()!=null) prdct.setName(product.getName());
		if(product.getQuantity()!=null) prdct.setQuantity(product.getQuantity());
		
		return productRepo.save(prdct);	
	}
	
	@DeleteMapping("deleyById/{id}")
	void deleteById(Integer id) {
		 productRepo.deleteById(id);
	}

}
