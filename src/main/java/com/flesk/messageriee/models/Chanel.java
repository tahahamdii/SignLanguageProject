package com.flesk.messageriee.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Calendar;
import java.util.List;


@Data
@AllArgsConstructor
@Document(collection = "Chanel")
public class Chanel {

    @Id
    private String id;

    private String content;

    @DBRef
    private List<Message> messages;

    public Chanel() {
    }

    public Chanel(String content) {
        this.content = content;
    }

    public void setUserId(String userId) {
    }

    public void setRecipientId(String recipientId) {
    }
}
