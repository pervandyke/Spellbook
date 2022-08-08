package com.vandyke.demoproject.SpellbookAPI.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vandyke.demoproject.SpellbookAPI.models.Spell;
import com.vandyke.demoproject.SpellbookAPI.models.User;

@Repository
public interface SpellRepository extends CrudRepository<Spell, Long> {
    List<Spell> findAllByUser(User user);

    List<Spell> findAll();
}
