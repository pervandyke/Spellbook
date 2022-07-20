package com.vandyke.demoproject.service;

import org.springframework.stereotype.Service;

import com.vandyke.demoproject.model.Spell;

@Service
public class SpellService {
    
    /**
     * Will eventually save the spell to a DB, and return that entity. For now just returns the given spell.
     * 
     * @param spell The provided spell from the front end.
     * @return The spell entity from the DB.
     */
    public Spell createSpell(Spell spell) {
        return spell;
    }

}
