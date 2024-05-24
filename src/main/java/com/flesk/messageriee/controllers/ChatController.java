package com.flesk.messageriee.controllers;


import com.flesk.messageriee.models.Message;
import com.flesk.messageriee.models.MessageWithTimestamp;
import com.flesk.messageriee.services.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chats")
public class ChatController {


    private final ChatService chatsService;


    public ChatController(ChatService chatsService) {
        this.chatsService = chatsService;
    }



    @PostMapping("/add")
    public ResponseEntity<MessageWithTimestamp> createMessage(@RequestBody Message message) {
        // Définir le timestamp actuel
        LocalDateTime timestamp = LocalDateTime.now();
        message.setTimestamp(timestamp);

        // Enregistrer le message avec le timestamp
        Message savedMessage = chatsService.createMessage(message);

        // Créer une réponse avec le message et le timestamp
        MessageWithTimestamp messageWithTimestamp = new MessageWithTimestamp(savedMessage, timestamp);

        return ResponseEntity.ok(messageWithTimestamp);
    }



    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = chatsService.getAllMessages();
        return ResponseEntity.ok(messages);
    }


    @GetMapping("/messages/by-sender/{senderId}")
    public Map<String, List<Message>> getAllMessagesBySenderId(@PathVariable("senderId") String senderId) {
        List<Message> messages = chatsService.getAllMessagesBySenderId(senderId);
        Map<String, List<Message>> response = new HashMap<>();
        response.put("data", messages);
        return response;
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public Map<String, Object> getAllMessagesBySenderAndRecipientId(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId) {
        List<Message> messages = chatsService.getAllMessagesBySenderAndRecipientId(senderId, recipientId);
        List<Message> filteredMessages = new ArrayList<>();

        // Filtrer les messages pour n'inclure que ceux entre le senderId et le recipientId
        for (Message message : messages) {
            if (message.getSenderId().equals(senderId) && message.getRecipientId().equals(recipientId) ||
                    message.getSenderId().equals(recipientId) && message.getRecipientId().equals(senderId)) {
                filteredMessages.add(message);
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("senderId", senderId);
        response.put("recipientId", recipientId);
        response.put("messages", filteredMessages);
        return response;
    }





    @GetMapping("/message/{chatId}")
    public ResponseEntity<List<Message>> getMessagesForChat(@PathVariable("chatId") String chatId) {
        List<Message> messages = chatsService.getMessagesForChat(chatId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/conversation/{firstUserId}/{secondUserId}")
    public ResponseEntity<Map<String, List<MessageWithTimestamp>>> findConversationByMembers(
            @PathVariable("firstUserId") String firstUserId,
            @PathVariable("secondUserId") String secondUserId
    ) {
        List<Message> conversation = chatsService.findConversationByMembers(firstUserId, secondUserId);
        List<MessageWithTimestamp> conversationWithTimestamp = new ArrayList<>();

        // Ajouter le timestamp à chaque message
        LocalDateTime now = LocalDateTime.now();
        for (Message message : conversation) {
            conversationWithTimestamp.add(new MessageWithTimestamp(message, now));
        }

        // Construire la réponse avec la clé "data" et la liste de messages avec leurs horodatages
        Map<String, List<MessageWithTimestamp>> responseData = new HashMap<>();
        responseData.put("data", conversationWithTimestamp);

        return ResponseEntity.ok(responseData);
    }



    @GetMapping("/conversation")
    public ResponseEntity<List<String>> findAllConversations() {
        List<String> conversations = chatsService.findAllConversations();
        return ResponseEntity.ok(conversations);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getMessage());
    }


}
