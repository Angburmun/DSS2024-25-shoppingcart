package com.dss.shoppingcart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dss.shoppingcart.models.Product;
import com.dss.shoppingcart.repositories.ProductRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() { return productRepo.findAll(); }

    public Optional<Product> getProductById(Long id) { return productRepo.findById(id); }

    public Product saveProduct(Product product) { return productRepo.save(product); }

    public void deleteProduct(Long id) { productRepo.deleteById(id); }
    
    public List<Product> searchProductsByName(String name) { return productRepo.findByNameContainingIgnoreCase(name); }
    
    public List<Product> filterProductsByPrice(Double minPrice, Double maxPrice) {
        if (minPrice == null && maxPrice == null) {
            return productRepo.findAll();
        } else if (minPrice == null) {
            return productRepo.findByPriceLessThanEqual(maxPrice);
        } else if (maxPrice == null) {
            return productRepo.findByPriceGreaterThanEqual(minPrice);
        } else {
            return productRepo.findByPriceBetween(minPrice, maxPrice);
        }
    }
}
