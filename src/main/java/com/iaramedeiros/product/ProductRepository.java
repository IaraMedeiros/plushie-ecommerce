package com.iaramedeiros.product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Integer> {
    @Query("SELECT p FROM Product p WHERE p.price > :minPrice AND p.price < :maxPrice")
    List<Product> findProductsByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
    List<Product> findAllByOrderByPrice();
    List<Product> findAllByOrderByPriceDesc();

    List<Product> findAllByOrderByName();

    List<Product> findAllByOrderByNameDesc();




}
