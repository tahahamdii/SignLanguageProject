// ConversationController.java
package com.flesk.messageriee.controllers;

import com.flesk.messageriee.services.ConversationService;
import com.flesk.messageriee.models.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    @Autowired
    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping
    public List<Conversation> getAllConversations() {
        return conversationService.getAllConversations();
    }

    @GetMapping("/{id}")
    public Optional<Conversation> getConversationById(@PathVariable String id) {
        return conversationService.getConversationById(id);
    }

    @PostMapping("/saveConversation")
    public Conversation saveConversation(@RequestBody Conversation conversation) {
        return conversationService.saveConversation(conversation);
    }

    @DeleteMapping("/{id}")
    public void deleteConversation(@PathVariable String id) {
        conversationService.deleteConversation(id);
    }
}
