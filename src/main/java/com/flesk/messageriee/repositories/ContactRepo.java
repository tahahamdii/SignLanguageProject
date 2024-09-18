package com.flesk.messageriee.repositories;

import com.flesk.messageriee.models.Contact;
import com.flesk.messageriee.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ContactRepo extends MongoRepository<Contact, String> {
    Optional<Contact> findByEmail(String email);
    boolean existsByEmail(String email);

    void deleteByEmail(String email);
    List<Contact> findByNameContainingIgnoreCase(String name);

}
