package com.flesk.messageriee.configuration;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new MyWebSocketHandler(), "/chat").setAllowedOrigins("*");
    }

    private static class MyWebSocketHandler implements WebSocketHandler {
        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            System.out.println("Connexion établie avec le client : " + session.getId());
        }

        @Override
        public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
            System.out.println("Message reçu du client " + session.getId() + " : " + message.getPayload());
        }

        @Override
        public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
            System.err.println("Erreur de transport pour le client " + session.getId() + " : " + exception.getMessage());
        }

        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
            System.out.println("Connexion fermée pour le client " + session.getId() + ", raison : " + closeStatus.getReason());
        }

        @Override
        public boolean supportsPartialMessages() {
            return false;
        }

    }
}
