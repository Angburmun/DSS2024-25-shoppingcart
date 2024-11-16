package com.dss.shoppingcart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dss.shoppingcart.models.Product;
import com.dss.shoppingcart.services.CartService;
import com.dss.shoppingcart.services.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cartProducts", cartService.getCartProducts());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addProductToCart(@PathVariable Long id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + id));
        cartService.addProduct(product);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeProductFromCart(@PathVariable Long id) {
        cartService.removeProduct(id);
        return "redirect:/cart";
    }
}
