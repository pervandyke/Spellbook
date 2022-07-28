package com.vandyke.demoproject.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vandyke.demoproject.model.Spell;


@Repository
public class SpellDao {
    
    @Autowired
    SessionFactory sessionFactory;

    public Spell createSpell(Spell spell) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(spell);
        session.close();
        return spell;
    }

    public List<Spell> findAllSpells() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Spell> criteria = builder.createQuery(Spell.class);
        Root<Spell> rootEntry = criteria.from(Spell.class);
        CriteriaQuery<Spell> all = criteria.select(rootEntry);

        TypedQuery<Spell> allQuery = session.createQuery(all);
        List<Spell> spells = allQuery.getResultList();
        session.close();
        return spells;
    }

}
