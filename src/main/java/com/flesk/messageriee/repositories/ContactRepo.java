package com.flesk.messageriee.repositories;

import com.flesk.messageriee.models.Contact;
import com.flesk.messageriee.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ContactRepo extends MongoRepository<Contact, String> {

    boolean existsByEmail(String email);

}
