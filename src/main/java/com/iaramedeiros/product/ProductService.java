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

    public List<Product> listAll(){
        return (List<Product>) repo.findAll();
    }

    public List<Product> ListByPriceRange(double minPrice, double maxPrice){
        return (List<Product>) repo.findProductsByPriceRange(minPrice, maxPrice);
    }

    public List<Product> orderByPrice(){
        return repo.findAllByOrderByPrice();
    }

    public List<Product> orderByPriceDesc(){
        return repo.findAllByOrderByPriceDesc();
    }

    public List<Product> orderByName(){
        return repo.findAllByOrderByName();
    }

    public List<Product> orderByNameDesc(){
        return repo.findAllByOrderByNameDesc();
    }

    public Map<Integer, List<Product>> doAll() {
        Map<Integer, List<Product>> result = new HashMap<>();
        result.put(1, orderByPrice());
        result.put(2,orderByName());
        result.put(3,orderByNameDesc());
        result.put(4,orderByPriceDesc());
        return result;
    }

    public List<Product> orderByCriteria(Integer type){
        Map<Integer, List<Product>> productMap = doAll();
        return productMap.get(type);
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


