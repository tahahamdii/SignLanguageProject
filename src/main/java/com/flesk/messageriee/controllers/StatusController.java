package com.flesk.messageriee.controllers;

import com.flesk.messageriee.models.Status;
import com.flesk.messageriee.models.User;
import com.flesk.messageriee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class StatusController {

    @Autowired
    private UserRepository userRepository;

    @MessageMapping("/status")
    @SendTo("/topic/status")
    public User updateStatus(User user) {
        // Mettre à jour le statut de l'utilisateur dans la base de données
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setStatus(user.getStatus());
            userRepository.save(existingUser);
        }
        return user; // Retourne l'utilisateur mis à jour avec le nouveau statut
    }
}
