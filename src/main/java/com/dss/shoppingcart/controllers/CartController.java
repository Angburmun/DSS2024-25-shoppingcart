package com.dss.shoppingcart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dss.shoppingcart.models.Product;
import com.dss.shoppingcart.services.CartService;
import com.dss.shoppingcart.services.PdfService;
import com.dss.shoppingcart.services.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PdfService pdfService;
    
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
    
    @GetMapping("/checkout")
    public String checkout(Model model) {
    	List<Product> products = cartService.getCartProducts();
    	if (products.isEmpty()) {
            model.addAttribute("error", "El carrito está vacío. No es posible proceder al pago.");
            return "cart";
        }
    	
        return "thank-you";
    }
    
    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/products";
    }
    
    @GetMapping("/download-invoice")
    public ResponseEntity<byte[]> downloadInvoice() {
        List<Product> products = cartService.getCartProducts();
        byte[] pdfBytes = pdfService.generateInvoice(products);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=factura.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

}
