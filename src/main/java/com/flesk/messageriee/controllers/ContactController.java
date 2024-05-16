package com.flesk.messageriee.controllers;

import com.flesk.messageriee.models.Contact;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final List<Contact> contacts = new ArrayList<>();



    @GetMapping
    public List<Contact> getAllContacts() {
        return contacts;
    }

    @PostMapping
    public Contact addContact(@RequestBody Contact contact) {
        contacts.add(contact);
        return contact;
    }



}