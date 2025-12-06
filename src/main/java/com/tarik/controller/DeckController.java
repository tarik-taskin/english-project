package com.tarik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarik.dto.DeckDto;
import com.tarik.service.DecksService;

@RequestMapping("/deck")
@RestController
public class DeckController {

    @Autowired
    DecksService decksService;

    @GetMapping(path = "/getAll")
    public List<DeckDto> getAllDecks() {
        return decksService.getAllDecks();
    }

    @PutMapping(path = "/updateLastRepeatDate/{id}")
    public Boolean updateDecksLastRepeatDate(@PathVariable Long id) {
        return decksService.updateDecksLastRepeatDate(id);
    }

}
