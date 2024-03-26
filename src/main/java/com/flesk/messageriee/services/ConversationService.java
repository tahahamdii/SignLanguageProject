package com.flesk.messageriee.services;
import com.flesk.messageriee.models.Conversation;
import com.flesk.messageriee.repositories.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public List<Conversation> getAllConversations() {
        return (List<Conversation>) conversationRepository.findAll();
    }

    public Optional<Conversation> getConversationById(String id) {
        return conversationRepository.findById(id);
    }

    public Conversation saveConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    public void deleteConversation(String id) {
        conversationRepository.deleteById(id);
    }
}

