package com.flesk.messageriee.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "User")
@Getter
@Setter
public class User {

    @Id

    private String id;
    private String username;
    private String email;
    private String birthday;
    private String password;
    private String resetCode;
    private String role;
    private Image image;
    //private List<Contact> contacts = new ArrayList<>(); // Initialize the contacts list
    private Status status;

    @DBRef
    private List<Contact> contacts;


    public User (String username ,String email ,String birthday , String password , String resetCode , String role ,  List<Contact> contacts){

        this.username=username;
        this.email=email;
        this.birthday=birthday;
        this.password=password;
        this.resetCode=resetCode;
        this.role=role;
        this.contacts = contacts != null ? contacts : new ArrayList<>(); // Initialize if null
        this.status = Status.OFFLINE; // Initialiser le statut par défaut à OFFLINE



    }


}
