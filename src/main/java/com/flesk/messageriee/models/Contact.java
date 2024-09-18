package com.flesk.messageriee.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Getter
@Setter
@Data
public class Contact {
    @Id
    private String id;
    private String name;
    private String email;
    @DBRef
    private List<User> users;

    public void setUser(User user) {
    }
}
