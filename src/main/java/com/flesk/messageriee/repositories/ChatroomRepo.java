package com.flesk.messageriee.repositories;

import com.flesk.messageriee.models.Chatroom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatroomRepo extends MongoRepository<Chatroom, String> {

    Optional<Chatroom> findBySenderIdAndRecipientId(String senderId, String recipientId);

    // Optional<Chanel> findByUserIdAndRecipientId(String userId, String recipientId);
}
