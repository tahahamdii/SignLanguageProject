package com.flesk.messageriee.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "Message")
@Getter
@Setter
@AllArgsConstructor
@Data


    public class Message {
        @Id
        private String id;

        private String message;

        private String senderId;

        private String recipientId;

        private String content;

        @Setter
        @Getter
        private String members;

            @Setter
         @Getter
         private String chatId;

    // Autres champs et méthodes




    public Message() {

    }



}
