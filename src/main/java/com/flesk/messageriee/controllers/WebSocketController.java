package com.flesk.messageriee.controllers;

import com.flesk.messageriee.models.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSocketController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Message();
    }

    @MessageMapping("/connect") // Endpoint pour la connexion WebSocket
    public void connect() {
        System.out.println("WebSocket connected");
    }
}
