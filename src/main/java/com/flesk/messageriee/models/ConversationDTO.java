package com.flesk.messageriee.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversationDTO {
    private String senderId;
    private String receiverId;
    private String chanelId;
    private String message;

    public String getMessage() { // Getter pour récupérer le message
        return message;
    }

    public void setMessage(String message) { // Setter pour définir le message
        this.message = message;
    }
    // Getters and setters
}