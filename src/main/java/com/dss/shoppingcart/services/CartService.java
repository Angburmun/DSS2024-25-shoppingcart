package com.dss.shoppingcart.services;

import org.springframework.stereotype.Service;

import com.dss.shoppingcart.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final List<Product> cartProducts = new ArrayList<>();

    public List<Product> getCartProducts() { return new ArrayList<>(cartProducts); }
    
    public void addProduct(Product product) { cartProducts.add(product); }

    public void removeProduct(Long productId) {
        Optional<Product> productToRemove = cartProducts.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
        productToRemove.ifPresent(cartProducts::remove);
    }
}
