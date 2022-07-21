package com.vandyke.demoproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vandyke.demoproject.model.Spell;
import com.vandyke.demoproject.service.SpellService;

@RestController
public class SpellbookController {

    @Autowired
    SpellService spellService;
    
    @RequestMapping(value = "/spells", method = RequestMethod.POST)
    public ResponseEntity<String> addSpell(@RequestBody Spell spell) {
        System.out.println("Get all spells request.");
        return ResponseEntity.status(HttpStatus.CREATED).body(spellService.createSpell(spell));
    }
    
    @RequestMapping(value = "/spells", method = RequestMethod.GET)
    public ResponseEntity<List<Spell>> getSpells() {
        System.out.println("Add Spell Request.");
        return ResponseEntity.status(HttpStatus.OK).body(spellService.getSpells());
    }
}
