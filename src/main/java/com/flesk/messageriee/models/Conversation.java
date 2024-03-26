package com.flesk.messageriee.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "Conversation")
@Getter
@Setter
@AllArgsConstructor
@Data
public class Conversation {

    @Id
    private String id;

    private User sender;

    private User receiver;

    private String content;

    private LocalDateTime timestamp;

    private List<User> participants;



}
