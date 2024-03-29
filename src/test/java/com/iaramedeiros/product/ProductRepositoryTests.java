package com.iaramedeiros.product;

import com.iaramedeiros.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {
    @Autowired private ProductRepository repo; // Spring deve injetar uma instância de UserRepository nesta variável

    @Test
    public void testAddNew() throws IOException {

        // Carregar a imagem do arquivo
        File file = new File("C:\\Users\\iara3\\IdeaProjects\\CrudWebSpring\\src\\main\\resources\\images\\raccoon.png");
        byte[] imgBytes = Files.readAllBytes(file.toPath());

        Product product2 = new Product(null,"Guaxinim", "Um lindo e fofo guaxinim, adorável!", 55.0,imgBytes,"gray", "Small");

        // Salvar o produto no banco de dados
        Product savedProduct = repo.save(product2);

        Product retrievedProduct = repo.findById(savedProduct.getId()).orElse(null);


        // Verificar se o produto foi salvo com sucesso
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getId()).isGreaterThan(0);
        assertThat(retrievedProduct.getImg().length).isEqualTo(imgBytes.length);

    }
}

