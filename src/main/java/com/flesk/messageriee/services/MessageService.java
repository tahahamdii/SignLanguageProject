package com.flesk.messageriee.services;
import com.flesk.messageriee.models.Message;
import com.flesk.messageriee.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    public Optional<Message> getMessageById(String id) {
        return messageRepository.findById(id);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public void deleteMessage(String id) {
        messageRepository.deleteById(id);
    }

    public List<Message> getMessagesByParticipants(String userId1, String userId2) {
        return messageRepository.findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimestampAsc(
                userId1, userId2, userId2, userId1);
    }

    public List<Message> getAllMessagesByUser(String userId) {
        return messageRepository.findBySenderIdOrReceiverId(userId, userId);

    }


}
