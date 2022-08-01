package com.vandyke.demoproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vandyke.demoproject.dao.SpellDao;
import com.vandyke.demoproject.exceptions.SpellNotFoundException;
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
    public ResponseString createSpell(Spell spell) {
        spellDao.createSpell(spell);
        return new ResponseString("Spell " + spell.getName() + " was saved.");
    }

    public Spell getSpellById(Long Id) throws SpellNotFoundException {
        return spellDao.findSpellById(Id);
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
    public List<Spell> getUserSpells(Long userId) {
        return null;
    }

    /**
     * Delete a given spell.
     * Current implementation temporary.
     * 
     * @param spellId The id of the spell to be deleted.
     * @return A confirmation String.
     */
    public ResponseString deleteSpell(Long spellId) {
        spellDao.deleteSpell(spellId);
        return new ResponseString("Spell " + spellId + " was deleted.");
    }

    /*private static List<Spell> populateExampleSpells() {
        List<Spell> demoSpells = new ArrayList<Spell>();
        Spell spell1 = Spell.builder()
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
