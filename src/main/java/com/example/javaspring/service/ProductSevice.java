package com.example.javaspring.service;

import com.example.javaspring.entities.Product;
import com.example.javaspring.repostories.ProductRepostories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductSevice {
    ProductRepostories productRepostories;

    public ProductSevice(ProductRepostories productRepostories) {
        this.productRepostories = productRepostories;
    }

    public ResponseEntity save(Product product) {
        try {
            if (product.getPrice() <= 9) {
                throw new Exception();
            }
            productRepostories.save(product);
            return new ResponseEntity(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(product, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        //Product product1 = new Product();
        //product1.setTitle(product.getTitle());
        //if (product.getPrice() <= 9) {
        //    return new ResponseEntity(product1, HttpStatus.valueOf("Tutar 10 Tl den Düşük olamaz"));
        //}
        //product1.setPrice(product.getPrice());
        //product1.setDetail(product.getDetail());
        //productRepostories.save(product1);
        //return new ResponseEntity(product1, HttpStatus.OK);
    }

    public ResponseEntity getall() {
        Map<String, Object> hm = new HashMap<>();
        hm.put("All Products", productRepostories.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity delete(String id) {
        Map<String, Object> hm = new HashMap<>();
        int pid = Integer.parseInt(id);
        try {
            productRepostories.deleteById(pid);
            hm.put("Success", true);
        } catch (Exception e) {
            hm.put("Status", false);
            hm.put("Request parameter", id);
            return new ResponseEntity(hm, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity update(Product product) {
        Map<String, Object> hm = new HashMap<>();
        Optional<Product> optional = productRepostories.findById(product.getPid());
        if (optional.isPresent()) {
            productRepostories.saveAndFlush(product);
            hm.put("Status", true);
            hm.put("Result", product);
        } else {
            hm.put("Status", false);
            hm.put("Result", "The ID entered was not found.");
            return new ResponseEntity(hm, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity search(String q) {
        Map<String, Object> hm = new HashMap<>();
        boolean status = false;
        List<Product> productList = productRepostories.findByTitleContainsIgnoreCaseOrDetailContainsIgnoreCase(q, q);
        long count = productRepostories.countByTitleContainsIgnoreCaseOrDetailContainsIgnoreCase(q, q);
        if (count > 0) {
            status = true;
            hm.put("Status: ", status);
            hm.put("Search total count: ", count);
            hm.put("Product List ", productList);
        }
        hm.put("Status: ", status);
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }

    public ResponseEntity searchPrice(double price1, double price2) {
        Map<String, Object> hm = new HashMap<>();
        boolean status = false;
        List<Product> productList = productRepostories.findByPriceBetween(price1, price2);
        long countByPriceBetween = productRepostories.countByPriceBetween(price1, price2);
        if (countByPriceBetween > 0) {
            status = true;
            hm.put("Status: ", status);
            hm.put("Search total count: ", countByPriceBetween);
            hm.put("Product List ", productList);
        }
        hm.put("Status: ", status);
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }
}
