package com.vandyke.demoproject.SpellbookAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vandyke.demoproject.SpellbookAPI.exeptions.UserNotFoundException;
import com.vandyke.demoproject.SpellbookAPI.exeptions.SpellNotFoundException;
import com.vandyke.demoproject.SpellbookAPI.frontEndObjects.SpellData;
import com.vandyke.demoproject.SpellbookAPI.models.ResponseString;
import com.vandyke.demoproject.SpellbookAPI.models.Spell;
import com.vandyke.demoproject.SpellbookAPI.services.SpellService;

@RestController
@CrossOrigin
public class SpellController {

    @Autowired
    SpellService spellService;

    
    // Endpoints
    
    @PostMapping(value = "/spells")
    public ResponseEntity<Spell> createSpell(@RequestBody SpellData spell) throws UserNotFoundException {
        System.out.println("Recieved New Spell");
        return ResponseEntity.status(HttpStatus.CREATED).body(spellService.createOrEditSpell(spell));
    }

    @PutMapping(value = "/spells/{spellId}")
    public ResponseEntity<Spell> editSpell(@RequestBody SpellData spell) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(spellService.createOrEditSpell(spell));
    }
    
    /*@GetMapping(value = "/spells")
    public ResponseEntity<List<SpellData>> getSpells() {
        return ResponseEntity.status(HttpStatus.OK).body(spellService.getSpells());
    }*/

    /*@GetMapping(value = "/spells/{spellId}")
    public ResponseEntity<SpellData> getSpellById(@PathVariable Long spellId) throws SpellNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(spellService.getSpellById(spellId));
    }*/

    @GetMapping(value = "/user/{userId}/spells")
    public ResponseEntity<List<SpellData>> getUsersSpells(@PathVariable Long userId) throws SpellNotFoundException, UserNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(spellService.getUserSpells(userId));
    }

    @DeleteMapping(value = "/spells/{spellId}")
    public ResponseEntity<ResponseString> deleteSpell(@PathVariable Long spellId) throws SpellNotFoundException {
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
