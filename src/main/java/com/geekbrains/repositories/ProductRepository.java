package com.geekbrains.repositories;

import com.geekbrains.entities.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> productList;

    @PostConstruct
    public void init() {
        productList = new ArrayList<Product>();
        productList.add(new Product(1, "Milk", new BigDecimal(50)));
        productList.add(new Product(2, "Water", new BigDecimal(30)));
        productList.add(new Product(3, "Bread", new BigDecimal(40)));
    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public Product getProductById(long id) {
        Product product = null;
        for (Product productItem : productList) {
            if (productItem.getId()==id) {
                product = productItem;
            }
        }
        return product;
    }

    public void addNewProduct(long id, String title, BigDecimal price) {
        productList.add(new Product(id, title, price));
    }

    public void changeItem(long id, String title, BigDecimal price) {
        getProductById(id).setTitle(title);
        getProductById(id).setPrice(price);
    }

    public void deleteById(Long id) {
        productList.remove(getProductById(id));
    }
}
