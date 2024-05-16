package com.flesk.messageriee.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageWithTimestamp {
    private Message message;
    private LocalDateTime timestamp;

    public MessageWithTimestamp(Message message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
