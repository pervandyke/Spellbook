package com.vandyke.demoproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vandyke.demoproject.dao.SpellDao;
import com.vandyke.demoproject.model.ResponseString;
import com.vandyke.demoproject.model.Spell;

@Service
public class SpellService {

    @Autowired
    SpellDao spellDao;

    /**
     * Will eventually save the spell to a mySQL DB.
     * Current implementation temporary.
     * 
     * @param spell The provided spell from the front end.
     * @return A confirmation String.
     */
    @Transactional
    public ResponseString createSpell(Spell spell) {

        spellDao.createSpell(spell);
        return new ResponseString("Spell " + spell.getName() + " was saved.");
    }

    /**
     * Get all spells in the DB.
     * Current implementation temporary.
     * 
     * @return A list of all spells in the DB.
     */
    public List<Spell> getSpells() {
        return spellDao.findAllSpells();
    }
    
    /**
     * Get all spells belonging to a given user id.
     * Current implementation temporary.
     * 
     * @return A list of all spells belonging to the given user.
     */
    /*public List<Spell> getUserSpells(Long userId) {
        List<Spell> userSpells = spells.stream()
            .filter((s) -> {
                if (s.getUserId() == userId) {
                    return true;
                }
                return false;
            })
            .collect(Collectors.toList());
        return userSpells;
    }*/

    /**
     * Delete a given spell.
     * Current implementation temporary.
     * 
     * @param spellId The id of the spell to be deleted.
     * @return A confirmation String.
     */
    /*public ResponseString deleteSpell(Long spellId) {
        List<Spell> spellList = spells.stream()
            .filter((s) -> {
                if (s.getId() == spellId) {
                    return false;
                }
                return true;
            })
            .collect(Collectors.toList());
        spells = spellList;
        return new ResponseString("Spell " + spellId + " was deleted.");
    }*/

    /*private static List<Spell> populateExampleSpells() {
        List<Spell> demoSpells = new ArrayList<Spell>();
        Spell spell1 = Spell.builder()
            .id(counter.incrementAndGet())
            .name("Fire Bolt")
            .level(0)
            .castingTime("1 Action")
            .range("120ft")
            .damageAmount("1d10")
            .damageType("Fire")
            .components("V,S")
            .duration("Instantaneous")
            .description("You hurl a mote of fire at a creature or object within range. Make a ranged spell attack against the target. On a hit, the target takes 1d10 fire damage. A flammable object hit by this spell ignites if it isn\'t being worn or carried. This spell\'s damage increases by 1d10 when you reach 5th level (2d10), 11th level (3d10), and 17th level (4d10).")
            .build();
        demoSpells.add(spell1);
        Spell spell2 = Spell.builder()
            .id(counter.incrementAndGet())
            .name("Ray of Frost")
            .level(0)
            .castingTime("1 Action")
            .range("60ft")
            .damageAmount("1d8")
            .damageType("Cold")
            .components("V,S")
            .duration("Instantaneous")
            .description("A frigid beam of blue-white light streaks toward a creature within range. Make a ranged spell attack against the target. On a hit, it takes 1d8 cold damage, and its speed is reduced by 10 feet until the start of your next turn. The spell's damage increases by 1d8 when you reach 5th level (2d8), 11th level (3d8), and 17th level (4d8).")
            .build();
        demoSpells.add(spell2);

        return demoSpells;
    }*/

}
