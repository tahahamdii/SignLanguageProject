// ChanelController.java
package com.flesk.messageriee.controllers;

import com.flesk.messageriee.models.Chanel;
import com.flesk.messageriee.services.ChanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chanels")
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

    @PostMapping("/save")
    public ResponseEntity<Chanel> saveChanel(@RequestBody Chanel chanel) {
        Chanel savedChanel = chanelService.saveChanel(chanel);
        return new ResponseEntity<>(savedChanel, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public void deleteChanel(@PathVariable String id) {
        chanelService.deleteChanel(id);
    }
}
