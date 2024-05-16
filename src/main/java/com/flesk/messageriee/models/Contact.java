package com.flesk.messageriee.models;

import lombok.Data;

@Data
public class Contact {

    private String id;
    private String email;

    public Contact(String name, String email) {

        this.email = email;
    }
}
