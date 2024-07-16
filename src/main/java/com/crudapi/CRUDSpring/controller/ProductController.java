package com.crudapi.CRUDSpring.controller;

import com.crudapi.CRUDSpring.entity.ProductEntity;
import com.crudapi.CRUDSpring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping("/addProduct")
    public ResponseEntity<ProductEntity> addProduct(@RequestBody ProductEntity product) {
        ProductEntity savedProduct = service.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PostMapping("/addProducts")
    public List<ProductEntity> addProducts(@RequestBody List<ProductEntity> products) {
        return service.saveProducts(products);
    }

    @GetMapping("/products")
    public List<ProductEntity> findAllProducts() {
        return service.getProducts();
    }

    @GetMapping("/productById/{id}")
    public ProductEntity findProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

    @GetMapping("/product/{name}")
    public ProductEntity findProductByName(@PathVariable String name) {
        return service.getProductByName(name);
    }

    @PutMapping("/update")
    public ProductEntity updateProduct(@RequestBody ProductEntity product) {
        return service.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }
}
