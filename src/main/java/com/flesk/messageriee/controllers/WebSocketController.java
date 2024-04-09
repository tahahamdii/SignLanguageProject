//package com.flesk.messageriee.controllers;
//
//import com.flesk.messageriee.models.*;
//import com.flesk.messageriee.repositories.ChanelRepository;
//import com.flesk.messageriee.repositories.MessageRepository;
//import com.flesk.messageriee.repositories.UserRepository;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping("/api/chat")
//public class WebSocketController {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private MessageRepository conversationRepository;
//    @Autowired
//    private ChanelRepository chanelRepository;
//
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//
//    @Autowired
//    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
//        this.messagingTemplate = messagingTemplate;
//    }
//
//
//    @PostMapping("/start-conversation")
//    public ResponseEntity<String> startConversation(@RequestBody ConversationDTO conversationDTO) {
//        // Recherche de l'utilisateur
//        User sender = userRepository.findById(conversationDTO.getSenderId()).orElse(null);
//        User receiver = userRepository.findById(conversationDTO.getReceiverId()).orElse(null);
//        if (sender == null || receiver == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
//        }
//        // Créer une nouvelle conversation
//        Message conversation = new Message();
//        conversation.setSender(sender);
//        conversation.setReceiver(receiver);
//        // Définir d'autres propriétés de la conversation si nécessaire
//        conversationRepository.save(conversation);
//        // Mettre à jour le chanel
//        Chanel chanel = chanelRepository.findById(conversationDTO.getChanelId()).orElse(null);
//        if (chanel == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chanel non trouvé");
//        }
//
//        // Envoyer un message via WebSocket
//        messagingTemplate.convertAndSend("/topic/conversations", conversation);
//        return ResponseEntity.ok("Conversation démarrée avec succès");
//    }
//}
