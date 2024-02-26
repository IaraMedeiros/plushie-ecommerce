package com.iaramedeiros;

import com.iaramedeiros.user.User;
import com.iaramedeiros.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest // teste de persistência de dados com JPA
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // configurar o banco de dados de teste.
@Rollback(false) // desativa o rollback automático das transações de teste.
public class UserRepositoryTests {

    @Autowired private UserRepository repo; // Spring deve injetar uma instância de UserRepository nesta variável

    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("testing@gmail.com");
        user.setPassword("5443524");
        user.setFirstName("Jane");
        user.setLastName("Dove");

        User savedUser = repo.save(user);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
@Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();

        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("hello2020");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("hello2020");


    }

    @Test
    public void testDelete(){
        Integer userId = 2;
        repo.deleteById(userId);
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();


    }
}
