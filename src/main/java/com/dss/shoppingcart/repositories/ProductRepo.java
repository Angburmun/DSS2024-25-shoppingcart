package com.dss.shoppingcart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dss.shoppingcart.models.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {}
