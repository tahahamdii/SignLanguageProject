package com.flesk.messageriee.services;

import com.flesk.messageriee.models.Chatroom;
import com.flesk.messageriee.repositories.ChatroomRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatroomService {

    private static final Logger logger = LoggerFactory.getLogger(ChatroomService.class);

    private final ChatroomRepo chatroomRepo;

    public Optional<String> getChatRoomId(
            String senderId,
            String recipientId,
            boolean createNewRoomIfNotExists
    ) {
        logger.info("Retrieving chat room for senderId: {}, recipientId: {}", senderId, recipientId);
        return chatroomRepo.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(chatroom -> {
                    logger.info("Found chat room: {}", chatroom.getChatId());
                    return chatroom.getChatId();
                })
                .or(() -> {
                    if (createNewRoomIfNotExists) {
                        var chatId = createChatId(senderId, recipientId);
                        logger.info("Created new chat room: {}", chatId);
                        return Optional.of(chatId);
                    }
                    logger.info("No chat room found and not creating new one for senderId: {}, recipientId: {}", senderId, recipientId);
                    return Optional.empty();
                });
    }

    private String createChatId(String senderId, String recipientId) {
        var chatId = String.format("%s_%s", senderId, recipientId);

        Chatroom senderRecipient = Chatroom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        Chatroom recipientSender = Chatroom.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        chatroomRepo.save(senderRecipient);
        chatroomRepo.save(recipientSender);

        logger.info("Saved chat room entries for chatId: {}", chatId);

        return chatId;
    }
}
