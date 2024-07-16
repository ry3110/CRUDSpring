package com.crudapi.CRUDSpring.service;

import com.crudapi.CRUDSpring.entity.ProductEntity;
import com.crudapi.CRUDSpring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    public ProductEntity saveProduct(ProductEntity product){
        return repository.save(product);
    }


    public List<ProductEntity> saveProducts(List<ProductEntity> products) {
        return repository.saveAll(products);
    }

    public List<ProductEntity> getProducts() {
        return repository.findAll();
    }

    public ProductEntity getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    public ProductEntity getProductByName(String name) {
        return repository.findByName(name);
    }

    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "product removed !! " + id;
    }

    public ProductEntity updateProduct(ProductEntity product) {
        ProductEntity existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        return repository.save(existingProduct);
    }

}
