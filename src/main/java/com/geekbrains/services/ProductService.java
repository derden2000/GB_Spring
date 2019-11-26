package com.geekbrains.services;

import com.geekbrains.entities.Product;
import com.geekbrains.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getProductList();
    }

    public Product getProductById(long id) {
        return productRepository.getProductById(id);
    }

    public void addNewProduct(long id, String title, BigDecimal price) {
        productRepository.addNewProduct(id, title, price);
    }

    public void changeProduct(long id, String title, BigDecimal price) {
        productRepository.changeItem(id, title, price);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
