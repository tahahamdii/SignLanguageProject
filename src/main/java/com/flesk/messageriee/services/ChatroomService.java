package com.flesk.messageriee.services;

import com.flesk.messageriee.models.Chatroom;
import com.flesk.messageriee.repositories.ChatroomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatroomService {

    private final ChatroomRepo chatroomRepo;

    public Optional<String> getChatRoomId(
            String senderId,
            String recipientId,
            boolean createNewRoomIfNotExists
    ) {
        return chatroomRepo.findBySenderIdAndRecipientId(senderId,recipientId)
                .map(Chatroom::getChatId)
                .or(()-> {
                    if(createNewRoomIfNotExists){
                        var chatId = createChatId(senderId,recipientId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });
    }

    private String createChatId(String senderId, String recipientId) {
        var chatId = String.format("%s_%s",senderId,recipientId);

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
        return null ;
    }
}
