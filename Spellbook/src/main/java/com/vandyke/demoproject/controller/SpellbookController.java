package com.vandyke.demoproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vandyke.demoproject.model.ResponseString;
import com.vandyke.demoproject.model.Spell;
import com.vandyke.demoproject.service.SpellService;

@RestController
public class SpellbookController {

    @Autowired
    SpellService spellService;
    
    @RequestMapping(value = "/spells", method = RequestMethod.POST)
    public ResponseEntity<ResponseString> addSpell(@RequestBody Spell spell) {
        System.out.println("Add Spell Request.");
        return ResponseEntity.status(HttpStatus.CREATED).body(spellService.createSpell(spell));
    }
    
    @RequestMapping(value = "/spells", method = RequestMethod.GET)
    public ResponseEntity<List<Spell>> getSpells() {
        System.out.println("Get all spells request.");
        return ResponseEntity.status(HttpStatus.OK).body(spellService.getSpells());
    }

    /*@RequestMapping(value = "/user/{userId}/spells")
    public ResponseEntity<List<Spell>> getUserSpells(@RequestBody Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(spellService.getUserSpells(userId));
    }*/

    @RequestMapping(value = "/spells/{spellId}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseString> deleteSpell(@PathVariable Long spellId) {
        return ResponseEntity.status(HttpStatus.OK).body(spellService.deleteSpell(spellId));
    }
}
