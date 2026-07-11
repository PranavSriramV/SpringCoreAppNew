package com.springapp.service;

import com.springapp.model.Product;
import com.springapp.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product findById(int id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product Id not found.."));
    }

    public void updateStock(int productId, int quantity) {
        productRepository.updateStock(productId, quantity);
    }

    public Map<String, Integer> countProductsByVendor() {
        return productRepository.countProductsByVendor();
    }
}
