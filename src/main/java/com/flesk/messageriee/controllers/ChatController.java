package com.flesk.messageriee.controllers;


import com.flesk.messageriee.models.Chanel;
import com.flesk.messageriee.models.Message;
import com.flesk.messageriee.services.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {


    private final ChatService chatsService;


    public ChatController(ChatService chatsService) {
        this.chatsService = chatsService;
    }



    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message savedMessage = chatsService.createMessage(message);
        return ResponseEntity.ok(savedMessage);
    }



    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = chatsService.getAllMessages();
        return ResponseEntity.ok(messages);
    }


    @GetMapping("/messages/{userId}")
    public ResponseEntity<List<Message>> getAllMessagesForUser(@PathVariable("userId") String userId) {
        List<Message> messages = chatsService.getAllMessagesForUser(userId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/message/{chatId}")
    public ResponseEntity<List<Message>> getMessagesForChat(@PathVariable("chatId") String chatId) {
        List<Message> messages = chatsService.getMessagesForChat(chatId);
        return ResponseEntity.ok(messages);
    }

  @GetMapping("/conversation/{firstUserId}/{secondUserId}")
  public ResponseEntity<List<Chanel>> findConversationByMembers(
            @PathVariable("firstUserId") String firstUserId,
            @PathVariable("secondUserId") String secondUserId
    ) {
        List<Chanel> conversation = chatsService.findConversationByMembers(firstUserId, secondUserId);
        return ResponseEntity.ok(conversation);
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
