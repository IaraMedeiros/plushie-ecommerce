package com.iaramedeiros.product;

import com.iaramedeiros.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {
    @Autowired private ProductRepository repo; // Spring deve injetar uma instância de UserRepository nesta variável

    @Test
    public void testAddNew()  {
        Product product = new Product();

    }
}

