package com.vandyke.demoproject.service;

import java.util.ArrayList;
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

    static {
        spells = populateExampleSpells();
    }

    /**
     * Will eventually save the spell to a DB, and return that entity.
     * 
     * @param spell The provided spell from the front end.
     * @return A confirmation String.
     */
    public String createSpell(Spell spell) {
        spell.setId(counter.incrementAndGet());
        spells.add(spell);
        System.out.println("Added Spell " + spell.getName());
        return "Spell " + spell.getName() + " was added with ID " + spell.getId() + ".";
    }

    public List<Spell> getSpells() {
        return spells;
    }

    private static List<Spell> populateExampleSpells() {
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
    }

}
