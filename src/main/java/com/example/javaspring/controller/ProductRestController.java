package com.example.javaspring.controller;

import com.example.javaspring.entities.Product;
import com.example.javaspring.service.ProductSevice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping("/product")
public class ProductRestController {
    ProductSevice productSevice;

    public ProductRestController(ProductSevice productSevice) {
        this.productSevice = productSevice;
    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Product product) {

        try {
            productSevice.save(product);
            return new ResponseEntity(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getall")
    public ResponseEntity getall() {
        return productSevice.getall();
    }

    @GetMapping("/delete")
    public ResponseEntity delete(@RequestParam String id) {
        return productSevice.delete(id);
    }

    @GetMapping("/search")
    public ResponseEntity searchTitleandDetail(@RequestParam String q) {
        return productSevice.search(q);

    }

    @GetMapping("/pricesearch")
    public ResponseEntity searchPriceBetween(@RequestParam double price1, double price2) {
        return productSevice.searchPrice(price1, price2);
    }
}
