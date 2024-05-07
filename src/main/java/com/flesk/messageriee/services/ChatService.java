package com.flesk.messageriee.services;

import com.flesk.messageriee.dto.MessageDto;
import com.flesk.messageriee.models.Chanel;
import com.flesk.messageriee.models.Message;
import com.flesk.messageriee.models.User;
import com.flesk.messageriee.repositories.MessageRepository;
import com.flesk.messageriee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.security.Principal;
import java.util.List;

@Service

public class ChatService {



@Autowired
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public ChatService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }


    public Message createMessage(Message message) {
        // Convertir le DTO en une entité Message
        Message msg = new Message();
        msg.setMessage(message.getMessage());
        msg.setSenderId(message.getSenderId());
        msg.setRecipientId(message.getRecipientId());

        // Enregistrer le message dans la base de données
        Message savedMessage = messageRepository.save(msg);

        return savedMessage;
    }


    public List<Message> getAllMessages() {
        return (List<Message>) messageRepository.findAll();
    }



    public List<Message> getAllMessagesForUser(String userId) {
        // Récupérer tous les messages pour l'utilisateur spécifié
        return messageRepository.findByRecipientId(userId);
    }






    public List<Message> getMessagesForChat(String chatId) {
        // Récupérer tous les messages pour le chat spécifié
        return messageRepository.findByChatId(chatId);
    }


    public List<Chanel> findConversationByMembers(String firstUserId, String secondUserId) {
        // Récupérer la conversation entre les deux membres spécifiés
        return messageRepository.findConversationByMembers(firstUserId, secondUserId);
    }



    public List<String> findAllConversations() {
        // Récupérer toutes les conversations disponibles
        return messageRepository.findAllConversations();
    }


    public User getUserFromSocket(Principal principal) throws AuthenticationException {
        if (principal != null) {
            // Utilisez le principal pour obtenir les détails de l'utilisateur authentifié
            String username = principal.getName();
            // Implémentez la logique pour récupérer l'utilisateur à partir de son nom d'utilisateur
            User user = userRepository.findByUsername(username);
            return user;
        } else {
            // Gérez le cas où l'utilisateur n'est pas authentifié
            throw new AuthenticationException("User not authenticated");
        }
    }
    public void processMessage(MessageDto messageDto) {
        try {
            // Vous pouvez implémenter la logique de traitement du message ici
            // Par exemple, vous pouvez enregistrer le message dans la base de données
            Message message = new Message();
            message.setContent(messageDto.getContent());
            message.setSenderId(messageDto.getSenderId());
            // Enregistrez le message dans la base de données
            messageRepository.save(message);

            // Vous pouvez également implémenter d'autres logiques de traitement ici,
            // comme envoyer des notifications aux destinataires du message, etc.
        } catch (Exception e) {
            // Gérez les exceptions ici, par exemple en journalisant l'erreur
            e.printStackTrace();

        }
    }
}




