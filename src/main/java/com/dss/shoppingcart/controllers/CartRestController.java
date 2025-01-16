package com.dss.shoppingcart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dss.shoppingcart.models.Product;
import com.dss.shoppingcart.services.CartService;
import com.dss.shoppingcart.services.PdfService;
import com.dss.shoppingcart.services.ProductService;

@RestController
@RequestMapping("/api/cart")
public class CartRestController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PdfService pdfService;

    // Endpoint para ver los productos en el carrito
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getCartProducts() {
        List<Product> cartProducts = cartService.getCartProducts();
        return ResponseEntity.ok(cartProducts);
    }

    // Endpoint para añadir un producto al carrito
    @PostMapping("/add/{id}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + id));
        cartService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto añadido al carrito");
    }

    // Endpoint para eliminar un producto del carrito
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Long id) {
        cartService.removeProduct(id);
        return ResponseEntity.ok("Producto eliminado del carrito");
    }

    // Endpoint para proceder al checkout
    @PostMapping("/checkout")
    public ResponseEntity<String> checkout() {
        List<Product> products = cartService.getCartProducts();
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El carrito está vacío. No es posible proceder al pago.");
        }
        return ResponseEntity.ok("Compra finalizada");
    }

    // Endpoint para limpiar el carrito
    @PostMapping("/clear")
    public ResponseEntity<String> clearCart() {
        cartService.clearCart();
        return ResponseEntity.ok("Carrito limpiado");
    }

    // Endpoint para descargar la factura en formato PDF
    @GetMapping("/download-invoice")
    public ResponseEntity<byte[]> downloadInvoice() {
        List<Product> products = cartService.getCartProducts();
        byte[] pdfBytes = pdfService.generateInvoice(products);
        
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=factura.pdf")
                .body(pdfBytes);
    }
}
