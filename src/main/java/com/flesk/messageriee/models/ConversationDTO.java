package com.flesk.messageriee.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversationDTO {
    private String senderId;
    private String receiverId;
    private String chanelId;
    @Setter
    private String message;

    public String getMessage() {
        return message;
    }

}