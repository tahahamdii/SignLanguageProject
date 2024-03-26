// ChanelController.java
package com.flesk.messageriee.controllers;

import com.flesk.messageriee.models.Chanel;
import com.flesk.messageriee.services.ChanelService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/saveChanel")
    public Chanel saveChanel(@RequestBody Chanel chanel) {
        return chanelService.saveChanel(chanel);
    }


    @DeleteMapping("/{id}")
    public void deleteChanel(@PathVariable String id) {
        chanelService.deleteChanel(id);
    }
}
