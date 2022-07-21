package com.vandyke.demoproject.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.vandyke.demoproject.model.Spell;

@Service
public class SpellService {
    
    /*
     * Temporary persistence.
    */
    private static final AtomicLong counter = new AtomicLong();
    private static List<Spell> spells;

    /**
     * Will eventually save the spell to a DB, and return that entity.
     * 
     * @param spell The provided spell from the front end.
     * @return A confirmation String.
     */
    public String createSpell(Spell spell) {
        spell.setId(counter.incrementAndGet());
        spells.add(spell);
        return "Spell " + spell.getName() + " was added with ID " + spell.getId() + ".";
    }

    public List<Spell> getSpells() {
        return spells;
    }



}
