package com.flesk.messageriee.handler;


import com.flesk.messageriee.dto.MessageDto;
import com.flesk.messageriee.services.ChatService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;




@Component
public class ChatWebSocketHandler {

    private final ChatService chatsService;
    private final Map<String, WebSocketSession> userSessions = new HashMap<>();

    public ChatWebSocketHandler(ChatService chatsService) {
        this.chatsService = chatsService;
    }


    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = getUserId(session);
        if (userId != null) {
            userSessions.put(userId, session);
        }
    }


    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = getUserId(session);
        if (userId != null) {
            userSessions.remove(userId);
        }
    }


//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        try {
//            String userId = getUserId(session); // Obtenez l'ID de l'utilisateur à partir de la session
//            if (userId != null) {
//                // Récupérez le contenu du message
//                String messageContent = message.getPayload();
//
//                // Créez un objet DTO de message à partir du contenu du message
//                MessageDto messageDto = new MessageDto();
//                messageDto.setContent(messageContent);
//                messageDto.setSenderId(userId);
//
//                // Appelez la méthode du service ChatsService pour traiter le message
//                chatsService.processMessage(messageDto);
//
//                // Vous pouvez également renvoyer une confirmation au client si nécessaire
//                session.sendMessage(new TextMessage("Message processed successfully"));
//            } else {
//                // Gérez le cas où l'ID de l'utilisateur n'a pas pu être obtenu
//                session.sendMessage(new TextMessage("Failed to process message: User ID not found"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Gérez les exceptions et renvoyez une réponse appropriée au client
//            session.sendMessage(new TextMessage("Failed to process message: " + e.getMessage()));
//        }
//    }

    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            String userId = getUserId(session); // Obtenez l'ID de l'utilisateur à partir de la session
            if (userId != null) {
                // Récupérez le contenu du message
                String messageContent = message.getPayload();

                // Analysez le contenu du message pour extraire l'ID du destinataire et le contenu du message
                String[] parts = messageContent.split(":");
                if (parts.length == 2) {
                    String recipientId = parts[0];
                    String content = parts[1];

                    // Créez un objet DTO de message à partir du contenu du message
                    MessageDto messageDto = new MessageDto();
                    messageDto.setContent(content);
                    messageDto.setSenderId(userId);
                    messageDto.setRecipientId(recipientId);

                    // Appelez la méthode du service ChatsService pour traiter le message
                    chatsService.processMessage(messageDto);

                    // Vous pouvez également renvoyer une confirmation au client si nécessaire
                    session.sendMessage(new TextMessage("Message processed successfully"));
                } else {
                    // Gérez le cas où le format du message est incorrect
                    session.sendMessage(new TextMessage("Failed to process message: Invalid message format"));
                }
            } else {
                // Gérez le cas où l'ID de l'utilisateur n'a pas pu être obtenu
                session.sendMessage(new TextMessage("Failed to process message: User ID not found"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Gérez les exceptions et renvoyez une réponse appropriée au client
            session.sendMessage(new TextMessage("Failed to process message: " + e.getMessage()));
        }
    }


    private String getUserId(WebSocketSession session) {
        try {
            // Récupérer le token JWT à partir des attributs de la session WebSocket
            String jwtToken = (String) session.getAttributes().get("jwtToken");

            // Analyser le token JWT pour extraire l'ID de l'utilisateur
            if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
                jwtToken = jwtToken.substring(7); // Supprimer le préfixe "Bearer "
                Claims claims = Jwts.parser().setSigningKey("your_secret_key").parseClaimsJws(jwtToken).getBody();
                return claims.getSubject(); // Renvoyer l'ID de l'utilisateur extrait du token JWT
            }

            return null; // Retourner null si le token JWT n'est pas trouvé ou n'est pas valide
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Gérer les erreurs en renvoyant null
        }
    }




}







