package com.flesk.messageriee.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private byte[] photoData;
    private List<Contact> contacts;
    public Status status;




    public User (String username ,String email ,String birthday , String password , String resetCode , String role ,  List<Contact> contacts){

        this.username=username;
        this.email=email;
        this.birthday=birthday;
        this.password=password;
        this.resetCode=resetCode;
        this.role=role;
        this.contacts = contacts;



    }


}
