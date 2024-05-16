package com.flesk.messageriee.repositories;
import com.flesk.messageriee.models.Chanel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChanelRepository extends MongoRepository<Chanel, String> {

   // Optional<Chanel> findByUserIdAndRecipientId(String userId, String recipientId);
}
