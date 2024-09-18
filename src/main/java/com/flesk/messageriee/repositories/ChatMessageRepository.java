package com.flesk.messageriee.repositories;

import com.flesk.messageriee.models.ChatMessage;
import com.flesk.messageriee.models.Chatroom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {


    List<ChatMessage> findByChatId(String s);
}
