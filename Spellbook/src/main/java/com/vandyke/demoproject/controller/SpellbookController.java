package com.vandyke.demoproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vandyke.demoproject.exceptions.SpellNotFoundException;
import com.vandyke.demoproject.exceptions.UserNotFoundException;
import com.vandyke.demoproject.frontEndObjects.SpellData;
import com.vandyke.demoproject.model.ResponseString;
import com.vandyke.demoproject.model.Spell;
import com.vandyke.demoproject.service.SpellService;

@RestController
public class SpellbookController {

    @Autowired
    SpellService spellService;

    // Endpoints
    
    @RequestMapping(value = "/spells", method = RequestMethod.POST)
    public ResponseEntity<ResponseString> createSpell(@RequestBody SpellData spell) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(spellService.createSpell(spell));
    }

    @RequestMapping(value = "/spells/{spellId}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseString> editSpell(@RequestBody SpellData spell) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(spellService.editSpell(spell));
    }
    
    @RequestMapping(value = "/spells", method = RequestMethod.GET)
    public ResponseEntity<List<SpellData>> getSpells() {
        return ResponseEntity.status(HttpStatus.OK).body(spellService.getSpells());
    }

    @RequestMapping(value = "/spells/{spellId}", method = RequestMethod.GET)
    public ResponseEntity<SpellData> getSpellById(@PathVariable Long spellId) throws SpellNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(spellService.getSpellById(spellId));
    }

    @RequestMapping(value = "/user/{userId}/spells", method = RequestMethod.GET)
    public ResponseEntity<List<SpellData>> getUsersSpells(@PathVariable Long userId) throws SpellNotFoundException, UserNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(spellService.getUserSpells(userId));
    }

    @RequestMapping(value = "/spells/{spellId}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseString> deleteSpell(@PathVariable Long spellId) throws UserNotFoundException, SpellNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(spellService.deleteSpell(spellId));
    }

    // Exception Handlers

    @ExceptionHandler(SpellNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Throwable> SpellNotFoundException(Throwable err) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Throwable> UserNotFoundExeption(Throwable err) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
