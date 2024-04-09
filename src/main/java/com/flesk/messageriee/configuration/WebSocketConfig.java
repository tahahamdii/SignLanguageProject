//package com.flesk.messageriee.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.WebSocketMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.config.annotation.*;
//
//@Configuration
//@EnableWebSocket
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//@Bean
//    public SimpMessagingTemplate simpMessagingTemplate(SimpMessageSendingOperations messagingTemplate) {
//        return new SimpMessagingTemplate((MessageChannel) messagingTemplate);
//    }
//
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(new MyWebSocketHandler(), "/chat").setAllowedOrigins("*");
//    }
//
//    private static class MyWebSocketHandler implements WebSocketHandler {
//        @Override
//        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//            System.out.println("Connexion établie avec le client : " + session.getId());
//        }
//
//        @Override
//        public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//            System.out.println("Message reçu du client " + session.getId() + " : " + message.getPayload());
//        }
//
//        @Override
//        public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//            System.err.println("Erreur de transport pour le client " + session.getId() + " : " + exception.getMessage());
//        }
//
//        @Override
//        public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//            System.out.println("Connexion fermée pour le client " + session.getId() + ", raison : " + closeStatus.getReason());
//        }
//
//        @Override
//        public boolean supportsPartialMessages() {
//            return false;
//        }
//
//    }
//}
