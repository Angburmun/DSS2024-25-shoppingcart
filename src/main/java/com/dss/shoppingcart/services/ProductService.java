package com.dss.shoppingcart.services;

import com.dss.shoppingcart.model.Product;
import com.dss.shoppingcart.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) { this.productRepo = productRepo; }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
    }

    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        if (!productRepo.existsById(id)) {
            throw new IllegalArgumentException("Product not found with ID: " + id);
        }
        productRepo.deleteById(id);
    }
}
