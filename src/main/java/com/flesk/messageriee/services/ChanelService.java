package com.flesk.messageriee.services;
import com.flesk.messageriee.models.Chanel;
import com.flesk.messageriee.repositories.ChanelRepository;
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
}
