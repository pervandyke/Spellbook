package com.vandyke.demoproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vandyke.demoproject.dao.SpellDao;
import com.vandyke.demoproject.dao.UserDao;
import com.vandyke.demoproject.exceptions.SpellNotFoundException;
import com.vandyke.demoproject.exceptions.UserNotFoundException;
import com.vandyke.demoproject.frontEndObjects.SpellData;
import com.vandyke.demoproject.model.ResponseString;
import com.vandyke.demoproject.model.Spell;
import com.vandyke.demoproject.model.User;

@Service
public class SpellService {

    @Autowired
    SpellDao spellDao;

    @Autowired
    UserDao userDao;

    /**
     * Will eventually save the spell to a mySQL DB.
     * Current implementation temporary.
     * 
     * @param spell The provided spell from the front end.
     * @return A confirmation String.
     */
    public ResponseString createSpell(SpellData data) throws UserNotFoundException {
        Spell spell = dataToSpell(data);
        System.out.println("Creating spell " + spell.getName());
        spellDao.createSpell(spell);
        return new ResponseString("Spell " + spell.getName() + " was saved.");
    }

    public ResponseString editSpell(SpellData data) throws UserNotFoundException {
        Spell spell = dataToSpell(data);
        return new ResponseString(spellDao.editSpell(spell));
    }

    public SpellData getSpellById(Long Id) throws SpellNotFoundException {
        Spell spell = spellDao.findSpellById(Id);
        SpellData spellData = spellToData(spell);
        return spellData;
    }

    /**
     * Get all spells in the DB.
     * Current implementation temporary.
     * 
     * @return A list of all spells in the DB.
     */
    public List<SpellData> getSpells() {
        List<Spell> spellList = spellDao.findAllSpells();
        List<SpellData> spellDataList = spellList.stream().map((spell) -> spellToData(spell)).collect(Collectors.toList());
        return  spellDataList;
    }
    
    /**
     * Get all spells belonging to a given user id.
     * Current implementation temporary.
     * 
     * @return A list of all spells belonging to the given user.
     */
    public List<SpellData> getUserSpells(Long userId) {
        List<Spell> spellList = spellDao.findUserSpells(userId);
        List<SpellData> spellDataList = spellList.stream().map((spell) -> spellToData(spell)).collect(Collectors.toList());
        return spellDataList;
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

    private Spell dataToSpell(SpellData data) throws UserNotFoundException {
        Spell spell = new Spell();

        spell.setId(data.getId());
        User user = userDao.findUserById(data.getUserId());
        if (user != null) {
            spell.setUser(user);
        } else {
            throw new UserNotFoundException("User with id " + data.getId() + " not found.");
        }
        spell.setLevel(data.getLevel());
        spell.setName(data.getName());
        spell.setCastingTime(data.getCastingTime());
        spell.setRange(data.getRange());
        spell.setDamageAmount(data.getDamageAmount());
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
        data.setComponents(spell.getComponents());
        data.setDuration(spell.getDuration());
        data.setSave(spell.getSave());
        data.setDescription(spell.getDescription());

        return data;
    }

}
