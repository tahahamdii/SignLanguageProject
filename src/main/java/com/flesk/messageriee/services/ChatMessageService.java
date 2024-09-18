package com.flesk.messageriee.services;

import com.flesk.messageriee.models.ChatMessage;
import com.flesk.messageriee.repositories.ChatMessageRepository;
import com.flesk.messageriee.repositories.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private static final Logger logger = LoggerFactory.getLogger(ChatMessageService.class);

    private final ChatMessageRepository chatMessageRepository;
    private final ChatroomService chatroomService;

    public ChatMessage save(ChatMessage chatMessage){
        if (chatMessage.getSenderId().equals(chatMessage.getRecipientId())) {
            throw new IllegalArgumentException("Sender ID and Recipient ID cannot be the same");
        }
        var chatId = chatroomService.getChatRoomId(
                chatMessage.getSenderId(),
                chatMessage.getRecipientId(),
                true
        ).orElseThrow();
        chatMessage.setChatId(chatId);
        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);
        logger.info("Saved message with ID: {}, Chat ID: {}, Content: {}", savedMessage.getId(), savedMessage.getChatId(), savedMessage.getContent());
        return savedMessage;
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatroomService.getChatRoomId(senderId, recipientId, false);
        logger.info("Chat ID for sender: {}, recipient: {} is: {}", senderId, recipientId, chatId.orElse(null));
        List<ChatMessage> messages = chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
        logger.info("Found {} messages for Chat ID: {}", messages.size(), chatId.orElse(null));
        return messages;
    }

    public void deleteMessage(String id) {
        chatMessageRepository.deleteById(id);

    }
}
