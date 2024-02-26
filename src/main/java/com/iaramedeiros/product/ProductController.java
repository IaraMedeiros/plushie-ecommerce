package com.iaramedeiros.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired ProductService service;

    @GetMapping(value="/products")
    public String showProductList(Model model){
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);

        return "products";
    }
}
