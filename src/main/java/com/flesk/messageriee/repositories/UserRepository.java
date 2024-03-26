package com.flesk.messageriee.repositories;
import com.flesk.messageriee.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends MongoRepository<User, String> {
   

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    User findByUsername(String username);

    User findByEmail(String email);


    //Optional<User> findByUsername(String username);
}