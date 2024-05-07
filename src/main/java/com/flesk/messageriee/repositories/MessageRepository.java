package com.flesk.messageriee.repositories;
import com.flesk.messageriee.models.Chanel;
import com.flesk.messageriee.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

//    List<Message> findBySenderIdAndRecipientIdOrSenderIdAndRecipientIdOrderByTimestampAsc(String userId1, String userId2, String userId21, String userId11);
//
//    List<Message> findBySenderIdOrRecipientId(String userId, String userId1);


    List<Message> findByRecipientId(String recipientId);


    List<Message> findByChatId(String chatId);

    List<Chanel> findConversationByMembers(String firstUserId, String secondUserId);

//    List<Chanel> findAllConversations();
    @Query("{_id: '$chatId'}")
    List<String> findAllConversations();
}
