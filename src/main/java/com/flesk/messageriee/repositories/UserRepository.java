package com.flesk.messageriee.repositories;
import com.flesk.messageriee.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends MongoRepository<User, String> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByResetCode(String resetCode);
    User findContactsById(String userId);


    List<User> findByUsernameContaining(String username);
}