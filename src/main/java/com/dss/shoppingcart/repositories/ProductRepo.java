package com.dss.shoppingcart.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dss.shoppingcart.models.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	List<Product> findByNameContainingIgnoreCase(String name);
}
