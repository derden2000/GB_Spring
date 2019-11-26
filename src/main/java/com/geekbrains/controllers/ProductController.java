package com.geekbrains.controllers;


import com.geekbrains.entities.Product;
import com.geekbrains.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/show_form")
    public String showSimpleProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @GetMapping("/change_form/{id}")
    public String showChangeProductForm(Model model, @PathVariable Long id) {
        if (id != null) {
            model.addAttribute("product", productService.getProductById(id));
        } else {
            throw new RuntimeException("id продукта не может быть пустым ");
        }
        return "change_product_form";
    }

    @RequestMapping(path = "/show_all", method = RequestMethod.GET)
    public String showAllProducts(Model model) {
        List<Product> productsList = productService.getAllProducts();
        model.addAttribute("products", productsList);
        return "all_products";
    }

    @PostMapping("/process_form")
    public String processForm(@ModelAttribute("product") Product product) {
        productService.addNewProduct(product.getId(), product.getTitle(), product.getPrice());
        return  "product_form_result";
    }

    @PostMapping("/change_product_form")
    public String changeProcessForm(@ModelAttribute("product") Product product) {
        productService.changeProduct(product.getId(), product.getTitle(), product.getPrice());
        return  "product_form_result";
    }

    @RequestMapping(path = "/info/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @RequestMapping(path = "/info/", method = RequestMethod.GET)
    @ResponseBody
    public Product getProductByParamId(@RequestParam(value = "id", required = false) Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/delete_product/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        productService.deleteById(id);
        List<Product> productsList = productService.getAllProducts();
        model.addAttribute("products", productsList);
        return "all_products";
    }

}
