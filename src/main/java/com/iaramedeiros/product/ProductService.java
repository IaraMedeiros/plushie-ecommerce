package com.iaramedeiros.product;

import com.iaramedeiros.user.User;
import com.iaramedeiros.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired private ProductRepository repo;

    private Map<String, List<Product>> result = new HashMap<>();

    public List<Product> listAll(){
        return (List<Product>) repo.findAll();
    }

    public List<Product> ListByPriceRange(double minPrice, double maxPrice){
        return (List<Product>) repo.findProductsByPriceRange(minPrice, maxPrice);
    }

    public List<Product> orderByPrice(){
        List<Product> productsByPrice = repo.findAllByOrderByPrice();
        result.put("menorPreco", productsByPrice);
        return productsByPrice;
    }

    public List<Product> orderByPriceDesc(){
        List<Product> productsByPriceDesc = repo.findAllByOrderByPriceDesc();
        result.put("maiorPreco", productsByPriceDesc);
        return productsByPriceDesc;
    }

    public List<Product> orderByName(){
        List<Product> productsByName = repo.findAllByOrderByName();
        result.put("AZ", productsByName);
        return productsByName;
    }

    public List<Product> orderByNameDesc(){
        List<Product> productsByNameDesc = repo.findAllByOrderByNameDesc();
        result.put("ZA", productsByNameDesc);
        return productsByNameDesc;
    }

    public List<Product> orderByCriteria(String type){
        return result.get(type);
    }

    public Product get(Integer id) throws ProductNotFoundException {
        Optional<Product> product = repo.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        throw new ProductNotFoundException("Could not find any products with ID " + id);
    }

    public void delete(Integer id) throws ProductNotFoundException {
        if(id == null || id == 0) {
            throw new ProductNotFoundException("Invalid ID: " + id);
        }

        Optional<Product> product = repo.findById(id);
        if(product.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new ProductNotFoundException("Product not found with ID: " + id);
        }
    }


}


