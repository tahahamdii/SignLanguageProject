package com.flesk.messageriee.controllers;

import com.flesk.messageriee.models.Contact;
import com.flesk.messageriee.repositories.ContactRepo;
import com.flesk.messageriee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")

public class ContactController {

    private final ContactRepo contactRepository;
    private final UserRepository userRepository;

    @Autowired
    public ContactController(ContactRepo contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }
    @GetMapping("/get")
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addContact(@RequestBody Contact contact) {
        // Check if a contact with the same email already exists
        if (contactRepository.existsByEmail(contact.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Contact with the same email already exists");
        }
        // Check if a user with the same email already exists
        if (userRepository.existsByEmail(contact.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with the same email already exists");
        }

        // If the contact doesn't exist, save it
        return ResponseEntity.ok(contactRepository.save(contact));
    }
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteContactByEmail(@PathVariable String email) {
        if (!contactRepository.existsByEmail(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
        }

        contactRepository.deleteByEmail(email);
        return ResponseEntity.ok("Contact deleted successfully");
    }


}
