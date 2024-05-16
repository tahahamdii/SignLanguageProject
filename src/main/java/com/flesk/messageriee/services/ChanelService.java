package com.flesk.messageriee.services;
import com.flesk.messageriee.models.Chanel;
import com.flesk.messageriee.repositories.ChanelRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChanelService {
    private final ChanelRepository chanelRepository;

    public ChanelService(ChanelRepository chanelRepository) {
        this.chanelRepository = chanelRepository;
    }

    public List<Chanel> getAllChanels() {
        return chanelRepository.findAll();
    }

    public Optional<Chanel> getChanelById(String id) {
        return chanelRepository.findById(id);
    }

    public Chanel saveChanel(Chanel chanel) {
        return chanelRepository.save(chanel);
    }

    public void deleteChanel(String id) {
        chanelRepository.deleteById(id);
    }

    public String createChannel(String userId, String recipientId) {
        // Créez une instance de CreateChannelRequest
        CreateChannelRequest request = new CreateChannelRequest();
        request.setUserId(userId);
        request.setRecipientId(recipientId);

        // Convertissez la demande en un objet Chanel
        Chanel chanel = new Chanel();
        chanel.setUserId(request.getUserId());
        chanel.setRecipientId(request.getRecipientId());

        // Enregistrez le canal en utilisant le repository
        Chanel savedChanel = chanelRepository.save(chanel);

        // Retournez l'identifiant du nouveau canal créé
        return savedChanel.getId();
    }

//    public Optional<Chanel> getChanelByUsers(String userId, String recipientId) {
//        return chanelRepository.findByUserIdAndRecipientId(userId, recipientId);
//    }


    @Getter
    @Setter
    public static class CreateChannelRequest {

        private String userId;

        private String recipientId;



        // Getters et Setters
    }

}
