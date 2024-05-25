package com.flesk.messageriee.services;

import com.flesk.messageriee.models.ChatMessage;
import com.flesk.messageriee.repositories.ChatMessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepo chatMessageRepo;
    private ChatroomService chatroomService;

    public ChatMessage save(ChatMessage chatMessage){
        var chatId = chatroomService.getChatRoomId(
                chatMessage.getSenderId(),
                chatMessage.getRecipientId(),
                true
        ).orElseThrow();
        chatMessage.setChatId(chatId);
        chatMessageRepo.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(
            String senderId,
            String recipientId

    ) {
        var chatId = chatroomService.getChatRoomId(
                senderId,
                recipientId,
                false
        );
        return chatId.map(chatMessageRepo::findByChatId).orElse(new ArrayList<>());
    }
}
