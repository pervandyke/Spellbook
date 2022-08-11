package com.vandyke.demoproject.SpellbookAPI.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vandyke.demoproject.SpellbookAPI.exeptions.SpellNotFoundException;
import com.vandyke.demoproject.SpellbookAPI.exeptions.UserNotFoundException;
import com.vandyke.demoproject.SpellbookAPI.frontEndObjects.SpellData;
import com.vandyke.demoproject.SpellbookAPI.models.ResponseString;
import com.vandyke.demoproject.SpellbookAPI.models.Spell;
import com.vandyke.demoproject.SpellbookAPI.models.User;
import com.vandyke.demoproject.SpellbookAPI.repositories.SpellRepository;
import com.vandyke.demoproject.SpellbookAPI.repositories.UserRepository;

@Service
public class SpellService {
    
    @Autowired
    SpellRepository spellRepo;

    @Autowired
    UserRepository userRepo;
    
    
    /**
     * Save the spell to a DB.
     * 
     * @param data The provided spell from the front end.
     * @return A confirmation String.
     */
    public Spell createSpell(SpellData data) throws UserNotFoundException {
        Spell spell = dataToSpell(data);
        spell = spellRepo.save(spell);
        return spell;
    }

    /**
     * Edit spell.
     * Currently identical to create, but may have other logic later.
     * 
     * @param dataThe provided spell from the front end.
     * @return A confirmation String.
     * @throws UserNotFoundException
     */
    public Spell editSpell(SpellData data) throws UserNotFoundException {
        Spell spell = dataToSpell(data);
        spell = spellRepo.save(spell);
        return spell;
    }

    /**
     * Gets a single spell by its Id.
     * 
     * @param id The spell Id.
     * @return The SpellData for the requested spell.
     * @throws SpellNotFoundException
     */
    /*public SpellData getSpellById(Long id) throws SpellNotFoundException {
        Optional<Spell> spell = spellRepo.findById(id);
        if (spell.isPresent()) {
            SpellData spellData = spellToData(spell.get());
            return spellData;
        }
        throw new SpellNotFoundException("Spell with id " + id + " not found.");
    }*/

    /**
     * Get all spells in the DB.
     * 
     * @return A list of all spells in the DB.
     */
    /*public List<SpellData> getSpells() {
        List<Spell> spellList = spellRepo.findAll();
        List<SpellData> spellDataList = spellList.stream().map((spell) -> spellToData(spell)).collect(Collectors.toList());
        return  spellDataList;
    }*/
    
    /**
     * Get all spells belonging to a given user id.
     * 
     * @return A list of all spells belonging to the given user.
     */
    public List<SpellData> getUserSpells(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            List<Spell> spellList = spellRepo.findAllByUser(user.get());
            List<SpellData> spellDataList = spellList.stream().map((spell) -> spellToData(spell)).collect(Collectors.toList());
            return spellDataList;
        }
        throw new UserNotFoundException("User with id " + userId + " not found.");
    }

    /**
     * Delete a given spell.
     * 
     * @param spellId The id of the spell to be deleted.
     * @return A confirmation String.
     */
    public ResponseString deleteSpell(Long spellId) throws SpellNotFoundException {
        if (spellRepo.existsById(spellId)) {
            spellRepo.deleteById(spellId);
            return new ResponseString("Spell " + spellId + " was deleted.");
        }
        throw new SpellNotFoundException("Spell " + spellId + " not found.");
    }

    private Spell dataToSpell(SpellData data) throws UserNotFoundException {
        Spell spell = new Spell();

        spell.setId(data.getId());

        Optional<User> user = userRepo.findById(data.getUserId());
        if (user.isPresent()) {
            spell.setUser(user.get());
        } else {
            throw new UserNotFoundException("User with id " + data.getUserId() + " not found.");
        }

        spell.setLevel(data.getLevel());
        spell.setName(data.getName());
        spell.setCastingTime(data.getCastingTime());
        spell.setRange(data.getRange());
        spell.setDamageAmount(data.getDamageAmount());
        spell.setDamageType(data.getDamageType());
        spell.setComponents(data.getComponents());
        spell.setDuration(data.getDuration());
        spell.setSave(data.getSave());
        spell.setDescription(data.getDescription());
        
        return spell;
    }

    private SpellData spellToData(Spell spell) {
        SpellData data = new SpellData();

        data.setId(spell.getId());
        data.setUserId(spell.getUser().getId());
        data.setLevel(spell.getLevel());
        data.setName(spell.getName());
        data.setCastingTime(spell.getCastingTime());
        data.setRange(spell.getRange());
        data.setDamageAmount(spell.getDamageAmount());
        data.setDamageType(spell.getDamageType());
        data.setComponents(spell.getComponents());
        data.setDuration(spell.getDuration());
        data.setSave(spell.getSave());
        data.setDescription(spell.getDescription());

        return data;
    }

}
