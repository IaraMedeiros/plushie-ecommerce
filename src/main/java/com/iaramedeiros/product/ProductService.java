package com.iaramedeiros.product;

import com.iaramedeiros.user.User;
import com.iaramedeiros.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired private ProductRepository repo;

    public List<Product> listAll(){
        return (List<Product>) repo.findAll();
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


