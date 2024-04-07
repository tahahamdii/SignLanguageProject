package com.flesk.messageriee.repositories;
import com.flesk.messageriee.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimestampAsc(String userId1, String userId2, String userId21, String userId11);

    List<Message> findBySenderIdOrReceiverId(String userId, String userId1);
}
