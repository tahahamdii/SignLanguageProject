//package com.flesk.messageriee.controllers;
//import com.flesk.messageriee.models.Message;
//import com.flesk.messageriee.models.User;
//import com.flesk.messageriee.services.MessageService;
//import com.flesk.messageriee.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/messages")
//public class MessagingController {
//
//    private final UserService userService;
//    private final MessageService messageService;
//
//    @Autowired
//    public MessagingController(UserService userService, MessageService messageService) {
//        this.userService = userService;
//        this.messageService = messageService;
//    }
//
//    @DeleteMapping("/{messageId}")
//    public ResponseEntity<String> deleteMessage(@PathVariable String messageId) {
//        messageService.deleteMessage(messageId);
//        return ResponseEntity.ok("Message deleted successfully");
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<Message>> getAllMessagesByUser(@PathVariable String userId) {
//        List<Message> messageList = messageService.getAllMessagesByUser(userId);
//        return ResponseEntity.ok(messageList);
//    }
//
//
//}