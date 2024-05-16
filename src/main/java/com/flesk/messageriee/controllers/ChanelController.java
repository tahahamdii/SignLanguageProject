package com.flesk.messageriee.controllers;

import com.flesk.messageriee.models.Chanel;
import com.flesk.messageriee.models.User;
import com.flesk.messageriee.services.ChanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chanels")
public class ChanelController {

    private final ChanelService chanelService;

    @Autowired
    public ChanelController(ChanelService chanelService) {
        this.chanelService = chanelService;
    }

    @GetMapping
    public List<Chanel> getAllChanels() {
        return chanelService.getAllChanels();
    }

    @GetMapping("/{id}")
    public Optional<Chanel> getChanelById(@PathVariable String id) {
        return chanelService.getChanelById(id);
    }


    @PostMapping("/savech")
    public ResponseEntity<Chanel> saveUser(@RequestBody Chanel chanel) {
        Chanel savedChanel = chanelService.saveChanel(chanel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedChanel);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createChannel(@RequestBody ChanelService.CreateChannelRequest request) {
        String userId = request.getUserId();
        String recipientId = request.getRecipientId();



        String channelId = chanelService.createChannel(userId, recipientId);

        return new ResponseEntity<>(channelId, HttpStatus.CREATED);
    }

//    @GetMapping("/find")
//    public Optional<Chanel> getChanelByUsers(@RequestParam String userId, @RequestParam String recipientId) {
//        return chanelService.getChanelByUsers(userId, recipientId);
//    }




    @DeleteMapping("/{id}")
    public void deleteChanel(@PathVariable String id) {
        chanelService.deleteChanel(id);
    }

}
