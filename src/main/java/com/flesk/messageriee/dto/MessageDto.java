package com.flesk.messageriee.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Setter
public class MessageDto {

    @Id
    private String id;

    @Field(name = "message")
    private String message;

    @Field(name = "senderId")
    private String senderId;

    @Field(name = "recipientId")
    private String recipientId;


    @Field(name = "recipientId")
    private String content;
    public MessageDto() {
    }

    public MessageDto(String content ) {
        this.content = content;

    }


}