//package com.flesk.messageriee.controllers;
//
//import com.flesk.messageriee.models.Contact;
//import com.flesk.messageriee.models.User;
//import com.flesk.messageriee.repositories.ContactRepo;
//import com.flesk.messageriee.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/contacts")
//
//public class ContactController {
//
//    private final ContactRepo contactRepository;
//    private final UserRepository userRepository;
//
//    @Autowired
//    public ContactController(ContactRepo contactRepository, UserRepository userRepository) {
//        this.contactRepository = contactRepository;
//        this.userRepository = userRepository;
//    }
//    @GetMapping("/get")
//    public List<Contact> getAllContacts() {
//        return contactRepository.findAll();
//    }
//
////    @PostMapping("/add")
////    public ResponseEntity<?> addContact(@RequestBody Contact contact) {
////        // Check if a contact with the same email already exists
////        if (contactRepository.existsByEmail(contact.getEmail())) {
////            return ResponseEntity.status(HttpStatus.CONFLICT).body("Contact with the same email already exists");
////        }
////        // Check if a user with the same email already exists
////        if (userRepository.existsByEmail(contact.getEmail())) {
////            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with the same email already exists");
////        }
////
////        // If the contact doesn't exist, save it
////        return ResponseEntity.ok(contactRepository.save(contact));
////    }
//@PostMapping("/add")
//public ResponseEntity<?> addContact(@RequestBody Contact contact, @AuthenticationPrincipal UserDetails userDetails) {
//    User user = userRepository.findByEmail(userDetails.getUsername());
//    if (user == null) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }
//
//    // Check if a contact with the same email already exists for this user
//    if (user.getContacts().stream().anyMatch(c -> c.getEmail().equals(contact.getEmail()))) {
//        return ResponseEntity.status(HttpStatus.CONFLICT).body("Contact with the same email already exists");
//    }
//
//    contact.setUser(user);
//    user.getContacts().add(contact);
//    userRepository.save(user); // This will also save the contact due to cascading
//
//    return ResponseEntity.ok(contact);
//}
//
//    @DeleteMapping("/delete/{email}")
//    public ResponseEntity<?> deleteContactByEmail(@PathVariable String email) {
//        if (!contactRepository.existsByEmail(email)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
//        }
//
//        contactRepository.deleteByEmail(email);
//        return ResponseEntity.ok("Contact deleted successfully");
//    }
//
//
//}
package com.flesk.messageriee.controllers;

import com.flesk.messageriee.models.Contact;
import com.flesk.messageriee.models.User;
import com.flesk.messageriee.repositories.ContactRepo;
import com.flesk.messageriee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Contact>> getAllContacts(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(user.getContacts());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addContact(@RequestBody Contact contact, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Contact> existingContact = contactRepository.findByEmail(contact.getEmail());
        if (existingContact.isPresent()) {
            Contact sharedContact = existingContact.get();
            if (!user.getContacts().contains(sharedContact)) {
                user.getContacts().add(sharedContact);
                userRepository.save(user);
            }
            return ResponseEntity.ok(sharedContact);
        } else {
            contact.getUsers().add(user);
            user.getContacts().add(contact);
            contactRepository.save(contact);
            return ResponseEntity.ok(contact);
        }
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteContactByEmail(@PathVariable String email, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Contact contact = user.getContacts().stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if (contact == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
        }

        user.getContacts().remove(contact);
        contact.getUsers().remove(user);
        contactRepository.save(contact);

        return ResponseEntity.ok("Contact deleted successfully");
    }
}
